package net.orcinus.overweightfarming.util;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orcinus.overweightfarming.registry.OFObjects;

public class OFUtils {
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

    public static ActionResult stripMelon(PlayerEntity player, World world, Hand hand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getMainHandStack();
        BlockState state = world.getBlockState(blockHitResult.getBlockPos());
        BlockPos blockPos = blockHitResult.getBlockPos();

        if (stack.getItem() instanceof AxeItem) {

            for (Block block : OFUtils.WAX_OFF_BY_BLOCK.get().keySet()) {
                if (state.isOf(block)) {
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, stack);
                    }
                    stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                    world.playSound(null, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(blockPos, OFUtils.WAX_OFF_BY_BLOCK.get().get(block).getDefaultState());
                    world.syncWorldEvent(player, 3004, blockPos, 0);
                    player.swingHand(hand);
                }
            }


            for (Block block : OFUtils.PEELABLES.get().keySet()) {
                if (state.isOf(block)) {
                    stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                    world.playSound(null, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    Block.dropStack(world, blockPos, new ItemStack(OFObjects.VEGETABLE_PEELS));
                    world.setBlockState(blockPos, OFUtils.PEELABLES.get().get(block).getDefaultState());
                    player.swingHand(hand);
                }
            }
        }
        if (stack.getItem() == Items.HONEYCOMB) {
            for (Block block : OFUtils.WAXABLES.get().keySet()) {
                if (state.isOf(block)) {
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, stack);
                    }
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                    world.playSound(null, blockPos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(blockPos, OFUtils.WAXABLES.get().get(block).getDefaultState());
                    world.syncWorldEvent(player, 3003, blockPos, 0);
                    return ActionResult.success(world.isClient());
                }
            }
        }

        if (stack.getItem() == OFObjects.VEGETABLE_PEELS) {
            for (Block block : OFUtils.UNPEELABLES.get().keySet()) {
                if (state.isOf(block)) {
                    if (!player.getAbilities().creativeMode){
                        stack.decrement(1);
                    }
                    world.playSound(null, blockPos, SoundEvents.ENTITY_GLOW_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(blockPos, OFUtils.UNPEELABLES.get().get(block).getDefaultState());
                    return ActionResult.success(true);
                }
            }
        }

        return ActionResult.PASS;
    }
}

