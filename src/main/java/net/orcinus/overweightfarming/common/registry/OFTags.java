package net.orcinus.overweightfarming.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.OverweightFarming;

public class OFTags {
    public static final TagKey<Block> OVERWEIGHT_OBSTACLES = TagKey.of(RegistryKeys.BLOCK, new Identifier(OverweightFarming.MODID, "overweight_obstacles"));
    public static final TagKey<Block> OVERWEIGHT_COMPAT = TagKey.of(RegistryKeys.BLOCK, new Identifier(OverweightFarming.MODID, "overweight_compat"));
    public static final TagKey<Block> OVERWEIGHT_APPLE_LEAVES = TagKey.of(RegistryKeys.BLOCK, new Identifier(OverweightFarming.MODID, "overweight_apple_leaves"));

    public static final TagKey<Item> OVERWEIGHT_HARVESTABLES = TagKey.of(RegistryKeys.ITEM, new Identifier(OverweightFarming.MODID, "overweight_harvestables"));
}
