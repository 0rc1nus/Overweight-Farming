package net.orcinus.overweightfarming.init;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class OFVanillaIntegration {

    public static void init() {
        registerCompostable(1.0F, OFItems.VEGETABLE_PEELS.get());
    }

    public static void registerCompostable(float chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

}
