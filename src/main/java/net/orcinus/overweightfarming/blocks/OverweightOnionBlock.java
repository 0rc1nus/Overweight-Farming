package net.orcinus.overweightfarming.blocks;

import net.orcinus.overweightfarming.init.OFObjects;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.Iterator;
import java.util.Random;

public class OverweightOnionBlock extends CropFullBlock {

    public OverweightOnionBlock(Block stemBlock, Settings settings) {
        super(stemBlock, settings);
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.up()).isAir() && world.getBlockState(pos.up(2)).isAir();
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos above = pos.up();
        BlockPos below = pos.down();
        if (this.getStemBlock() != null && world.getBlockState(above).isAir() && world.getBlockState(pos.up(2)).isAir()) {
            world.setBlockState(above, this.getStemBlock().getDefaultState(), 2);
            TallPlantBlock.placeAt(world, this.getStemBlock().getDefaultState(), above, 2);
        }
        if (world.testBlockState(below, AbstractBlockState::isAir)) {
            world.setBlockState(below, Blocks.HANGING_ROOTS.getDefaultState(), 2);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Iterator<Direction> var4 = Direction.Type.HORIZONTAL.iterator();
        Direction direction;
        Material material;
        do {
            if (!var4.hasNext()) {
                BlockState blockState2 = world.getBlockState(pos.down());
                return (blockState2.isOf(OFObjects.ALLIUM_BUSH) && !world.getBlockState(pos.up()).getMaterial().isLiquid());
            }
            direction = var4.next();
            BlockState blockState = world.getBlockState(pos.offset(direction));
            material = blockState.getMaterial();
        } while(!material.isSolid() && !world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA));

        return false;
    }
}