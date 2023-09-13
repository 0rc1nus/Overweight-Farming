package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.common.registry.OFObjects;

import java.util.function.Consumer;

public class OFRecipeProvider extends FabricRecipeProvider {

    public OFRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    public static Identifier asResource(String path) {
        return new Identifier(OverweightFarming.MODID, path);
    }



    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, OFObjects.VEGETABLE_COMPOST)
                .input('X', OFObjects.VEGETABLE_PEELS)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX");
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, OFObjects.VEGETABLE_PEELS, 9)
                .input(OFObjects.VEGETABLE_COMPOST);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MAGENTA_DYE, 2)
                .input(OFObjects.ALLIUM_BUSH).group("magenta_dye");
        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, OFObjects.WAXED_HALF_SEEDED_PEELED_MELON, 1)
                .input(Items.HONEYCOMB).input(OFObjects.HALF_SEEDED_PEELED_MELON).group("waxed_peeled_melon");
        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS,OFObjects.WAXED_SEEDED_PEELED_MELON, 1)
                .input(Items.HONEYCOMB).input(OFObjects.SEEDED_PEELED_MELON).group("waxed_peeled_melon");
        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS,OFObjects.WAXED_SEEDLESS_PEELED_MELON, 1)
                .input(Items.HONEYCOMB).input(OFObjects.SEEDLESS_PEELED_MELON).group("waxed_peeled_melon");
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS,OFObjects.OVERWEIGHT_GOLDEN_APPLE)
                .input('X', Items.GOLD_BLOCK)
                .input('#', OFObjects.OVERWEIGHT_APPLE)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX");
    }
}
