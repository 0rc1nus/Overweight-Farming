package net.orcinus.overweightfarming.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.orcinus.overweightfarming.blocks.OverweightAppleBlock;
import net.orcinus.overweightfarming.init.OFBlockEntityTypes;

public class OverweightAppleBlockEntity extends BlockEntity {

    public OverweightAppleBlockEntity(BlockPos pos, BlockState state) {
        super(OFBlockEntityTypes.OVERWEIGHT_APPLE.get(), pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, OverweightAppleBlockEntity te) {
        boolean flag = world.getRandom().nextInt(20) == 0;
        boolean gate = true;
        if (flag) {
            for (int i = 1; i < world.getHeight(); i++) {
                if (!world.getBlockState(pos.below(i)).isAir()) { break; }
                for (Player player : world.getEntitiesOfClass(Player.class, new AABB(pos.below(i)))) {
                    if (player != null && gate) {
                        ((OverweightAppleBlock)state.getBlock()).spawnFallingBlock(state, world, pos);
                        gate = false;
                    }
                }
            }
        }
    }

}
