package net.orcinus.overweightfarming.mixin.compat;

import moriyashiine.aylyth.common.block.PomegranateLeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static moriyashiine.aylyth.common.block.PomegranateLeavesBlock.FRUITING;

@Mixin(PomegranateLeavesBlock.class)
public class PomegranateLeavesBlockMixin {

    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"))
    private void of$f(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci){
        float chance =  0.001F;
        if (random.nextFloat() < chance && world.isAir(pos.down()) && state.get(FRUITING) == 2) {
            world.setBlockState(pos, state.with(FRUITING, 0));
            world.setBlockState(pos.down(), OFObjects.OVERWEIGHT_POMME.getDefaultState());
        }
    }
}
