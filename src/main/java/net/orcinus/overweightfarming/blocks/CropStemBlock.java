package net.orcinus.overweightfarming.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CropStemBlock extends BushBlock {
    public static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

    public CropStemBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos blockPos) {
        Direction direction = Direction.UP;
        BlockPos blockpos = blockPos.relative(direction.getOpposite());
        return world.getBlockState(blockpos).isFaceSturdy(world, blockpos, direction);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos blockPos, CollisionContext context) {
        return SHAPE;
    }
}
