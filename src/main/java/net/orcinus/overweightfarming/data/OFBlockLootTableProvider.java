package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.block.Block;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.common.blocks.PeeledMelonBlock;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import net.orcinus.overweightfarming.common.registry.OFTags;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class OFBlockLootTableProvider extends FabricBlockLootTableProvider {

    private static final ConditionJsonProvider FARMERSDELIGHT_LOADED = DefaultResourceConditions.allModsLoaded("farmersdelight");
    private static final ConditionJsonProvider HEDGEHOG_LOADED = DefaultResourceConditions.allModsLoaded("orcinus");
    private static final ConditionJsonProvider BEWITCHMENT_LOADED = DefaultResourceConditions.allModsLoaded("bewitchment");
    private static final ConditionJsonProvider BEWITCHMENT_PLUS_LOADED = DefaultResourceConditions.allModsLoaded("bwplus");
    private static final ConditionJsonProvider AYLYTH_LOADED = DefaultResourceConditions.allModsLoaded("aylyth");


    public OFBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

    }

    public LootTable.Builder overweightDrops(Block crop, Item product, Item seeds) {
        LootCondition.Builder conditionalBuilder = MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag((OFTags.OVERWEIGHT_HARVESTABLES)));
        LootTable.Builder builder = applyExplosionDecay(crop, LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(product)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0F, 20.0F)))
                        .conditionally(conditionalBuilder).alternatively(ItemEntry.builder(crop))))
        );
        if (seeds != null) {
            builder.pool(LootPool.builder().with(ItemEntry.builder(seeds).conditionally(conditionalBuilder)));
        }
        return builder;
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {

        for (Block block : OFObjects.BLOCKS.keySet().stream().filter(block -> block instanceof PeeledMelonBlock).toList()) {
            this.addDrop(block);
        }
        this.addDrop(OFObjects.VEGETABLE_COMPOST);
        this.addDrop(OFObjects.ALLIUM_BUSH, dropsWithProperty(OFObjects.ALLIUM_BUSH, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));

        this.addDrop(OFObjects.OVERWEIGHT_APPLE, overweightDrops(OFObjects.OVERWEIGHT_APPLE, Items.APPLE, null));
        this.addDrop(OFObjects.OVERWEIGHT_BAKED_POTATO, overweightDrops(OFObjects.OVERWEIGHT_BAKED_POTATO, Items.BAKED_POTATO, null));
        this.addDrop(OFObjects.OVERWEIGHT_BEETROOT, overweightDrops(OFObjects.OVERWEIGHT_BEETROOT, Items.BEETROOT, Items.BEETROOT_SEEDS));
        this.addDrop(OFObjects.OVERWEIGHT_CARROT, overweightDrops(OFObjects.OVERWEIGHT_CARROT, Items.CARROT, null));
        this.addDrop(OFObjects.OVERWEIGHT_COCOA, overweightDrops(OFObjects.OVERWEIGHT_COCOA, Items.COCOA_BEANS, null));
        this.addDrop(OFObjects.OVERWEIGHT_GOLDEN_APPLE, overweightDrops(OFObjects.OVERWEIGHT_GOLDEN_APPLE, Items.GOLDEN_APPLE, null));
        this.addDrop(OFObjects.OVERWEIGHT_NETHER_WART, overweightDrops(OFObjects.OVERWEIGHT_NETHER_WART, Items.NETHER_WART, null));
        this.addDrop(OFObjects.OVERWEIGHT_POISONOUS_POTATO, overweightDrops(OFObjects.OVERWEIGHT_POISONOUS_POTATO, Items.POISONOUS_POTATO, null));
        this.addDrop(OFObjects.OVERWEIGHT_POTATO, overweightDrops(OFObjects.OVERWEIGHT_POTATO, Items.POTATO, null));

        this.addDrop(OFObjects.PEELED_OVERWEIGHT_BEETROOT, overweightDrops(OFObjects.PEELED_OVERWEIGHT_BEETROOT, Items.BEETROOT, Items.BEETROOT_SEEDS));
        this.addDrop(OFObjects.PEELED_OVERWEIGHT_CARROT, overweightDrops(OFObjects.PEELED_OVERWEIGHT_CARROT, Items.CARROT, null));
        this.addDrop(OFObjects.PEELED_OVERWEIGHT_COCOA, overweightDrops(OFObjects.PEELED_OVERWEIGHT_COCOA, Items.COCOA_BEANS, null));
        this.addDrop(OFObjects.PEELED_OVERWEIGHT_POTATO, overweightDrops(OFObjects.PEELED_OVERWEIGHT_POTATO, Items.POTATO, null));

        this.addDrop(OFObjects.WAXED_HALF_SEEDED_PEELED_MELON);
        this.addDrop(OFObjects.WAXED_SEEDED_PEELED_MELON);
        this.addDrop(OFObjects.WAXED_SEEDLESS_PEELED_MELON);

        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_APPLE);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_BEETROOT);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_CABBAGE);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_CARROT);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_COCOA);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_GINGER);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_GOLDEN_APPLE);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_POISONOUS_POTATO);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_KIWI);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_NETHER_WART);
        this.addPottedPlantDrops(OFObjects.POTTED_OVERWEIGHT_ONION);

        withConditions(biConsumer, FARMERSDELIGHT_LOADED).accept(
                OFObjects.OVERWEIGHT_CABBAGE.getLootTableId(),
                overweightDrops(OFObjects.OVERWEIGHT_CABBAGE, getCompatItem("farmersdelight", "cabbage"), getCompatItem("farmersdelight", "cabbage_seeds"))
        );
        withConditions(biConsumer, FARMERSDELIGHT_LOADED).accept(
                OFObjects.OVERWEIGHT_ONION.getLootTableId(),
                overweightDrops(OFObjects.OVERWEIGHT_ONION, getCompatItem("farmersdelight", "onion"), null)
        );
        withConditions(biConsumer, FARMERSDELIGHT_LOADED).accept(
                OFObjects.PEELED_OVERWEIGHT_ONION.getLootTableId(),
                overweightDrops(OFObjects.PEELED_OVERWEIGHT_ONION, getCompatItem("farmersdelight", "onion"), null)
        );

        withConditions(biConsumer, HEDGEHOG_LOADED).accept(
                OFObjects.PEELED_OVERWEIGHT_KIWI.getLootTableId(),
                overweightDrops(OFObjects.PEELED_OVERWEIGHT_KIWI, getCompatItem("hedgehog", "kiwi"), null)
        );
        withConditions(biConsumer, HEDGEHOG_LOADED).accept(
                OFObjects.OVERWEIGHT_KIWI.getLootTableId(),
                overweightDrops(OFObjects.OVERWEIGHT_KIWI, getCompatItem("hedgehog", "kiwi"), null)
        );
        withConditions(biConsumer, HEDGEHOG_LOADED).accept(
                OFObjects.OVERWEIGHT_SLICED_KIWI.getLootTableId(),
                overweightDrops(OFObjects.OVERWEIGHT_SLICED_KIWI, getCompatItem("hedgehog", "kiwi"), null)
        );

        withConditions(biConsumer, BEWITCHMENT_LOADED).accept(
                OFObjects.OVERWEIGHT_MANDRAKE.getLootTableId(),
                overweightDrops(OFObjects.OVERWEIGHT_MANDRAKE, getCompatItem("bewitchment", "mandrake_root"), getCompatItem("bewitchment", "mandrake_seeds"))
        );
        withConditions(biConsumer, BEWITCHMENT_LOADED).accept(
                OFObjects.OVERWEIGHT_GARLIC.getLootTableId(),
                overweightDrops(OFObjects.OVERWEIGHT_GARLIC, getCompatItem("bewitchment", "garlic"), null)
        );
    }

    @Nullable
    public Item getCompatItem(String modid, String name) {
        return Registries.ITEM.get(new Identifier(modid, name));
    }
}
