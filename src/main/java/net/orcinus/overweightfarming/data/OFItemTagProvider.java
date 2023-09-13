package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import net.orcinus.overweightfarming.common.registry.OFTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class OFItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public OFItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
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
