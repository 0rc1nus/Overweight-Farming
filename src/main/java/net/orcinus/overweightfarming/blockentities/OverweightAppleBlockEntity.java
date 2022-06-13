package net.orcinus.overweightfarming.blockentities;


import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.orcinus.overweightfarming.blocks.OverweightAppleBlock;
import net.orcinus.overweightfarming.registry.OFBlockEntityTypes;

public class OverweightAppleBlockEntity extends BlockEntity {
    public OverweightAppleBlockEntity(BlockPos pos, BlockState state) {
        super(OFBlockEntityTypes.OVERWEIGHT_APPLE_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, OverweightAppleBlockEntity overweightAppleBlockEntity) {
        if (world != null && !world.isClient()) {
            boolean flag = world.getRandom().nextInt(20) == 0;
            if (flag) {
                for (int i = 1; i < world.getHeight(); i++) {
                    if (!world.getBlockState(pos.down(i)).isAir()) {
                        break;
                    }
                    for (PlayerEntity player : world.getNonSpectatingEntities(PlayerEntity.class, new Box(pos.down(i)))) {
                        if (player != null) {
                            ((OverweightAppleBlock)state.getBlock()).spawnFallingBlock(state, world, pos);
                        }
                    }
                }
            }
        }
    }
}