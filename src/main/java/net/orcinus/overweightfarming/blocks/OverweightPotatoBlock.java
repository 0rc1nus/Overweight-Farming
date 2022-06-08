package net.orcinus.overweightfarming.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orcinus.overweightfarming.registry.OFObjects;
import org.jetbrains.annotations.Nullable;

import java.util.Random;


public class OverweightPotatoBlock extends CropFullBlock {
    public OverweightPotatoBlock(Block stemBlock, Settings properties) {
        super(stemBlock, properties);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        world.createAndScheduleBlockTick(pos, this, 900);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        BlockState belowState = world.getBlockState(pos.down());
        boolean flag = belowState.isIn(BlockTags.FIRE) || belowState.isIn(BlockTags.CAMPFIRES);
        if (this == OFObjects.OVERWEIGHT_POTATO && flag) {
            world.setBlockState(pos, OFObjects.OVERWEIGHT_BAKED_POTATO.getDefaultState());
        }
    }
}
