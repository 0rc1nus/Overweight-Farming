package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class OFDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(OFBlockTagProvider::new);
        fabricDataGenerator.addProvider(OFRecipeProvider::new);
        fabricDataGenerator.addProvider(OFBlockLootTableProvider::new);
        fabricDataGenerator.addProvider(OFCompatBlockLootTableProvider::new);
        fabricDataGenerator.addProvider(OFModelProvider::new);

        OFBlockTagProvider blockTagProvider = fabricDataGenerator.addProvider(OFBlockTagProvider::new);
        fabricDataGenerator.addProvider(new OFItemTagProvider(fabricDataGenerator, blockTagProvider));

    }
}
