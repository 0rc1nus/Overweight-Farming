package net.orcinus.overweightfarming.init;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class OFVanillaIntegration {

    public static void init() {
        registerCompostable(1.0F, OFBlocks.OVERWEIGHT_BEETROOT.get().asItem());
        registerCompostable(1.0F, OFBlocks.OVERWEIGHT_CARROT.get().asItem());
        registerCompostable(1.0F, OFBlocks.OVERWEIGHT_COCOA.get().asItem());
        registerCompostable(1.0F, OFBlocks.OVERWEIGHT_POTATO.get().asItem());
        registerCompostable(1.0F, OFBlocks.OVERWEIGHT_BAKED_POTATO.get().asItem());
        registerCompostable(1.0F, OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get().asItem());
        registerCompostable(1.0F, OFBlocks.OVERWEIGHT_ONION.get().asItem());
        registerCompostable(1.0F, OFBlocks.OVERWEIGHT_CABBAGE.get().asItem());
        registerCompostable(0.65F, OFBlocks.SEEDED_PEELED_MELON.get().asItem());
        registerCompostable(0.65F, OFBlocks.HALF_SEEDED_PEELED_MELON.get().asItem());
        registerCompostable(0.65F, OFBlocks.SEEDLESS_PEELED_MELON.get().asItem());
        registerCompostable(0.65F, OFBlocks.WAXED_SEEDED_PEELED_MELON.get().asItem());
        registerCompostable(0.65F, OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get().asItem());
        registerCompostable(0.65F, OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get().asItem());
        registerCompostable(1.0F, OFBlocks.PEELED_OVERWEIGHT_BEETROOT.get().asItem());
        registerCompostable(1.0F, OFBlocks.PEELED_OVERWEIGHT_CARROT.get().asItem());
        registerCompostable(1.0F, OFBlocks.PEELED_OVERWEIGHT_POTATO.get().asItem());
        registerCompostable(1.0F, OFBlocks.PEELED_OVERWEIGHT_ONION.get().asItem());
        registerCompostable(1.0F, OFBlocks.VEGETABLE_COMPOST.get().asItem());
        registerCompostable(0.65F, OFBlocks.ALLIUM_BUSH.get().asItem());
        registerCompostable(1.0F, OFItems.VEGETABLE_PEELS.get());
    }

    public static void registerCompostable(float chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

}
