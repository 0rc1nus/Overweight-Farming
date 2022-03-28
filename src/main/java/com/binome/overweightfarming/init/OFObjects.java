package com.binome.overweightfarming.init;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.blocks.CropFullBlock;
import com.binome.overweightfarming.blocks.CropStemBlock;
import com.binome.overweightfarming.blocks.OverweightOnionBlock;
import com.binome.overweightfarming.items.StrawHatItem;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class OFObjects {
    private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    public static final Block OVERWEIGHT_BEETROOT_STEM = register("overweight_beetroot_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_CARROT_STEM = register("overweight_carrot_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_POTATO_STEM = register("overweight_potato_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_BEETROOT = register("overweight_beetroot_block", new CropFullBlock(OVERWEIGHT_BEETROOT_STEM, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_CARROT = register("overweight_carrot_block", new CropFullBlock(OVERWEIGHT_CARROT_STEM, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_COCOA = register("overweight_cocoa_block", new Block(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_POTATO = register("overweight_potato_block", new CropFullBlock(OVERWEIGHT_POTATO_STEM, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());

    public static final Block ALLIUM_BUSH = registerCompatBlock("farmersdelight", "allium_bush", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.CROP)), true, gen());

    public static final Block OVERWEIGHT_ONION = registerCompatBlock("farmersdelight", "overweight_onion_block", new OverweightOnionBlock(ALLIUM_BUSH, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),true, gen());
    public static final Block OVERWEIGHT_CABBAGE = registerCompatBlock("farmersdelight", "overweight_cabbage_block", new CropFullBlock(null, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),true, gen());

    public static final Item STRAW_HAT = register("straw_hat", new StrawHatItem(gen().maxCount(1)));


    private static Item.Settings gen() {
        return new Item.Settings().group(ItemGroup.FOOD);
    }

    private static <T extends Item> T register(String name, T item) {
        ITEMS.put(item, new Identifier(OverweightFarming.MODID, name));
        return item;
    }

    private static <T extends Block> T register(String name, T block, boolean createItem, Item.Settings settings) {
        BLOCKS.put(block, new Identifier(OverweightFarming.MODID, name));
        if (createItem) {
            ITEMS.put(new BlockItem(block, settings), BLOCKS.get(block));
        }
        return block;
    }

    private static <T extends Block> T registerCompatBlock(String modid, String name, T block, boolean createItem, Item.Settings settings) {
        if(FabricLoader.getInstance().isModLoaded(modid)){
            BLOCKS.put(block, new Identifier(OverweightFarming.MODID, name));
            if (createItem) {
                ITEMS.put(new BlockItem(block, settings), BLOCKS.get(block));
            }
            return block;
        }
        return null;
    }

    public static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
    }

}
