package com.binome.overweightfarming.registry;

import com.binome.overweightfarming.OverweightFarming;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class OFItemTags {
    public static final TagKey<Item> OVERWEIGHT_HARVESTABLES = TagKey.of(Registry.ITEM_KEY, new Identifier(OverweightFarming.MODID, "overweight_harvestables"));
}
