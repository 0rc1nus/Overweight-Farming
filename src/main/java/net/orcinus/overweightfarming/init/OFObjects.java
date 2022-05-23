package net.orcinus.overweightfarming.init;

import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.blocks.CropFullBlock;
import net.orcinus.overweightfarming.blocks.CropStemBlock;
import net.orcinus.overweightfarming.blocks.OverweightOnionBlock;
import net.orcinus.overweightfarming.items.StrawHatItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
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

    public static final Item STRAW_HAT = register("straw_hat", new StrawHatItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));

    public static final Block OVERWEIGHT_BEETROOT_STEM = register("overweight_beetroot_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_CARROT_STEM = register("overweight_carrot_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_POTATO_STEM = register("overweight_potato_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_BEETROOT = register("overweight_beetroot_block", new CropFullBlock(OVERWEIGHT_BEETROOT_STEM, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_CARROT = register("overweight_carrot_block", new CropFullBlock(OVERWEIGHT_CARROT_STEM, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_COCOA = register("overweight_cocoa_block", new Block(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_POTATO = register("overweight_potato_block", new CropFullBlock(OVERWEIGHT_POTATO_STEM, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());

    public static final Block ALLIUM_BUSH = register("allium_bush", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.CROP)), true, new Item.Settings().group(ItemGroup.DECORATIONS));

    public static final Block OVERWEIGHT_ONION = register("overweight_onion_block", new OverweightOnionBlock(ALLIUM_BUSH, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)), FabricLoader.getInstance().isModLoaded("farmersdelight"), gen());
    public static final Block OVERWEIGHT_CABBAGE = register("overweight_cabbage_block", new CropFullBlock(null, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),FabricLoader.getInstance().isModLoaded("farmersdelight"), gen());


    public static final Block OVERWEIGHT_MANDRAKE_STEM = register("overweight_mandrake_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_GARLIC_STEM = register("overweight_garlic_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_BLOODROOT_STEM = register("overweight_bloodroot_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());

    public static final Block OVERWEIGHT_MANDRAKE = register("overweight_mandrake_block", new CropFullBlock(OVERWEIGHT_MANDRAKE_STEM, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),FabricLoader.getInstance().isModLoaded("bewitchment"), gen());
    public static final Block OVERWEIGHT_GARLIC = register("overweight_garlic_block", new CropFullBlock(OVERWEIGHT_GARLIC_STEM, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),FabricLoader.getInstance().isModLoaded("bewitchment"), gen());
    public static final Block OVERWEIGHT_BLOODROOT = register("overweight_bloodroot_block", new CropFullBlock(OVERWEIGHT_BLOODROOT_STEM, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),FabricLoader.getInstance().isModLoaded("bwplus"), gen());


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

    public static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
    }

}
