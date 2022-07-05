package net.orcinus.overweightfarming.common.registry;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.orcinus.overweightfarming.common.util.ITreeFeatureConfig;
import net.orcinus.overweightfarming.common.worldgen.AppleTreeDecorator;

public class OFWorldGenerators {
    public static final TreeDecoratorType<AppleTreeDecorator> APPLE = new TreeDecoratorType<>(AppleTreeDecorator.CODEC);


    private static void addAppleTrees(ConfiguredFeature<?, ?> object) {
        if (object.feature() == Feature.TREE && object.config() instanceof TreeFeatureConfig config) {
            if (config.decorators.stream().anyMatch(decorator -> decorator instanceof ITreeFeatureConfig) || !(config.trunkProvider instanceof SimpleBlockStateProvider)) {
                return;
            }
            TreeDecorator decorator = new AppleTreeDecorator(0.005F, 0.1F);
            ((ITreeFeatureConfig) config).addDecorator(decorator);
        }
    }

    public static void init(){

        RegistryEntryAddedCallback.event(BuiltinRegistries.CONFIGURED_FEATURE).register((rawId, id, object) -> {
            addAppleTrees(object);
        });


        DynamicRegistrySetupCallback.EVENT.register(registryManager -> {
            Registry<ConfiguredFeature<?, ?>> registry = registryManager.getManaged(Registry.CONFIGURED_FEATURE_KEY);
            RegistryEntryAddedCallback.event(registry).register((rawId, id, object) -> {
                addAppleTrees(object);
            });
        });
    }
}
