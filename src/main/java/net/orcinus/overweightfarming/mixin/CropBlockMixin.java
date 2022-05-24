package net.orcinus.overweightfarming.mixin;

import net.minecraft.block.BeetrootsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.orcinus.overweightfarming.util.OvergrowthHandler;
import net.orcinus.overweightfarming.util.OverweightGrowthManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(CropBlock.class)
public class CropBlockMixin {
    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), cancellable = true)
    private void OF$randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci){
        OverweightGrowthManager manager = new OverweightGrowthManager(random);
        for (Block cropBlock : OvergrowthHandler.CROPS_TO_OVERGROWN.keySet()) {
            if (state.isOf(cropBlock)) {
                boolean flag = state.contains(CropBlock.AGE) && state.get(CropBlock.AGE) < 7 && state.get(CropBlock.AGE) == 3;
                boolean flag1 = state.contains(CocoaBlock.AGE) && state.get(CocoaBlock.AGE) == 1;
                boolean flag2 = state.contains(BeetrootsBlock.AGE) && state.get(BeetrootsBlock.AGE) < BeetrootsBlock.MAX_AGE && state.get(BeetrootsBlock.AGE) > 1;
                if (flag || flag1 || flag2) {
                    float chance = world.isNight() && world.getMoonPhase() == 0 ? 0.0010538863F : 3.4290552E-4F;
                    if (random.nextFloat() < chance) {
                        OvergrowthHandler.growOverweightCrop(random, state, world, pos, cropBlock);
//                        manager.growOverweightCrops(world, pos, state, random);
                        ci.cancel();
                    }
                }
            }
        }
    }
}
