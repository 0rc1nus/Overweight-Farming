package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;


public class OFDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(OFBlockTagProvider::new);
        fabricDataGenerator.addProvider(OFRecipeProvider::new);
        fabricDataGenerator.addProvider(OFBlockLootTableProvider::new);

        OFBlockTagProvider blockTagProvider = fabricDataGenerator.addProvider(OFBlockTagProvider::new);
        fabricDataGenerator.addProvider(new OFItemTagProvider(fabricDataGenerator, blockTagProvider));

    }
}
