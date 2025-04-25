package net.orcinus.overweightfarming.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.init.OFBiomeModifiers;
import net.orcinus.overweightfarming.init.OFConfiguredFeatures;
import net.orcinus.overweightfarming.init.OFPaintingVariants;
import net.orcinus.overweightfarming.init.OFPlacedFeatures;

import java.util.Set;
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

        RegistrySetBuilder builder = new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, OFConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, OFPlacedFeatures::bootstrap)
                .add(Registries.PAINTING_VARIANT, OFPaintingVariants::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, OFBiomeModifiers::bootstrap);

        OFBlockTagsProvider provider = new OFBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);

        dataGenerator.addProvider(event.includeServer(), provider);
        dataGenerator.addProvider(event.includeServer(), new OFItemTagsProvider(packOutput, lookupProvider, provider.contentsGetter(), existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new OFBiomeTagsProvider(packOutput, lookupProvider, existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new OFLootTableProvider(packOutput, lookupProvider));
        dataGenerator.addProvider(event.includeServer(), new OFRecipeProvider(packOutput, lookupProvider));

        DatapackBuiltinEntriesProvider datapackProvider = new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, builder, Set.of(OverweightFarming.MODID));
        dataGenerator.addProvider(event.includeServer(), datapackProvider);
    }

}
