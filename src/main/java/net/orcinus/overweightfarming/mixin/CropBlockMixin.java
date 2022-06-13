package net.orcinus.overweightfarming.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.orcinus.overweightfarming.registry.OFTags;
import net.orcinus.overweightfarming.util.OverweightGrowthManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(CropBlock.class)
public class CropBlockMixin {
    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), cancellable = true)
    private void OF$randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci){
        OverweightGrowthManager manager = new OverweightGrowthManager(random);
        for (Block cropBlock : manager.getOverweightMap().keySet()) {
            if (state.isOf(cropBlock)) {
                boolean flag = state.contains(CropBlock.AGE) && state.get(CropBlock.AGE) < 7 && state.get(CropBlock.AGE) == 3;
                boolean flag1 = state.contains(CocoaBlock.AGE) && state.get(CocoaBlock.AGE) == 1;
                boolean flag2 = (state.getBlock() instanceof BeetrootsBlock || state.getBlock() instanceof NetherWartBlock) && state.contains(Properties.AGE_3) &&
                        state.get(Properties.AGE_3) < 3 && state.get(Properties.AGE_3) > 1;
                boolean flag5 = state.isIn(OFTags.OVERWEIGHT_COMPAT) && state.contains(Properties.AGE_3) && state.get(Properties.AGE_3) < 3 && state.get(Properties.AGE_3) > 1;
                boolean flag3 = FabricLoader.getInstance().isModLoaded("orcinus") && state.getBlock() == manager.getCompatBlock("hedgehog", "kiwi_vines") && state.get(Properties.BERRIES);


                if (flag || flag1 || flag2 || flag5 || flag3) {
                    float chance = world.isNight() && world.getMoonPhase() == 0 ? 0.0010538863F : 3.4290552E-4F;
                    if (random.nextFloat() < chance) {
                        manager.growOverweightCrops(world, pos, state, random);
                        ci.cancel();
                    }
                }
            }
        }
    }
}
