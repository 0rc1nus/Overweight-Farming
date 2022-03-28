package com.binome.overweightfarming.blocks;

import com.binome.overweightfarming.init.OFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class OverweightOnionBlock extends CropFullBlock {

    public OverweightOnionBlock(Block stemBlock, Properties properties) {
        super(stemBlock, properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        BlockState plant = plantable.getPlant(world, pos.relative(facing));
        return plant.is(OFBlocks.ALLIUM_BUSH.get());
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos blockPos, BlockState state, boolean isClient) {
        return world.getBlockState(blockPos.above()).isAir() && world.getBlockState(blockPos.above(2)).isAir();
    }

    @Override
    public void performBonemeal(ServerLevel world, Random random, BlockPos blockPos, BlockState state) {
        BlockPos above = blockPos.above();
        BlockPos below = blockPos.below();
        if (this.stemBlock != null && world.getBlockState(above).isAir() && world.getBlockState(blockPos.above(2)).isAir()) {
            world.setBlock(above, stemBlock.defaultBlockState(), 2);
            DoublePlantBlock.placeAt(world, this.stemBlock.defaultBlockState(), above, 2);
        }
        if (world.isStateAtPosition(below, BlockBehaviour.BlockStateBase::isAir)) {
            world.setBlock(below, Blocks.HANGING_ROOTS.defaultBlockState(), 2);
        }
    }
}
