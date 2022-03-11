package com.binome.overweightfarming.init;

import com.binome.overweightfarming.OverweightFarming;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

public class OFItemTags {

    public static final Tags.IOptionalNamedTag<Item> OVERWEIGHT_HARVESTABLES = register("overweight_harvestables");
    public static final Tags.IOptionalNamedTag<Item> POUCH_SEED_ITEMS = register("pouch_seed_items");

    public static Tags.IOptionalNamedTag<Item> register(String name) {
        return ItemTags.createOptional(new ResourceLocation(OverweightFarming.MODID, name));
    }

}
