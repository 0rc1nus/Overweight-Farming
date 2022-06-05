package com.binome.overweightfarming.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NetherCropBlock extends CropFullBlock{
    public NetherCropBlock(Block stemBlock, Settings properties) {
        super(stemBlock, properties);
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        super.applyGrowth(world, pos, state);
    }
}
