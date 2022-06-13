package net.orcinus.overweightfarming.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Random;


public class NetherCropFullBlock extends CropFullBlock{
    public NetherCropFullBlock(Block stemBlock, Settings properties) {
        super(stemBlock, properties);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos.up(), this.getDefaultState(), Block.NOTIFY_LISTENERS);
        world.setBlockState(pos, this.stemBlock.getDefaultState(), Block.NOTIFY_LISTENERS);
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return !world.getBlockState(pos.down()).isOf(this.stemBlock) && world.getBlockState(pos.up()).isAir();
    }
}
