package net.orcinus.overweightfarming.init;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.orcinus.overweightfarming.OverweightFarming;

public class OFBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_APPLE_TREES = createKey("add_apple_trees");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        context.register(ADD_APPLE_TREES, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(OFBiomeTags.APPLE_TREE_GENERATES),
                HolderSet.direct(placedFeatures.getOrThrow(OFPlacedFeatures.APPLE_OAK_TREES)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, OverweightFarming.id(name));
    }

}
