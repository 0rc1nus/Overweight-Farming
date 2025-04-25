package net.orcinus.overweightfarming.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.orcinus.overweightfarming.OverweightFarming;
import org.jetbrains.annotations.NotNull;

public class OFBiomeTags {

    public static final TagKey<Biome> APPLE_TREE_GENERATES = createKey("apple_tree_generates");

    private static TagKey<Biome> createKey(String name) {
        return TagKey.create(Registries.BIOME, OverweightFarming.id(name));
    }

}
