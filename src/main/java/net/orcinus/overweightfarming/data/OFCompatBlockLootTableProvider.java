package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class OFCompatBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final ConditionJsonProvider FARMERSDELIGHT_LOADED = DefaultResourceConditions.allModsLoaded("farmersdelight");
    private static final ConditionJsonProvider HEDGEHOG_LOADED = DefaultResourceConditions.allModsLoaded("orcinus");
    private static final ConditionJsonProvider BEWITCHMENT_LOADED = DefaultResourceConditions.allModsLoaded("bewitchment");
    private static final ConditionJsonProvider BEWITCHMENT_PLUS_LOADED = DefaultResourceConditions.allModsLoaded("bwplus");
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

        withConditions(biConsumer, BEWITCHMENT_LOADED).accept(
                OFObjects.OVERWEIGHT_MANDRAKE.getLootTableId(),
                OFBlockLootTableProvider.overweightDrops(OFObjects.OVERWEIGHT_MANDRAKE, getCompatItem("bewitchment", "mandrake_root"), getCompatItem("bewitchment", "mandrake_seeds"))
        );
        withConditions(biConsumer, BEWITCHMENT_LOADED).accept(
                OFObjects.OVERWEIGHT_GARLIC.getLootTableId(),
                OFBlockLootTableProvider.overweightDrops(OFObjects.OVERWEIGHT_GARLIC, getCompatItem("bewitchment", "garlic"), null)
        );
        withConditions(biConsumer, BEWITCHMENT_PLUS_LOADED).accept(
                OFObjects.OVERWEIGHT_BLOODROOT.getLootTableId(),
                OFBlockLootTableProvider.overweightDrops(OFObjects.OVERWEIGHT_BLOODROOT, getCompatItem("bwplus", "bloodroot_item"), null)
        );
    }


    @Nullable
    public Item getCompatItem(String modid, String name) {
        return Registry.ITEM.get(new Identifier(modid, name));
    }




}