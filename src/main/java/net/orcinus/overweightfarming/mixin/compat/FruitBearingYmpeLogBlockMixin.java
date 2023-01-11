package net.orcinus.overweightfarming.mixin.compat;

import moriyashiine.aylyth.common.block.FruitBearingYmpeLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(FruitBearingYmpeLogBlock.class)
public class FruitBearingYmpeLogBlockMixin {

    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), cancellable = true)
    private void of$f(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci){
        float chance =  0.001F;
        if (random.nextFloat() < chance && state.get(FruitBearingYmpeLogBlock.AGE) == 3) {
            List<BlockPos> blockPosList = new ArrayList<>();
            for(Direction dir : Direction.Type.HORIZONTAL){
                if(world.isAir(pos.offset(dir))){
                    blockPosList.add(pos.offset(dir));
                }
            }
            if(!blockPosList.isEmpty()){
                Collections.shuffle(blockPosList);
                world.setBlockState(pos, state.with(FruitBearingYmpeLogBlock.AGE, 0));
                world.setBlockState(blockPosList.get(0), OFObjects.OVERWEIGHT_YMPE.getDefaultState());
                ci.cancel();
            }

        }
    }
}
