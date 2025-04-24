package net.orcinus.overweightfarming.mixin;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.orcinus.overweightfarming.world.gen.decorators.AppleTreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FeatureUtils.class)
public class FeatureUtilsMixin {

    @Inject(at = @At("HEAD"), method = "register(Lnet/minecraft/data/worldgen/BootstrapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/levelgen/feature/Feature;Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;)V")
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> p_256315_, ResourceKey<ConfiguredFeature<?, ?>> p_255983_, F p_255949_, FC featureConfig, CallbackInfo ci) {
        if (featureConfig instanceof TreeConfiguration treeConfiguration) {
            treeConfiguration.decorators.add(new AppleTreeDecorator(1.0E-6F, 0.05f));
        }
    }

}
