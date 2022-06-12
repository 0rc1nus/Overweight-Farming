package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.*;
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
import net.orcinus.overweightfarming.blocks.PeeledMelonBlock;
import net.orcinus.overweightfarming.registry.OFObjects;
import net.orcinus.overweightfarming.registry.OFTags;

public class OFBlockLootTableProvider extends FabricBlockLootTableProvider {
    public OFBlockLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        for(Block block : OFObjects.BLOCKS.keySet().stream().filter(block -> block instanceof PeeledMelonBlock).toList()){
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
        this.addDrop(OFObjects.OVERWEIGHT_NETHERWART, overweightDrops(OFObjects.OVERWEIGHT_NETHERWART, Items.NETHER_WART, null));
        this.addDrop(OFObjects.OVERWEIGHT_POISONOUS_POTATO, overweightDrops(OFObjects.OVERWEIGHT_POISONOUS_POTATO, Items.POISONOUS_POTATO, null));
        this.addDrop(OFObjects.OVERWEIGHT_POTATO, overweightDrops(OFObjects.OVERWEIGHT_POTATO, Items.POTATO, null));

        this.addDrop(OFObjects.PEELED_OVERWEIGHT_BEETROOT, overweightDrops(OFObjects.PEELED_OVERWEIGHT_BEETROOT, Items.BEETROOT, Items.BEETROOT_SEEDS));
        this.addDrop(OFObjects.PEELED_OVERWEIGHT_CARROT, overweightDrops(OFObjects.PEELED_OVERWEIGHT_CARROT, Items.CARROT, null));
        this.addDrop(OFObjects.PEELED_OVERWEIGHT_COCOA, overweightDrops(OFObjects.PEELED_OVERWEIGHT_COCOA, Items.COCOA_BEANS, null));
        this.addDrop(OFObjects.PEELED_OVERWEIGHT_POTATO, overweightDrops(OFObjects.PEELED_OVERWEIGHT_POTATO, Items.POTATO, null));

        this.addDrop(OFObjects.WAXED_HALF_SEEDED_PEELED_MELON);
        this.addDrop(OFObjects.WAXED_SEEDED_PEELED_MELON);
        this.addDrop(OFObjects.WAXED_SEEDLESS_PEELED_MELON);


    }


    public static LootTable.Builder overweightDrops(Block crop, Item product, Item seeds) {
        LootCondition.Builder conditionalBuilder = MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag((OFTags.OVERWEIGHT_HARVESTABLES)));
        LootTable.Builder builder = applyExplosionDecay(crop, LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(product)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0F, 20.0F)))
                        .conditionally(conditionalBuilder).alternatively(ItemEntry.builder(crop))))
                        );
        if(seeds != null){
            builder.pool(LootPool.builder().with(ItemEntry.builder(seeds).conditionally(conditionalBuilder)));
        }
        return builder;
    }

}
