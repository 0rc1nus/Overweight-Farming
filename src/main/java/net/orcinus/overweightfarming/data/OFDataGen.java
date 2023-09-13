package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class OFDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(OFRecipeProvider::new);
        pack.addProvider(OFBlockLootTableProvider::new);
        pack.addProvider(OFModelProvider::new);

        OFBlockTagProvider blockTagProvider = pack.addProvider(OFBlockTagProvider::new);
        pack.addProvider((o, f) -> new OFItemTagProvider(o, f, blockTagProvider));

    }
}
