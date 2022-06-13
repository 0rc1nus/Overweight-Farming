package net.orcinus.overweightfarming.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NetherCropStemBlock extends CropStemBlock {
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

    public NetherCropStemBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction getFacingDirection() {
        return Direction.DOWN;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos blockPos, CollisionContext context) {
        return SHAPE;
    }
}
