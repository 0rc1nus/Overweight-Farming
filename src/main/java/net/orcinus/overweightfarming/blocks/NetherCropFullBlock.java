package net.orcinus.overweightfarming.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class NetherCropFullBlock extends CropFullBlock {

    public NetherCropFullBlock(Block stemBlock, Properties properties) {
        super(stemBlock, properties);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos blockPos, BlockState state, boolean isClient) {
        return !world.getBlockState(blockPos.below()).is(this.stemBlock) && world.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public void performBonemeal(ServerLevel world, Random random, BlockPos blockPos, BlockState state) {
        world.setBlock(blockPos, this.stemBlock.defaultBlockState(), 2);
        world.setBlock(blockPos.above(), this.defaultBlockState(), 2);
    }
}