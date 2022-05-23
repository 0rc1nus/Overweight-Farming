package net.orcinus.overweightfarming.init;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.OverweightFarming;

public class OFItemTags {

    public static final TagKey<Item> OVERWEIGHT_HARVESTABLES = TagKey.of(Registry.ITEM_KEY, new Identifier(OverweightFarming.MODID, "overweight_harvestables"));

}
