package com.binome.overweightfarming.init;

import com.binome.overweightfarming.OverweightFarming;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class OFItemTags {

    public static final TagKey<Item> OVERWEIGHT_HARVESTABLES = register("overweight_harvestables");

    public static TagKey<Item> register(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(OverweightFarming.MODID, name));
    }

}
