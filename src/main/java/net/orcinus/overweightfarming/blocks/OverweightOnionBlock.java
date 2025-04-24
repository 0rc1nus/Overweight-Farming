package net.orcinus.overweightfarming.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;
import net.orcinus.overweightfarming.init.OFBlocks;

public class OverweightOnionBlock extends CropFullBlock {

    public OverweightOnionBlock(Block stemBlock, Properties properties) {
        super(stemBlock, properties);
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
        return plant.is(OFBlocks.ALLIUM_BUSH.get()) ? TriState.TRUE : super.canSustainPlant(state, level, soilPosition, facing, plant);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos blockPos, BlockState state) {
        return world.getBlockState(blockPos.above()).isAir() && world.getBlockState(blockPos.above(2)).isAir();
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos blockPos, BlockState state) {
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
