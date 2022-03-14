package com.binome.overweightfarming.init;

import com.binome.overweightfarming.OverweightFarming;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class OFItemTags {
    public static final Tag<Item> OVERWEIGHT_HARVESTABLES = TagFactory.ITEM.create(new Identifier(OverweightFarming.MODID, "overweight_harvestables"));
    public static final Tag<Item> POUCH_SEED_ITEMS = TagFactory.ITEM.create(new Identifier(OverweightFarming.MODID, "pouch_seed_items"));

}
