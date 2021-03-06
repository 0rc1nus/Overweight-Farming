package net.orcinus.overweightfarming.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.orcinus.overweightfarming.init.OFBlocks;

public class OverweightPotatoBlock extends CropFullBlock {

    public OverweightPotatoBlock(Block stemBlock, Properties properties) {
        super(stemBlock, properties);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState neighborState, boolean isClient) {
        world.scheduleTick(pos, this, 900);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        BlockState belowState = world.getBlockState(pos.below());
        boolean flag = belowState.is(BlockTags.FIRE) || belowState.is(BlockTags.CAMPFIRES);
        if (this == OFBlocks.OVERWEIGHT_POTATO.get() && flag) {
            world.setBlockAndUpdate(pos, OFBlocks.OVERWEIGHT_BAKED_POTATO.get().defaultBlockState());
        }
    }
}
