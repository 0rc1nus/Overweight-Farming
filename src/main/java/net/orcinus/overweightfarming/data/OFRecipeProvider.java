package net.orcinus.overweightfarming.data;

import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipesProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.registry.OFObjects;

import java.util.function.Consumer;

public class OFRecipeProvider extends FabricRecipesProvider {
    private static final ConditionJsonProvider FARMERS_DELIGHT_LOADED = DefaultResourceConditions.allModsLoaded(FarmersDelightMod.MOD_ID);
    public OFRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(OFObjects.VEGETABLE_COMPOST)
                .input('X' ,OFObjects.VEGETABLE_PEELS)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX");
        ShapelessRecipeJsonBuilder.create(OFObjects.VEGETABLE_PEELS, 9)
                .input(OFObjects.VEGETABLE_COMPOST);
        ShapelessRecipeJsonBuilder.create(Items.MAGENTA_DYE, 2)
                .input(OFObjects.ALLIUM_BUSH).group("magenta_dye");
        ShapelessRecipeJsonBuilder.create(OFObjects.WAXED_HALF_SEEDED_PEELED_MELON, 1)
                .input(Items.HONEYCOMB).input(OFObjects.HALF_SEEDED_PEELED_MELON).group("waxed_peeled_melon");
        ShapelessRecipeJsonBuilder.create(OFObjects.WAXED_SEEDED_PEELED_MELON, 1)
                .input(Items.HONEYCOMB).input(OFObjects.SEEDED_PEELED_MELON).group("waxed_peeled_melon");
        ShapelessRecipeJsonBuilder.create(OFObjects.WAXED_SEEDLESS_PEELED_MELON, 1)
                .input(Items.HONEYCOMB).input(OFObjects.SEEDLESS_PEELED_MELON).group("waxed_peeled_melon");
        ShapedRecipeJsonBuilder.create(OFObjects.OVERWEIGHT_GOLDEN_APPLE)
                .input('X' ,Items.GOLD_BLOCK)
                .input('#' ,OFObjects.OVERWEIGHT_APPLE)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX");
    }

    public static Identifier asResource(String path) {
        return new Identifier(OverweightFarming.MODID, path);
    }
}
