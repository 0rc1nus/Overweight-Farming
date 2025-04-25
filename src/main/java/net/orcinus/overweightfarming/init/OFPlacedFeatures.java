package net.orcinus.overweightfarming.init;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.orcinus.overweightfarming.OverweightFarming;

public class OFPlacedFeatures {

    public static final ResourceKey<PlacedFeature> APPLE_OAK_CHECKED = createKey("apple_oak_checked");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_OAK_CHECKED = createKey("fancy_apple_oak_checked");
    public static final ResourceKey<PlacedFeature> APPLE_OAK_BEES_CHECKED = createKey("apple_oak_bees_checked");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_OAK_BEES_CHECKED = createKey("fancy_apple_oak_bees_checked");
    public static final ResourceKey<PlacedFeature> APPLE_OAK_TREES = createKey("apple_oak_trees");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context, APPLE_OAK_CHECKED, configuredLookup.getOrThrow(OFConfiguredFeatures.APPLE_OAK), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
        PlacementUtils.register(context, FANCY_APPLE_OAK_CHECKED, configuredLookup.getOrThrow(OFConfiguredFeatures.FANCY_APPLE_OAK), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
        PlacementUtils.register(context, APPLE_OAK_BEES_CHECKED, configuredLookup.getOrThrow(OFConfiguredFeatures.APPLE_OAK_BEES), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
        PlacementUtils.register(context, FANCY_APPLE_OAK_BEES_CHECKED, configuredLookup.getOrThrow(OFConfiguredFeatures.FANCY_APPLE_OAK_BEES), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));

        PlacementUtils.register(
                context,
                APPLE_OAK_TREES,
                configuredLookup.getOrThrow(OFConfiguredFeatures.APPLE_OAK_TREES),
                RarityFilter.onAverageOnceEvery(40),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome()
        );
    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, OverweightFarming.id(name));
    }

}
