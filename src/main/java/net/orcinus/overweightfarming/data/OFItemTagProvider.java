package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import net.orcinus.overweightfarming.common.registry.OFTags;

public class OFItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public OFItemTagProvider(FabricDataGenerator dataGenerator, BlockTagProvider blockTagProvider) {
        super(dataGenerator, blockTagProvider);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(ItemTags.TALL_FLOWERS).add(OFObjects.ALLIUM_BUSH.asItem());
        getOrCreateTagBuilder(OFTags.OVERWEIGHT_HARVESTABLES)
                .add(Items.DIAMOND_HOE)
                .add(Items.NETHERITE_HOE)
                .add(Items.WOODEN_HOE)
                .add(Items.STONE_HOE)
                .add(Items.IRON_HOE)
                .add(Items.GOLDEN_HOE);


    }
}
