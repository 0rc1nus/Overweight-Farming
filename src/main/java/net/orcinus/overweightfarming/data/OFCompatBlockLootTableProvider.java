package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.registry.OFObjects;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class OFCompatBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final ConditionJsonProvider FARMERSDELIGHT_LOADED = DefaultResourceConditions.allModsLoaded("farmersdelight");
    private static final ConditionJsonProvider HEDGEHOG_LOADED = DefaultResourceConditions.allModsLoaded("orcinus");
    public OFCompatBlockLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        withConditions(biConsumer, FARMERSDELIGHT_LOADED).accept(
                OFObjects.OVERWEIGHT_CABBAGE.getLootTableId(),
                OFBlockLootTableProvider.overweightDrops(OFObjects.OVERWEIGHT_CABBAGE, getCompatItem("farmersdelight", "cabbage"), getCompatItem("farmersdelight", "cabbage_seeds"))
        );
        withConditions(biConsumer, FARMERSDELIGHT_LOADED).accept(
                OFObjects.OVERWEIGHT_ONION.getLootTableId(),
                OFBlockLootTableProvider.overweightDrops(OFObjects.OVERWEIGHT_ONION, getCompatItem("farmersdelight", "onion"), null)
        );
        withConditions(biConsumer, FARMERSDELIGHT_LOADED).accept(
                OFObjects.PEELED_OVERWEIGHT_ONION.getLootTableId(),
                OFBlockLootTableProvider.overweightDrops(OFObjects.PEELED_OVERWEIGHT_ONION, getCompatItem("farmersdelight", "onion"), null)
        );

        withConditions(biConsumer, HEDGEHOG_LOADED).accept(
                OFObjects.PEELED_OVERWEIGHT_KIWI.getLootTableId(),
                OFBlockLootTableProvider.overweightDrops(OFObjects.PEELED_OVERWEIGHT_KIWI, getCompatItem("hedgehog", "kiwi"), null)
        );
        withConditions(biConsumer, HEDGEHOG_LOADED).accept(
                OFObjects.OVERWEIGHT_KIWI.getLootTableId(),
                OFBlockLootTableProvider.overweightDrops(OFObjects.OVERWEIGHT_KIWI, getCompatItem("hedgehog", "kiwi"), null)
        );
        withConditions(biConsumer, HEDGEHOG_LOADED).accept(
                OFObjects.OVERWEIGHT_SLICED_KIWI.getLootTableId(),
                OFBlockLootTableProvider.overweightDrops(OFObjects.OVERWEIGHT_SLICED_KIWI, getCompatItem("hedgehog", "kiwi"), null)
        );
    }


    @Nullable
    public Item getCompatItem(String modid, String name) {
        return Registry.ITEM.get(new Identifier(modid, name));
    }




}