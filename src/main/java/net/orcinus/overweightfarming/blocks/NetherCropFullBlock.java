package net.orcinus.overweightfarming.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class NetherCropFullBlock extends CropFullBlock {

    public NetherCropFullBlock(Block stemBlock, Properties properties) {
        super(stemBlock, properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        BlockState stateBelow = level.getBlockState(pos.below());
        return !stateBelow.is(stemBlock) && stateBelow.isFaceSturdy(level, pos.below(), Direction.UP) && level.getBlockState(pos.above()).isAir();
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, stemBlock.defaultBlockState(), Block.UPDATE_CLIENTS);
        level.setBlock(pos.above(), defaultBlockState(), Block.UPDATE_CLIENTS);
    }

}
