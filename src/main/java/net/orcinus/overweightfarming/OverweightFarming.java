package net.orcinus.overweightfarming;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.event.GameEvent;
import net.orcinus.overweightfarming.OFConfig;
import net.orcinus.overweightfarming.registry.OFObjects;
import net.orcinus.overweightfarming.util.EmeraldToItemOffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OverweightFarming implements ModInitializer {

    /*TODO
    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(OFObjects.SEEDED_PEELED_MELON.get(), OFBlocks.WAXED_SEEDED_PEELED_MELON.get())
            .put(OFBlocks.HALF_SEEDED_PEELED_MELON.get(), OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get())
            .put(OFBlocks.SEEDLESS_PEELED_MELON.get(), OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get())
            .build());

     */
    //TODO public static final Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> WAXABLES.get().inverse());
    public static final Supplier<BiMap<Block, Block>> PEELABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(OFObjects.OVERWEIGHT_BEETROOT, OFObjects.PEELED_OVERWEIGHT_BEETROOT)
            .put(OFObjects.OVERWEIGHT_CARROT, OFObjects.PEELED_OVERWEIGHT_CARROT)
            .put(OFObjects.OVERWEIGHT_POTATO, OFObjects.PEELED_OVERWEIGHT_POTATO)
            .put(OFObjects.OVERWEIGHT_ONION, OFObjects.PEELED_OVERWEIGHT_ONION)
            .put(OFObjects.OVERWEIGHT_KIWI, OFObjects.OVERWEIGHT_SLICED_KIWI)
            .put(OFObjects.OVERWEIGHT_SLICED_KIWI, OFObjects.PEELED_OVERWEIGHT_KIWI)
            .put(OFObjects.OVERWEIGHT_GINGER, OFObjects.PEELED_OVERWEIGHT_GINGER)
            .put(OFObjects.OVERWEIGHT_COCOA, OFObjects.PEELED_OVERWEIGHT_COCOA)
            //TODO .put(Blocks.MELON, OFObjects.SEEDED_PEELED_MELON.get())
            .build());
    public static final Supplier<BiMap<Block, Block>> UNPEELABLES = Suppliers.memoize(() -> PEELABLES.get().inverse());


    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "overweight_farming";
    public static OFConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(OFConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(OFConfig.class).getConfig();
        OFObjects.init();

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5, factories -> {
            factories.add(new EmeraldToItemOffer(new ItemStack(OFObjects.STRAW_HAT), 20, 1, 12, 0.05F));
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getMainHandStack();
            BlockState state = world.getBlockState(hitResult.getBlockPos());
            BlockPos blockPos = hitResult.getBlockPos();

            if (stack.getItem() instanceof AxeItem) {
                /*
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

                 */
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
            /*TODO
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

             */
            if (stack.getItem() == OFObjects.VEGETABLE_PEELS) {
                for (Block block : UNPEELABLES.get().keySet()) {
                    if (state.isOf(block)) {
                        if (!player.getAbilities().creativeMode){
                            stack.decrement(1);
                        }
                        world.playSound(null, blockPos, SoundEvents.ENTITY_GLOW_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        world.setBlockState(blockPos, UNPEELABLES.get().get(block).getDefaultState());
                        return ActionResult.success(true);
                    }
                }
            }

            return ActionResult.PASS;
        });
    }

}
