package net.orcinus.overweightfarming.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFItems;

import java.util.concurrent.CompletableFuture;

public class OFRecipeProvider extends RecipeProvider {

    public OFRecipeProvider(PackOutput p_248933_, CompletableFuture<HolderLookup.Provider> p_323846_) {
        super(p_248933_, p_323846_);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderLookup) {
        wax(recipeOutput, OFBlocks.SEEDED_PEELED_MELON.get(), OFBlocks.WAXED_SEEDED_PEELED_MELON.get());
        wax(recipeOutput, OFBlocks.HALF_SEEDED_PEELED_MELON.get(), OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get());
        wax(recipeOutput, OFBlocks.SEEDLESS_PEELED_MELON.get(), OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get());

        nineBlockStorageRecipesRecipesWithCustomUnpacking(
                recipeOutput,
                RecipeCategory.MISC,
                OFItems.VEGETABLE_PEELS.get(),
                RecipeCategory.BUILDING_BLOCKS,
                OFBlocks.VEGETABLE_COMPOST.get(),
                OverweightFarming.id("vegetable_peels_from_vegetable_compost").toString(),
                OverweightFarming.id("vegetable_peels").toString()
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get())
                .define('#', Blocks.GOLD_BLOCK)
                .define('X', OFBlocks.OVERWEIGHT_APPLE.get())
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy("has_gold_block", has(Items.GOLD_BLOCK))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MAGENTA_DYE, 2)
                .requires(OFBlocks.ALLIUM_BUSH.get())
                .group("magenta_dye")
                .unlockedBy(getHasName(OFBlocks.ALLIUM_BUSH.get()), has(OFBlocks.ALLIUM_BUSH.get()))
                .save(recipeOutput, OverweightFarming.id(getConversionRecipeName(Items.MAGENTA_DYE, OFBlocks.ALLIUM_BUSH.get())));
    }

    protected static void nineBlockStorageRecipesRecipesWithCustomUnpacking(
            RecipeOutput recipeOutput, RecipeCategory category, ItemLike singleItem, RecipeCategory blockCategory, ItemLike blockItem, String p_248768_, String p_250847_
    ) {
        nineBlockStorageRecipes(recipeOutput, category, singleItem, blockCategory, blockItem, BuiltInRegistries.ITEM.getKey(blockItem.asItem()).toString(), null, p_248768_, p_250847_);
    }

    private static void wax(RecipeOutput recipeOutput, Block input, Block output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output)
                .requires(Items.HONEYCOMB)
                .requires(input)
                .group(getItemName(output))
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput, OverweightFarming.id(getConversionRecipeName(output, Items.HONEYCOMB)));
    }
}
