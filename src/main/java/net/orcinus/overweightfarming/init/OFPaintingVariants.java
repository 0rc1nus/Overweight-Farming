package net.orcinus.overweightfarming.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.orcinus.overweightfarming.OverweightFarming;

public class OFPaintingVariants {
    public static final ResourceKey<PaintingVariant> FUN_LAND = create("fun_land");

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, FUN_LAND, 2, 3);
    }

    private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
        context.register(key, new PaintingVariant(width, height, key.location()));
    }

    private static ResourceKey<PaintingVariant> create(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, OverweightFarming.id(name));
    }
}
