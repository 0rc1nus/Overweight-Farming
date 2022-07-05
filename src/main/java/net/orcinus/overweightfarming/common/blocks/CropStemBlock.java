package net.orcinus.overweightfarming.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class CropStemBlock extends PlantBlock {
    public static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);

    public CropStemBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = this.getFacingDirection();
        BlockPos blockpos = pos.offset(direction.getOpposite());
        return world.getBlockState(blockpos).isSideSolidFullSquare(world, blockpos, direction);
    }

    public Direction getFacingDirection() {
        return Direction.UP;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
