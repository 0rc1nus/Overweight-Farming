package net.orcinus.overweightfarming.blocks;

import net.orcinus.overweightfarming.init.OFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.util.RandomSource;

public class CropFullBlock extends Block implements BonemealableBlock {
    public final Block stemBlock;

    public CropFullBlock(Block stemBlock, Properties properties) {
        super(properties);
        this.stemBlock = stemBlock;
    }

    public Block getStemBlock() {
        return this.stemBlock;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos blockPos, BlockState state, boolean isClient) {
        return !world.getBlockState(blockPos.above()).is(this.stemBlock);
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos blockPos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos blockPos, BlockState state) {
        BlockPos above = blockPos.above();
        BlockPos below = blockPos.below();
        if (this.stemBlock != null && world.getBlockState(above).isAir()) {
            world.setBlock(above, stemBlock.defaultBlockState(), 2);
        }
        if (world.isStateAtPosition(below, BlockBehaviour.BlockStateBase::isAir) && this.shouldGrowRoots()) {
            world.setBlock(below, Blocks.HANGING_ROOTS.defaultBlockState(), 2);
        }
    }

    public boolean shouldGrowRoots() {
        return true;
    }
}
