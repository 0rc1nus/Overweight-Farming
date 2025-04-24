package net.orcinus.overweightfarming.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.orcinus.overweightfarming.OverweightFarming;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = OverweightFarming.MODID, bus = EventBusSubscriber.Bus.MOD)
public class OFDataGenerator {

    private OFDataGenerator() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = dataGenerator.getPackOutput();
        OFBlockTagsProvider provider = new OFBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
        dataGenerator.addProvider(event.includeServer(), provider);
        dataGenerator.addProvider(event.includeServer(), new OFItemTagsProvider(packOutput, lookupProvider, provider.contentsGetter(), existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new OFLootTableProvider(packOutput, lookupProvider));
        dataGenerator.addProvider(event.includeServer(), new OFRecipeProvider(packOutput, lookupProvider));
    }

}
