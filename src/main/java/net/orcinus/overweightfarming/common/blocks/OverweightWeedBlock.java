package net.orcinus.overweightfarming.common.blocks;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;
import net.orcinus.overweightfarming.common.networking.s2c.S2CFluffPacket;
import net.orcinus.overweightfarming.common.registry.OFParticleTypes;
import net.orcinus.overweightfarming.common.util.TripleBlockHalf;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class OverweightWeedBlock extends CropFullBlock {
    public static final EnumProperty<TripleBlockHalf> HALF;
    public static final BooleanProperty FLUFF;

    static {
        HALF = TripleBlockHalf.TRIPLE_BLOCK_HALF;
        FLUFF = BooleanProperty.of("fluff");
    }

    public OverweightWeedBlock(AbstractBlock.Settings settings) {
        super(null, settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(HALF, TripleBlockHalf.LOWER).with(FLUFF, false));
    }

    public static BlockState withWaterloggedState(WorldView world, BlockPos pos, BlockState state) {
        return state.contains(Properties.WATERLOGGED) ? state.with(Properties.WATERLOGGED, world.isWater(pos)) : state;
    }

    /**
     * Destroys a bottom half of a tall double block (such as a plant or a door)
     * without dropping an item when broken in creative.
     *
     * @see Block#onBreak(World, BlockPos, BlockState, PlayerEntity)
     */
    protected static void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        TripleBlockHalf tripleBlockHalf = state.get(HALF);
        if (tripleBlockHalf == TripleBlockHalf.UPPER || tripleBlockHalf == TripleBlockHalf.MIDDLE) {
            BlockPos blockPos = pos.down();
            BlockState blockState = world.getBlockState(blockPos);
            if (state.contains(HALF) && blockState.contains(HALF) && blockState.get(HALF) == TripleBlockHalf.LOWER || blockState.get(HALF) == TripleBlockHalf.MIDDLE) {
                BlockState blockState2 = blockState.contains(Properties.WATERLOGGED) && blockState.get(Properties.WATERLOGGED) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                world.setBlockState(blockPos, blockState2, Block.NOTIFY_ALL | Block.SKIP_DROPS);
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
            }
        }
    }

    public static void placeTallAt(WorldAccess world, BlockState state, BlockPos pos, int flags) {
        BlockPos blockPos = pos.up();
        world.setBlockState(pos, withWaterloggedState(world, pos, state.with(HALF, TripleBlockHalf.LOWER)), flags);
        world.setBlockState(blockPos, withWaterloggedState(world, blockPos, state.with(HALF, TripleBlockHalf.MIDDLE)), flags);
        world.setBlockState(blockPos.up(), withWaterloggedState(world, blockPos.up(), state.with(HALF, TripleBlockHalf.UPPER)), flags);
    }

    public static void placeShortAt(WorldAccess world, BlockState state, BlockPos pos, int flags) {
        BlockPos blockPos = pos.up();
        world.setBlockState(pos, withWaterloggedState(world, pos, state.with(HALF, TripleBlockHalf.LOWER)), flags);
        world.setBlockState(blockPos, withWaterloggedState(world, blockPos.up(), state.with(HALF, TripleBlockHalf.UPPER)), flags);
    }

    public boolean isMature(BlockState state) {
        return state.contains(FLUFF) && state.get(FLUFF);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !this.isMature(state);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.contains(HALF) && state.get(HALF) == TripleBlockHalf.UPPER && state.contains(FLUFF) && !state.get(FLUFF)) {
            if (world.getBaseLightLevel(pos, 0) >= 9) {
                if (random.nextInt((int) (20.0F)) == 0) {
                    world.setBlockState(pos, state.with(FLUFF, true), Block.NOTIFY_LISTENERS);
                }
            }
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.contains(FLUFF) && state.get(FLUFF)) {
            entity.slowMovement(state, new Vec3d(0.5D, 0.5D, 0.5D));
            if (!world.isClient) {
                BlockPos blockPosNormalized = pos.add(new Vec3i(0.5F, 0.5F, 0.5F));
                double distance = blockPosNormalized.getSquaredDistance(entity.getPos());
                if (distance < 0.75D) {
                    summonFluffParticle( world, pos, entity);
                    world.breakBlock(pos, false);
                }
            }
        }
    }



    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        world.addParticle(
                OFParticleTypes.FLUFF,
                pos.getX() + 0.5 + world.getRandom().nextGaussian() * 5,
                pos.getY() + 0.5 + world.getRandom().nextGaussian() * 5,
                pos.getZ() + 0.5 + world.getRandom().nextGaussian() * 5,
                0f, 0f, 0f);
        super.onBroken(world, pos, state);
    }

    private void summonFluffParticle(World world, BlockPos pos, Entity entity){
        if(world instanceof ServerWorld serverWorld && entity instanceof PlayerEntity player){
            PlayerLookup.tracking(serverWorld, pos).forEach(trackingPlayer -> {
                S2CFluffPacket.send(pos, player);
            });
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getMainHandStack().getItem() instanceof BoneMealItem && state.contains(FLUFF) && state.get(FLUFF)) {
            if (!player.isCreative()) {
                player.getMainHandStack().decrement(1);
            }
            spreadWeed(world, state, pos);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    /**
     * Spreads Weeds around the original plant, in a 7x7x7
     *
     * @param world
     * @param state
     * @param pos
     */
    private void spreadWeed(World world, BlockState state, BlockPos pos) {
        List<BlockPos> listPos = collectBlocks(world, pos.down(3));
        if (!listPos.isEmpty()) {
            Collections.shuffle(listPos);
            for (int i = 1; i < world.getRandom().nextInt(4); i++) {
                if (listPos.size() >= i) {
                    if (world.getBlockState(listPos.get(i).up(3)).isAir() && world.getRandom().nextBoolean()) {
                        placeTallAt(world, state, listPos.get(i), 2);
                    } else if (world.getBlockState(listPos.get(i).up(2)).isAir()) {
                        placeShortAt(world, state, listPos.get(i), 2);
                    }
                    if (world.isClient()) {
                        world.addBlockBreakParticles(listPos.get(i).up(), world.getBlockState(pos.down()));
                        world.playSound((double) listPos.get(i).up().getX() + 0.5D, (double) listPos.get(i).up().getY() + 0.5D, (double) listPos.get(i).up().getZ() + 0.5D, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 0.5F + world.getRandom().nextFloat(), world.getRandom().nextFloat() * 0.7F + 0.6F, false);
                    }
                }
            }
        }

    }

    /**
     * Collects all block in a 7x7x7 of type FARMLAND etc
     *
     * @param world
     * @param pos
     * @return List of blocks available for weed spread
     */
    private List<BlockPos> collectBlocks(World world, BlockPos pos) {
        List<BlockPos> listPos = new ArrayList<>();
        for (int x = -3; x < 3; ++x) {
            for (int y = -3; y < 3; ++y) {
                for (int z = -3; z < 3; ++z) {
                    if (world.getBlockState(pos.add(x, y, z)).isOf(Blocks.FARMLAND) || world.getBlockState(pos.add(x, y, z)).isIn(BlockTags.DIRT) && !world.getBlockState(pos.up()).isOf(this)) {
                        listPos.add(pos.add(x, y, z));
                    }
                }
            }
        }
        return listPos;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        return blockPos.getY() < world.getTopY() - 2 && world.getBlockState(blockPos.up()).canReplace(ctx) ? super.getPlacementState(ctx) : null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockPos blockPos = pos.up();
        BlockPos topPos = blockPos.up();
        world.setBlockState(blockPos, withWaterloggedState(world, blockPos, this.getDefaultState().with(HALF, TripleBlockHalf.MIDDLE)), Block.NOTIFY_ALL);
        world.setBlockState(topPos, withWaterloggedState(world, topPos, this.getDefaultState().with(HALF, TripleBlockHalf.UPPER)), Block.NOTIFY_ALL);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());
        return blockState.isIn(BlockTags.DIRT) || blockState.isOf(Blocks.FARMLAND) || blockState.isOf(this);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            if (player.isCreative()) {
                onBreakInCreative(world, pos, state, player);
            } else {
                dropStacks(state, world, pos, null, player, player.getMainHandStack());
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, Blocks.AIR.getDefaultState(), blockEntity, stack);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HALF, FLUFF);
    }

    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XZ;
    }

    public long getRenderingSeed(BlockState state, BlockPos pos) {
        return MathHelper.hashCode(pos.getX(), pos.down(state.get(HALF) == TripleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }
}
