package net.orcinus.overweightfarming.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFItems;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class OFDataMapsProvider extends DataMapProvider {

    public OFDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        Map<ItemLike, Float> compostables = Util.make(Maps.newHashMap(), map -> {
            map.put(OFBlocks.OVERWEIGHT_BEETROOT.get(), 1.0F);
            map.put(OFBlocks.OVERWEIGHT_CARROT.get(), 1.0F);
            map.put(OFBlocks.OVERWEIGHT_COCOA.get(), 1.0F);
            map.put(OFBlocks.OVERWEIGHT_POTATO.get(), 1.0F);
            map.put(OFBlocks.OVERWEIGHT_BAKED_POTATO.get(), 1.0F);
            map.put(OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get(), 1.0F);
            map.put(OFBlocks.OVERWEIGHT_ONION.get(), 1.0F);
            map.put(OFBlocks.OVERWEIGHT_CABBAGE.get(), 1.0F);
            map.put(OFBlocks.OVERWEIGHT_APPLE.get(), 1.0F);
            map.put(OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get(), 1.0F);
            map.put(OFBlocks.SEEDED_PEELED_MELON.get(), 0.65F);
            map.put(OFBlocks.HALF_SEEDED_PEELED_MELON.get(), 0.65F);
            map.put(OFBlocks.SEEDLESS_PEELED_MELON.get(), 0.65F);
            map.put(OFBlocks.WAXED_SEEDED_PEELED_MELON.get(), 0.65F);
            map.put(OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get(), 0.65F);
            map.put(OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get(), 0.65F);
            map.put(OFBlocks.PEELED_OVERWEIGHT_BEETROOT.get(), 1.0F);
            map.put(OFBlocks.PEELED_OVERWEIGHT_CARROT.get(), 1.0F);
            map.put(OFBlocks.PEELED_OVERWEIGHT_POTATO.get(), 1.0F);
            map.put(OFBlocks.PEELED_OVERWEIGHT_ONION.get(), 1.0F);
            map.put(OFBlocks.VEGETABLE_COMPOST.get(), 1.0F);
            map.put(OFBlocks.ALLIUM_BUSH.get(), 0.65F);
            map.put(OFItems.VEGETABLE_PEELS.get(), 1.0F);
        });

        Builder<Compostable, Item> builder = this.builder(NeoForgeDataMaps.COMPOSTABLES);

        compostables.forEach((itemLike, aFloat) -> {
            builder.add(itemLike.asItem().builtInRegistryHolder(), new Compostable(aFloat), false);
        });

    }
}
