package net.orcinus.overweightfarming.common.blockentities;


import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.orcinus.overweightfarming.common.blocks.OverweightAppleBlock;
import net.orcinus.overweightfarming.common.registry.OFBlockEntityTypes;

import java.util.Optional;

public class OverweightAppleBlockEntity extends BlockEntity {
    public OverweightAppleBlockEntity(BlockPos pos, BlockState state) {
        super(OFBlockEntityTypes.OVERWEIGHT_APPLE_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, OverweightAppleBlockEntity te) {
        boolean flag = world.getRandom().nextInt(20) == 0;
        boolean gate = true;
        if (flag) {
            for (int i = 1; i < world.getHeight(); i++) {
                if (!world.getBlockState(pos.down(i)).isAir()) {
                    break;
                }
                Optional<PlayerEntity> playerEntityOptional = world.getNonSpectatingEntities(PlayerEntity.class, new Box(pos.down(i))).stream().findAny();
                if (playerEntityOptional.isPresent() && gate) {
                    ((OverweightAppleBlock) state.getBlock()).spawnFallingBlock(state, world, pos);
                    gate = false;
                }
            }
        }
    }
}