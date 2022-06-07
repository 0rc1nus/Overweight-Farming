package net.orcinus.overweightfarming.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.orcinus.overweightfarming.registry.OFObjects;
import net.orcinus.overweightfarming.registry.OFParticleTypes;


public class PeeledMelonBlock extends Block {
    private final SeedState seedState;
    public PeeledMelonBlock(SeedState seedState, Settings properties) {
        super(properties);
        this.seedState = seedState;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        for(int i = 0; i < random.nextInt(1) + 1; ++i) {
            this.trySpawnDripParticles(world, pos, state);
        }
    }

    private void trySpawnDripParticles(World world, BlockPos pos, BlockState state) {
        if (state.getFluidState().isEmpty() && !(world.random.nextFloat() < 0.3F)) {
            VoxelShape voxelshape = state.getCollisionShape(world, pos);
            double d0 = voxelshape.getMax(Direction.Axis.Y);
            if (d0 >= 1.0D && !state.isIn(BlockTags.IMPERMEABLE)) {
                double d1 = voxelshape.getMin(Direction.Axis.Y);
                if (d1 > 0.0D) {
                    this.spawnParticle(world, pos, voxelshape, (double)pos.getY() + d1 - 0.05D);
                } else {
                    BlockPos blockpos = pos.down();
                    BlockState blockstate = world.getBlockState(blockpos);
                    VoxelShape voxelshape1 = blockstate.getCollisionShape(world, blockpos);
                    double d2 = voxelshape1.getMax(Direction.Axis.Y);
                    if ((d2 < 1.0D || !blockstate.isFullCube(world, blockpos)) && blockstate.getFluidState().isEmpty()) {
                        this.spawnParticle(world, pos, voxelshape, (double)pos.getY() - 0.05D);
                    }
                }
            }

        }
    }

    private void spawnParticle(World world, BlockPos pos, VoxelShape shape, double y) {
        this.spawnFluidParticle(world, (double)pos.getX() + shape.getMin(Direction.Axis.X), (double)pos.getX() + shape.getMax(Direction.Axis.X), (double)pos.getZ() + shape.getMin(Direction.Axis.Z), (double)pos.getZ() + shape.getMax(Direction.Axis.Z), y);
    }

    private void spawnFluidParticle(World world, double minX, double maxX, double minZ, double maxZ, double y) {
        world.addParticle(OFParticleTypes.DRIPPING_MELON, MathHelper.lerp(world.random.nextDouble(), minX, maxX), y, MathHelper.lerp(world.random.nextDouble(), minZ, maxZ), 0.0D, 0.0D, 0.0D);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (seedState != SeedState.SEEDLESS) {
            Block finalBlock = this.seedState == SeedState.SEEDED ? OFObjects.HALF_SEEDED_PEELED_MELON : OFObjects.SEEDLESS_PEELED_MELON;
            dropStack(world, pos, new ItemStack(Items.MELON_SEEDS));
            world.setBlockState(pos, finalBlock.getDefaultState());
            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1.0F, 1.4F);
            return ActionResult.success(world.isClient());
        } else if (stack.getItem() == Items.GLASS_BOTTLE) {
            stack.decrement(1);
            if (stack.isEmpty()) {
                player.setStackInHand(hand, new ItemStack(OFObjects.MELON_JUICE));
            } else if (!player.getInventory().insertStack(new ItemStack(OFObjects.MELON_JUICE))) {
                player.dropItem(new ItemStack(OFObjects.MELON_JUICE), false);
            }
            world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            if (!world.isClient()) {
                if (world.getRandom().nextInt(7) == 0) {
                    world.breakBlock(pos, false);
                }
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            }
            return ActionResult.success(world.isClient());
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }


    public enum SeedState {
        SEEDED,
        HALF_SEEDED,
        SEEDLESS,
    }
}
