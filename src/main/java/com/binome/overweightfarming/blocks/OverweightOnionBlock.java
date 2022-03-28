package com.binome.overweightfarming.blocks;

import com.binome.overweightfarming.init.OFObjects;
import net.minecraft.block.*;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

import java.util.Iterator;

public class OverweightOnionBlock extends CropFullBlock {

    public OverweightOnionBlock(Block stemBlock, Settings settings) {
        super(stemBlock, settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Iterator var4 = Direction.Type.HORIZONTAL.iterator();
        Direction direction;
        Material material;
        do {
            if (!var4.hasNext()) {
                BlockState blockState2 = world.getBlockState(pos.down());
                return (blockState2.isOf(OFObjects.ALLIUM_BUSH) && !world.getBlockState(pos.up()).getMaterial().isLiquid());
            }
            direction = (Direction)var4.next();
            BlockState blockState = world.getBlockState(pos.offset(direction));
            material = blockState.getMaterial();
        } while(!material.isSolid() && !world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA));

        return false;
    }
}