package net.orcinus.overweightfarming.common.blocks;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;


public class CropFullBlock extends PlantBlock implements Fertilizable {
    public final Block stemBlock;
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public CropFullBlock(Block stemBlock, Settings properties) {
        super(properties);
        this.stemBlock = stemBlock;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return super.canPlaceAt(state, world, pos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {

        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    public Block getStemBlock() {
        return this.stemBlock;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return !world.getBlockState(pos.up()).isOf(this.stemBlock);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos above = pos.up();
        BlockPos below = pos.down();
        if (this.stemBlock != null && world.getBlockState(above).isAir()) {
            world.setBlockState(above, stemBlock.getDefaultState(), 2);
        }
        if (world.testBlockState(below, AbstractBlockState::isAir)) {
            world.setBlockState(below, Blocks.HANGING_ROOTS.getDefaultState(), 2);
        }
    }

    //NOOP ALL FOR CREATE COMPAT

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state;
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    @Override
    public boolean hasRandomTicks(BlockState state) {
        return false;
    }


    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        //NOOP
    }

    public boolean shouldGrowRoots() {
        return true;
    }
}
