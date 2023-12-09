package net.orcinus.overweightfarming.common.blocks;

import net.minecraft.block.*;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.orcinus.overweightfarming.common.registry.OFObjects;

import java.util.Iterator;

public class AlliumBushBlock extends TallFlowerBlock {

    public AlliumBushBlock(Settings settings) {
        super(settings);
    }


    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {

        if (!super.canPlaceAt(state, world, pos) ) {
            Iterator<Direction> var4 = Direction.Type.HORIZONTAL.iterator();
            Direction direction;
            BlockState blockState;
            do {
                if (!var4.hasNext()) {
                    BlockState blockState2 = world.getBlockState(pos.down());
                    return (blockState2.isOf(OFObjects.OVERWEIGHT_ONION) && !world.getBlockState(pos.up()).isLiquid());
                }
                direction = var4.next();
                blockState = world.getBlockState(pos.offset(direction));
            } while (!blockState.isSolid() && !world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA));

            return false;
        } else {
            return true;
        }
    }

}