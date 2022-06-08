package net.orcinus.overweightfarming.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFItems;
import net.orcinus.overweightfarming.init.OFParticleTypes;

public class PeeledMelonBlock extends Block {
    private final SeedState seedState;

    public PeeledMelonBlock(SeedState seedState, Properties properties) {
        super(properties);
        this.seedState = seedState;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        for(int i = 0; i < random.nextInt(1) + 1; ++i) {
            this.trySpawnDripParticles(world, pos, state);
        }
    }

    private void trySpawnDripParticles(Level world, BlockPos pos, BlockState state) {
        if (state.getFluidState().isEmpty() && !(world.random.nextFloat() < 0.3F)) {
            VoxelShape voxelshape = state.getCollisionShape(world, pos);
            double d0 = voxelshape.max(Direction.Axis.Y);
            if (d0 >= 1.0D && !state.is(BlockTags.IMPERMEABLE)) {
                double d1 = voxelshape.min(Direction.Axis.Y);
                if (d1 > 0.0D) {
                    this.spawnParticle(world, pos, voxelshape, (double)pos.getY() + d1 - 0.05D);
                } else {
                    BlockPos blockpos = pos.below();
                    BlockState blockstate = world.getBlockState(blockpos);
                    VoxelShape voxelshape1 = blockstate.getCollisionShape(world, blockpos);
                    double d2 = voxelshape1.max(Direction.Axis.Y);
                    if ((d2 < 1.0D || !blockstate.isCollisionShapeFullBlock(world, blockpos)) && blockstate.getFluidState().isEmpty()) {
                        this.spawnParticle(world, pos, voxelshape, (double)pos.getY() - 0.05D);
                    }
                }
            }

        }
    }

    private void spawnParticle(Level world, BlockPos pos, VoxelShape p_49615_, double y) {
        this.spawnFluidParticle(world, (double)pos.getX() + p_49615_.min(Direction.Axis.X), (double)pos.getX() + p_49615_.max(Direction.Axis.X), (double)pos.getZ() + p_49615_.min(Direction.Axis.Z), (double)pos.getZ() + p_49615_.max(Direction.Axis.Z), y);
    }

    private void spawnFluidParticle(Level world, double minX, double maxX, double minZ, double maxZ, double y) {
        world.addParticle(OFParticleTypes.DRIPPING_MELON.get(), Mth.lerp(world.random.nextDouble(), minX, maxX), y, Mth.lerp(world.random.nextDouble(), minZ, maxZ), 0.0D, 0.0D, 0.0D);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
        ItemStack stack = player.getItemInHand(hand);
        if (seedState != SeedState.SEEDLESS) {
            Block finalBlock = this.seedState == SeedState.SEEDED ? OFBlocks.HALF_SEEDED_PEELED_MELON.get() : OFBlocks.SEEDLESS_PEELED_MELON.get();
            popResource(world, pos, new ItemStack(Items.MELON_SEEDS));
            world.setBlockAndUpdate(pos, finalBlock.defaultBlockState());
            world.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, 1.4F);
            return InteractionResult.sidedSuccess(world.isClientSide());
        } else if (stack.getItem() == Items.GLASS_BOTTLE) {
            stack.shrink(1);
            if (stack.isEmpty()) {
                player.setItemInHand(hand, new ItemStack(OFItems.MELON_JUICE.get()));
            } else if (!player.getInventory().add(new ItemStack(OFItems.MELON_JUICE.get()))) {
                player.drop(new ItemStack(OFItems.MELON_JUICE.get()), false);
            }
            world.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
            if (!world.isClientSide()) {
                if (world.getRandom().nextInt(7) == 0) {
                    world.destroyBlock(pos, false);
                }
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
            }
            return InteractionResult.sidedSuccess(world.isClientSide());
        } else {
            return super.use(state, world, pos, player, hand, p_60508_);
        }
    }

    public enum SeedState {
        SEEDED,
        HALF_SEEDED,
        SEEDLESS,
    }
}
