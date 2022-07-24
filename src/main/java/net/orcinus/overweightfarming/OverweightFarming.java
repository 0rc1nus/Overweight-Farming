package net.orcinus.overweightfarming;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.orcinus.overweightfarming.common.networking.c2s.C2SFluffPacket;
import net.orcinus.overweightfarming.common.registry.*;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;


public class OverweightFarming implements ModInitializer {
    public static final String MODID = "overweight_farming";
    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(OFObjects.SEEDED_PEELED_MELON, OFObjects.WAXED_SEEDED_PEELED_MELON)
            .put(OFObjects.HALF_SEEDED_PEELED_MELON, OFObjects.WAXED_HALF_SEEDED_PEELED_MELON)
            .put(OFObjects.SEEDLESS_PEELED_MELON, OFObjects.WAXED_SEEDLESS_PEELED_MELON)
            .build());
    public static final Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> WAXABLES.get().inverse());
    public static final Supplier<BiMap<Block, Block>> PEELABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(OFObjects.OVERWEIGHT_BEETROOT, OFObjects.PEELED_OVERWEIGHT_BEETROOT)
            .put(OFObjects.OVERWEIGHT_CARROT, OFObjects.PEELED_OVERWEIGHT_CARROT)
            .put(OFObjects.OVERWEIGHT_POTATO, OFObjects.PEELED_OVERWEIGHT_POTATO)
            .put(OFObjects.OVERWEIGHT_ONION, OFObjects.PEELED_OVERWEIGHT_ONION)
            .put(OFObjects.OVERWEIGHT_KIWI, OFObjects.OVERWEIGHT_SLICED_KIWI)
            .put(OFObjects.OVERWEIGHT_SLICED_KIWI, OFObjects.PEELED_OVERWEIGHT_KIWI)
            .put(OFObjects.OVERWEIGHT_GINGER, OFObjects.PEELED_OVERWEIGHT_GINGER)
            .put(OFObjects.OVERWEIGHT_COCOA, OFObjects.PEELED_OVERWEIGHT_COCOA)
            .put(Blocks.MELON, OFObjects.SEEDED_PEELED_MELON)
            .build());
    public static final Supplier<BiMap<Block, Block>> UNPEELABLES = Suppliers.memoize(() -> PEELABLES.get().inverse());


    @Override
    public void onInitialize() {
        MidnightConfig.init(MODID, OFConfig.class);

        OFObjects.init();
        OFEntityTypes.init();
        OFBlockEntityTypes.init();
        OFWorldGenerators.init();
        OFLootTables.init();
        OFTrades.init();

        UseBlockCallback.EVENT.register(this::stripMelon);
        UseBlockCallback.EVENT.register(this::growBloodroot);
        UseEntityCallback.EVENT.register(this::interactPig);

        ServerPlayNetworking.registerGlobalReceiver(C2SFluffPacket.ID, C2SFluffPacket::handle);
    }

    private ActionResult interactPig(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (entity instanceof PigEntity pigEntity) {
            if (stack.getItem() == OFObjects.VEGETABLE_PEELS) {
                int i = pigEntity.getBreedingAge();
                if (!world.isClient() && i == 0 && pigEntity.isReadyToBreed()) {
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                    pigEntity.lovePlayer(player);
                    pigEntity.emitGameEvent(GameEvent.MOB_INTERACT, pigEntity);
                    return ActionResult.SUCCESS;
                }
                if (pigEntity.isBaby()) {
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                    pigEntity.growUp((int) ((float) (-i / 20) * 0.1F), true);
                    pigEntity.emitGameEvent(GameEvent.MOB_INTERACT, pigEntity);
                    player.swingHand(hand);
                }
                if (world.isClient()) {
                    return ActionResult.PASS;
                }
            }
        }
        return ActionResult.PASS;
    }

    private ActionResult growBloodroot(PlayerEntity player, World world, Hand hand, BlockHitResult blockHitResult) {
        Identifier identifier = new Identifier("bwplus", "bloodroot");
        if (FabricLoader.getInstance().isModLoaded("bwplus") && Registry.BLOCK.containsId(identifier)) {
            if (world.getBlockState(blockHitResult.getBlockPos()).isOf(Registry.BLOCK.get(identifier))) {
                if (player.getMainHandStack().getItem() instanceof BoneMealItem) {
                    if (!player.isCreative()) {
                        player.getMainHandStack().decrement(1);
                    }

                    if (!world.isClient) {
                        world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockHitResult.getBlockPos(), 0);
                    }
                    BoneMealItem.createParticles(world, blockHitResult.getBlockPos(), 8);

                    if (world.getRandom().nextFloat() > 0.75F) {
                        world.setBlockState(blockHitResult.getBlockPos(), OFObjects.OVERWEIGHT_BLOODROOT.getDefaultState(), Block.NOTIFY_ALL);
                        if (world.getBlockState(blockHitResult.getBlockPos().up()).isAir()) {
                            world.setBlockState(blockHitResult.getBlockPos().up(), OFObjects.OVERWEIGHT_BLOODROOT_STEM.getDefaultState(), Block.NOTIFY_ALL);
                        }
                    }
                    return ActionResult.SUCCESS;

                }
            }

        }
        return ActionResult.PASS;
    }

    public ActionResult stripMelon(PlayerEntity player, World world, Hand hand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getMainHandStack();
        BlockState state = world.getBlockState(blockHitResult.getBlockPos());
        BlockPos blockPos = blockHitResult.getBlockPos();

        if (stack.getItem() instanceof AxeItem) {
            for (Block block : WAX_OFF_BY_BLOCK.get().keySet()) {
                if (state.isOf(block)) {
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, stack);
                    }
                    stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                    world.playSound(null, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(blockPos, WAX_OFF_BY_BLOCK.get().get(block).getDefaultState());
                    world.syncWorldEvent(player, 3004, blockPos, 0);
                    player.swingHand(hand);
                }
            }


            for (Block block : PEELABLES.get().keySet()) {
                if (state.isOf(block)) {
                    stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                    world.playSound(null, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    Block.dropStack(world, blockPos, new ItemStack(OFObjects.VEGETABLE_PEELS));
                    world.setBlockState(blockPos, PEELABLES.get().get(block).getDefaultState());
                    player.swingHand(hand);
                }
            }
        }
        if (stack.getItem() == Items.HONEYCOMB) {
            for (Block block : WAXABLES.get().keySet()) {
                if (state.isOf(block)) {
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, stack);
                    }
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                    world.playSound(null, blockPos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(blockPos, WAXABLES.get().get(block).getDefaultState());
                    world.syncWorldEvent(player, 3003, blockPos, 0);
                    return ActionResult.success(world.isClient());
                }
            }
        }

        if (stack.getItem() == OFObjects.VEGETABLE_PEELS) {
            for (Block block : UNPEELABLES.get().keySet()) {
                if (state.isOf(block)) {
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                    world.playSound(null, blockPos, SoundEvents.ENTITY_GLOW_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(blockPos, UNPEELABLES.get().get(block).getDefaultState());
                    return ActionResult.success(true);
                }
            }
        }

        return ActionResult.PASS;
    }

}
