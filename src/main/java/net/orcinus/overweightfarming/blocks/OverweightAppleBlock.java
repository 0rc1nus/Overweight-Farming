package net.orcinus.overweightfarming.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.orcinus.overweightfarming.blocks.blockentities.OverweightAppleBlockEntity;
import net.orcinus.overweightfarming.entities.OverweightAppleFallingBlockEntity;
import net.orcinus.overweightfarming.init.OFBlockEntityTypes;
import net.orcinus.overweightfarming.init.OFBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class OverweightAppleBlock extends CropFullBlock implements Fallable, EntityBlock {
    private final boolean isGolden;

    public OverweightAppleBlock(boolean isGolden, Block stemBlock, Properties properties) {
        super(stemBlock, properties);
        this.isGolden = isGolden;
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState p_53236_, boolean p_53237_) {
        world.scheduleTick(pos, this, 2);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState p_51034_, LevelAccessor world, BlockPos blockPos, BlockPos p_51037_) {
        world.scheduleTick(blockPos, this, 2);
        return super.updateShape(state, direction, p_51034_, world, blockPos, p_51037_);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, Random p_221127_) {
        BlockState aboveState = world.getBlockState(pos.above());
        BlockState belowState = world.getBlockState(pos.below());
        if (!(aboveState.is(BlockTags.LEAVES) || aboveState.is(BlockTags.LOGS) || aboveState.is(OFBlocks.OVERWEIGHT_APPLE_STEM.get()) || aboveState.is(OFBlocks.OVERWEIGHT_GOLDEN_APPLE_STEM.get())) && isFree(belowState) && pos.getY() >= world.getMinBuildHeight()) {
            this.spawnFallingBlock(state, world, pos);
        }
    }

    public void spawnFallingBlock(BlockState state, Level world, BlockPos pos) {
        OverweightAppleFallingBlockEntity fallingblockentity = new OverweightAppleFallingBlockEntity(this.isGolden, world, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, false) : state);
        world.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
        world.addFreshEntity(fallingblockentity);
    }

    public static boolean isFree(BlockState state) {
        Material material = state.getMaterial();
        return state.isAir() || state.is(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
    }

    @Override
    public boolean shouldGrowRoots() {
        return false;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OverweightAppleBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return !world.isClientSide ? createTickerHelper(type, OFBlockEntityTypes.OVERWEIGHT_APPLE.get(), OverweightAppleBlockEntity::tick) : null;
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityType<E> p_152134_, BlockEntityTicker<? super E> ticker) {
        return p_152134_ == p_152133_ ? (BlockEntityTicker<A>)ticker : null;
    }
}