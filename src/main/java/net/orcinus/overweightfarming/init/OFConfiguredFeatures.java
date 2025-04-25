package net.orcinus.overweightfarming.init;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.world.gen.decorators.AppleTreeDecorator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.OptionalInt;

public class OFConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_OAK = createKey("apple_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_OAK = createKey("fancy_apple_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_OAK_BEES = createKey("apple_oak_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_OAK_BEES = createKey("fancy_apple_oak_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_OAK_TREES = createKey("apple_oak_trees");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        AppleTreeDecorator appleTreeDecorator = new AppleTreeDecorator();
        AppleTreeDecorator tallAppleTreeDecorator = new AppleTreeDecorator(6);
        BeehiveDecorator beehiveDecorator = new BeehiveDecorator(0.05F);

        FeatureUtils.register(context, APPLE_OAK, Feature.TREE, oak().decorators(List.of(appleTreeDecorator)).build());
        FeatureUtils.register(context, FANCY_APPLE_OAK, Feature.TREE, createFancyOak().decorators(List.of(tallAppleTreeDecorator)).build());
        FeatureUtils.register(context, APPLE_OAK_BEES, Feature.TREE, oak().decorators(List.of(beehiveDecorator, appleTreeDecorator)).build());
        FeatureUtils.register(context, FANCY_APPLE_OAK_BEES, Feature.TREE, createFancyOak().decorators(List.of(beehiveDecorator, tallAppleTreeDecorator)).build());

        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        FeatureUtils.register(
                context,
                APPLE_OAK_TREES,
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(placedFeatures.getOrThrow(OFPlacedFeatures.APPLE_OAK_CHECKED), 0.2F),
                        new WeightedPlacedFeature(placedFeatures.getOrThrow(OFPlacedFeatures.APPLE_OAK_BEES_CHECKED), 0.1F),
                    new WeightedPlacedFeature(placedFeatures.getOrThrow(OFPlacedFeatures.FANCY_APPLE_OAK_BEES_CHECKED), 0.4F)
                ), placedFeatures.getOrThrow(OFPlacedFeatures.FANCY_APPLE_OAK_CHECKED))
        );
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyOak() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new FancyTrunkPlacer(3, 11, 0),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
        ).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder oak() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, OverweightFarming.id(name));
    }

}
