package net.orcinus.overweightfarming.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.orcinus.overweightfarming.OverweightFarming;

public class OFBlockTags {

    public static final TagKey<Block> OVERWEIGHT_OBSTACLES = register("overweight_obstacles");
    public static final TagKey<Block> OVERWEIGHT_APPLE_LEAVES = register("overweight_apple_leaves");

    public static TagKey<Block> register(String name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(OverweightFarming.MODID, name));
    }

}
