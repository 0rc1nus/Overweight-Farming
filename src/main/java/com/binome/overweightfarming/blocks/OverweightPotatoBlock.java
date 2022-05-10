package com.binome.overweightfarming.blocks;

import com.binome.overweightfarming.init.OFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class OverweightPotatoBlock extends CropFullBlock {

    public OverweightPotatoBlock(Block stemBlock, Properties properties) {
        super(stemBlock, properties);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState p_60569_, boolean p_60570_) {
        world.scheduleTick(pos, this, 900);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, Random p_60465_) {
        BlockState belowState = world.getBlockState(pos.below());
        boolean flag = belowState.is(BlockTags.FIRE) || belowState.is(BlockTags.CAMPFIRES);
        if (this == OFBlocks.OVERWEIGHT_POTATO.get() && flag) {
            world.setBlockAndUpdate(pos, OFBlocks.OVERWEIGHT_BAKED_POTATO.get().defaultBlockState());
        }
    }
}
