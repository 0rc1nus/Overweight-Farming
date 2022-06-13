package net.orcinus.overweightfarming.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.LandingBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.orcinus.overweightfarming.blockentities.OverweightAppleBlockEntity;
import net.orcinus.overweightfarming.entities.OverweightAppleFallingBlockEntity;
import net.orcinus.overweightfarming.registry.OFBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class OverweightAppleBlock extends CropFullBlock implements LandingBlock, BlockEntityProvider {
    private final boolean isGolden;
    public OverweightAppleBlock(boolean isGolden, Block stemBlock, Settings properties) {
        super(stemBlock, properties);
        this.isGolden = isGolden;
    }

    @Override
    public boolean shouldGrowRoots() {
        return false;
    }

    public static boolean isFree(BlockState state) {
        Material material = state.getMaterial();
        return state.isAir() || state.isIn(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
    }


    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        LandingBlock.super.onDestroyedOnLanding(world, pos, fallingBlockEntity);
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new OverweightAppleBlockEntity(pos, state);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.createAndScheduleBlockTick(pos, this, 2);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        world.createAndScheduleBlockTick(pos, this, 2);
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return !world.isClient() ? createTickerHelper(type, OFBlockEntityTypes.OVERWEIGHT_APPLE_BLOCK_ENTITY, OverweightAppleBlockEntity::tick) : null;
    }

    public void spawnFallingBlock(BlockState state, World world, BlockPos pos) {
        OverweightAppleFallingBlockEntity fallingBlockEntity = new OverweightAppleFallingBlockEntity(this.isGolden, world, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, state.contains(Properties.WATERLOGGED) ? state.with(Properties.WATERLOGGED, false) : state);
        world.setBlockState(pos, state.getFluidState().getBlockState(), Block.NOTIFY_ALL);
        world.spawnEntity(fallingBlockEntity);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> blockEntityType, BlockEntityType<E> blockEntityType1, BlockEntityTicker<? super E> ticker) {
        return blockEntityType1 == blockEntityType ? (BlockEntityTicker<A>)ticker : null;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState aboveState = world.getBlockState(pos.up());
        BlockState belowState = world.getBlockState(pos.down());
        if (!(aboveState.isIn(BlockTags.LEAVES) || aboveState.isIn(BlockTags.LOGS)) && isFree(belowState) && pos.getY() >= world.getBottomY()) {
            spawnFallingBlock(state, world, pos);
        }
    }
}
