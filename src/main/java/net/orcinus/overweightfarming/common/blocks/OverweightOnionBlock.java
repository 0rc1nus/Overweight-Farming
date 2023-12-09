package net.orcinus.overweightfarming.common.blocks;

import net.minecraft.block.*;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.orcinus.overweightfarming.common.registry.OFObjects;

import java.util.Iterator;

public class OverweightOnionBlock extends CropFullBlock {

    public OverweightOnionBlock(Block stemBlock, Settings settings) {
        super(stemBlock, settings);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
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
}