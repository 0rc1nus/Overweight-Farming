package net.orcinus.overweightfarming.mixin;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.*;
import net.orcinus.overweightfarming.common.worldgen.AppleTreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfiguredFeatures.class)
public class ConfiguredFeaturesMixin {

    @Inject(at = @At("HEAD"), method = "register(Lnet/minecraft/registry/Registerable;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/world/gen/feature/Feature;Lnet/minecraft/world/gen/feature/FeatureConfig;)V")
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config, CallbackInfo ci) {
        if (config instanceof TreeFeatureConfig treeConfiguration) {
            treeConfiguration.decorators.add(new AppleTreeDecorator(1.0E-6F, 0.05f));
        }
    }
}