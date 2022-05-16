package com.binome.overweightfarming.events;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.init.OFBlocks;
import com.binome.overweightfarming.init.OFItems;
import com.binome.overweightfarming.util.OverweightGrowthManager;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MiscEvents {
    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(OFBlocks.SEEDED_PEELED_MELON.get(), OFBlocks.WAXED_SEEDED_PEELED_MELON.get())
            .put(OFBlocks.HALF_SEEDED_PEELED_MELON.get(), OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get())
            .put(OFBlocks.SEEDLESS_PEELED_MELON.get(), OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get())
            .build());
    public static final Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> WAXABLES.get().inverse());
    public static final Supplier<BiMap<Block, Block>> PEELABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(OFBlocks.OVERWEIGHT_BEETROOT.get(), OFBlocks.PEELED_OVERWEIGHT_BEETROOT.get())
            .put(OFBlocks.OVERWEIGHT_CARROT.get(), OFBlocks.PEELED_OVERWEIGHT_CARROT.get())
            .put(OFBlocks.OVERWEIGHT_POTATO.get(), OFBlocks.PEELED_OVERWEIGHT_POTATO.get())
            .put(OFBlocks.OVERWEIGHT_ONION.get(), OFBlocks.PEELED_OVERWEIGHT_ONION.get())
            .put(OFBlocks.OVERWEIGHT_KIWI.get(), OFBlocks.OVERWEIGHT_SLICED_KIWI.get())
            .put(OFBlocks.OVERWEIGHT_SLICED_KIWI.get(), OFBlocks.PEELED_OVERWEIGHT_KIWI.get())
            .put(OFBlocks.OVERWEIGHT_GINGER.get(), OFBlocks.PEELED_OVERWEIGHT_GINGER.get())
            .put(Blocks.MELON, OFBlocks.SEEDED_PEELED_MELON.get())
            .build());
    public static final Supplier<BiMap<Block, Block>> UNPEELABLES = Suppliers.memoize(() -> PEELABLES.get().inverse());

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        Level world = event.getWorld();
        BlockPos blockPos = event.getPos();
        BlockState state = world.getBlockState(blockPos);
        Player player = event.getPlayer();
        InteractionHand hand = event.getHand();
        if (stack.getItem() instanceof AxeItem) {
            for (Block block : WAX_OFF_BY_BLOCK.get().keySet()) {
                if (state.is(block)) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, stack);
                    }
                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
                    world.playSound(null, blockPos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.setBlockAndUpdate(blockPos, WAX_OFF_BY_BLOCK.get().get(block).defaultBlockState());
                    world.levelEvent(player, 3004, blockPos, 0);
                    player.swing(hand);
                }
            }
            for (Block block : PEELABLES.get().keySet()) {
                if (state.is(block)) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, stack);
                    }
                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
                    world.playSound(null, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    Block.popResource(world, blockPos, new ItemStack(OFItems.VEGETABLE_PEELS.get()));
                    world.setBlockAndUpdate(blockPos, PEELABLES.get().get(block).defaultBlockState());
                    player.swing(hand);
                }
            }
        }
        if (stack.getItem() == Items.HONEYCOMB) {
            for (Block block : WAXABLES.get().keySet()) {
                if (state.is(block)) {
                    event.setCanceled(true);
                    if (player instanceof ServerPlayer serverPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, stack);
                    }
                    if (!player.getAbilities().instabuild) stack.shrink(1);
                    world.playSound(null, blockPos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.setBlockAndUpdate(blockPos, WAXABLES.get().get(block).defaultBlockState());
                    world.levelEvent(player, 3003, blockPos, 0);
                    event.setCancellationResult(InteractionResult.sidedSuccess(world.isClientSide));
                }
            }
        }
        if (stack.getItem() == OFItems.VEGETABLE_PEELS.get()) {
            for (Block block : UNPEELABLES.get().keySet()) {
                if (state.is(block)) {
                    event.setCanceled(true);
                    if (player instanceof ServerPlayer serverPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, stack);
                    }
                    if (!player.getAbilities().instabuild) stack.shrink(1);
                    world.playSound(null, blockPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.setBlockAndUpdate(blockPos, UNPEELABLES.get().get(block).defaultBlockState());
                    event.setCancellationResult(InteractionResult.sidedSuccess(world.isClientSide));
                }
            }
        }
    }

    @SubscribeEvent
    public void onCropsGrow(BlockEvent.CropGrowEvent.Pre event) {
        LevelAccessor level = event.getWorld();
        BlockPos blockPos = event.getPos();
        BlockState state = event.getState();
        Random random = level.getRandom();
        OverweightGrowthManager manager = new OverweightGrowthManager(random);
        if (level instanceof ServerLevel world) {
            for (Block cropBlock : manager.getOverweightMap().keySet()) {
                if (state.is(cropBlock)) {
                    boolean flag = state.hasProperty(CropBlock.AGE) && state.getValue(CropBlock.AGE) < 7 && state.getValue(CropBlock.AGE) == 3;
                    boolean flag1 = state.hasProperty(CocoaBlock.AGE) && state.getValue(CocoaBlock.AGE) == 1;
                    boolean flag2 = state.hasProperty(BeetrootBlock.AGE) && state.getValue(BeetrootBlock.AGE) < BeetrootBlock.MAX_AGE && state.getValue(BeetrootBlock.AGE) > 1;
                    boolean flag3 = ModList.get().isLoaded("hedgehog") && state.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("hedgehog", "kiwi_vines")) && state.getValue(BlockStateProperties.BERRIES);
                    if (flag || flag1 || flag2 || flag3) {
                        float chance = world.isNight() && world.getMoonPhase() == 0 ? 0.0010538863F : 3.4290552E-4F;
                        if (random.nextFloat() < chance) {
                            event.setResult(Event.Result.DENY);
                            manager.growOverweightCrops(world, blockPos, state, random);
                        }
                    }
                }
            }
        }
    }

}
