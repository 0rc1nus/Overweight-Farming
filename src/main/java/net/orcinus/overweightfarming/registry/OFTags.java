package net.orcinus.overweightfarming.registry;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.OverweightFarming;

public class OFTags {
    public static final TagKey<Block> OVERWEIGHT_OBSTACLES = TagKey.of(Registry.BLOCK_KEY, new Identifier(OverweightFarming.MODID, "overweight_obstacles"));

}
