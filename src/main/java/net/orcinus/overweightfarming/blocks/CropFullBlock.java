package net.orcinus.overweightfarming.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CropFullBlock extends BushBlock implements BonemealableBlock {
    public final Block stemBlock;
    private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public CropFullBlock(Block stemBlock, Properties properties) {
        super(properties);
        this.stemBlock = stemBlock;
        this.registerDefaultState(this.stateDefinition.any());
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
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
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos blockPos) {
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

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState p_51034_, LevelAccessor world, BlockPos blockPos, BlockPos p_51037_) {
        return state;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
    }

    public boolean shouldGrowRoots() {
        return true;
    }

}
