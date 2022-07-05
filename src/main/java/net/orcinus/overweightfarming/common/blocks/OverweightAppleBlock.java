package net.orcinus.overweightfarming.common.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.orcinus.overweightfarming.common.blockentities.OverweightAppleBlockEntity;
import net.orcinus.overweightfarming.common.entities.OverweightAppleFallingBlockEntity;
import net.orcinus.overweightfarming.common.registry.OFBlockEntityTypes;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import org.jetbrains.annotations.Nullable;

public class OverweightAppleBlock extends CropFullBlock implements LandingBlock, BlockEntityProvider {
    private final boolean isGolden;

    public OverweightAppleBlock(boolean isGolden, Block stemBlock, Settings properties) {
        super(stemBlock, properties);
        this.isGolden = isGolden;
    }

    public static boolean isFree(BlockState state) {
        Material material = state.getMaterial();
        return state.isAir() || state.isIn(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> thisBlockEntityType, BlockEntityType<E> compareBlockEntityType, BlockEntityTicker<? super E> ticker) {
        return compareBlockEntityType == thisBlockEntityType ? (BlockEntityTicker<A>) ticker : null;
    }

    @Override
    public boolean shouldGrowRoots() {
        return false;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.testBlockState(pos.up(), AbstractBlockState::isAir)) {
            world.setBlockState(pos.up(), this.stemBlock.getDefaultState());
        }
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
        OverweightAppleFallingBlockEntity fallingBlockEntity = new OverweightAppleFallingBlockEntity(this.isGolden, world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, state.contains(Properties.WATERLOGGED) ? state.with(Properties.WATERLOGGED, false) : state);
        world.setBlockState(pos, state.getFluidState().getBlockState(), Block.NOTIFY_ALL);
        world.spawnEntity(fallingBlockEntity);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState aboveState = world.getBlockState(pos.up());
        BlockState belowState = world.getBlockState(pos.down());
        if (!(aboveState.isIn(BlockTags.LEAVES) || aboveState.isIn(BlockTags.LOGS) || aboveState.isOf(OFObjects.OVERWEIGHT_APPLE_STEM) || aboveState.isOf(OFObjects.OVERWEIGHT_GOLDEN_APPLE_STEM)) && isFree(belowState) && pos.getY() >= world.getBottomY()) {
            spawnFallingBlock(state, world, pos);
        }

    }
}
