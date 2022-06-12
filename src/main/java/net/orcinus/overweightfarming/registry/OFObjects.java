package net.orcinus.overweightfarming.registry;

import net.orcinus.overweightfarming.blocks.*;
import net.orcinus.overweightfarming.items.MelonJuiceItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.items.StrawHatItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class OFObjects {
    public static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    public static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    //ITEMS
    public static final Item STRAW_HAT = register("straw_hat", new StrawHatItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
    public static final Item MELON_JUICE = register("melon_juice",  new MelonJuiceItem(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16)));
    public static final Item VEGETABLE_PEELS = register("vegetable_peels",  new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    //STEM
    public static final Block OVERWEIGHT_BEETROOT_STEM = register("overweight_beetroot_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_CARROT_STEM = register("overweight_carrot_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_POTATO_STEM = register("overweight_potato_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_MANDRAKE_STEM = register("overweight_mandrake_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_GARLIC_STEM = register("overweight_garlic_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());
    public static final Block OVERWEIGHT_BLOODROOT_STEM = register("overweight_bloodroot_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()),false, gen());

    public static final Block OVERWEIGHT_APPLE_STEM = register("overweight_apple_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().nonOpaque().sounds(BlockSoundGroup.CROP)), false, gen());
    public static final Block OVERWEIGHT_GOLDEN_APPLE_STEM = register("overweight_golden_apple_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().nonOpaque().sounds(BlockSoundGroup.CROP)), false, gen());

    public static final Block OVERWEIGHT_NETHERWART_STEM = register("overweight_nether_wart_stem", new NetherCropStemBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().sounds(BlockSoundGroup.NETHER_WART)), false, gen());
    public static final Block OVERWEIGHT_GINGER_STEM = register("overweight_ginger_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().sounds(BlockSoundGroup.CROP)), false, gen());


    //BLOCKS
    public static final Block ALLIUM_BUSH = register("allium_bush", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)),true, gen());
    public static final Block OVERWEIGHT_BEETROOT = register("overweight_beetroot_block", new CropFullBlock(OVERWEIGHT_BEETROOT_STEM, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_CARROT = register("overweight_carrot_block", new OverweightCarrotBlock(OVERWEIGHT_CARROT_STEM, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_COCOA = register("overweight_cocoa_block", new Block(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_POTATO = register("overweight_potato_block", new OverweightPotatoBlock(OVERWEIGHT_POTATO_STEM, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).breakInstantly()),true, gen());
    public static final Block OVERWEIGHT_ONION = register("overweight_onion_block", new OverweightOnionBlock(ALLIUM_BUSH, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)), FabricLoader.getInstance().isModLoaded("farmersdelight"), gen());
    public static final Block OVERWEIGHT_CABBAGE = register("overweight_cabbage_block", new OverweightCabbageBlock(null, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),FabricLoader.getInstance().isModLoaded("farmersdelight"), gen());
    public static final Block OVERWEIGHT_APPLE = register("overweight_apple_block", new OverweightAppleBlock(OVERWEIGHT_APPLE_STEM, FabricBlockSettings.of(OFMaterials.OVERWEIGHT_PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),true, gen());
    public static final Block OVERWEIGHT_GOLDEN_APPLE = register("overweight_golden_apple_block", new OverweightAppleBlock(OVERWEIGHT_GOLDEN_APPLE_STEM, FabricBlockSettings.of(OFMaterials.OVERWEIGHT_PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    public static final Block OVERWEIGHT_MANDRAKE = register("overweight_mandrake_block", new CropFullBlock(OVERWEIGHT_MANDRAKE_STEM, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),FabricLoader.getInstance().isModLoaded("bewitchment"), gen());
    public static final Block OVERWEIGHT_GARLIC = register("overweight_garlic_block", new CropFullBlock(OVERWEIGHT_GARLIC_STEM, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),FabricLoader.getInstance().isModLoaded("bewitchment"), gen());
    public static final Block OVERWEIGHT_BLOODROOT = register("overweight_bloodroot_block", new CropFullBlock(OVERWEIGHT_BLOODROOT_STEM, FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),FabricLoader.getInstance().isModLoaded("bwplus"), gen());

    public static final Block OVERWEIGHT_BAKED_POTATO = register("overweight_baked_potato_block", new Block(FabricBlockSettings.of(OFMaterials.OVERWEIGHT_PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),true, gen());
    public static final Block OVERWEIGHT_POISONOUS_POTATO = register("overweight_poisonous_potato_block", new CropFullBlock(null, FabricBlockSettings.copy(OVERWEIGHT_BAKED_POTATO)),true, gen());
    public static final Block OVERWEIGHT_NETHERWART = register("overweight_nether_wart_block", new NetherCropFullBlock(OVERWEIGHT_NETHERWART_STEM, FabricBlockSettings.of(OFMaterials.OVERWEIGHT_PLANT).strength(1.0F).sounds(BlockSoundGroup.NETHER_WART)), true, gen());
    public static final Block OVERWEIGHT_CABBAGE_STEM = register("overweight_cabbage_stem", new CropStemBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().sounds(BlockSoundGroup.CROP)), false, gen());
    public static final Block VEGETABLE_COMPOST = register("vegetable_compost", new Block(FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.MOSS_BLOCK)),true, gen());
    public static final Block OVERWEIGHT_KIWI = register( "overweight_kiwi_block", new CropFullBlock(null, FabricBlockSettings.of(OFMaterials.OVERWEIGHT_PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)), FabricLoader.getInstance().isModLoaded("orcinus"), gen());
    public static final Block OVERWEIGHT_SLICED_KIWI = register( "overweight_sliced_kiwi_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_KIWI)), FabricLoader.getInstance().isModLoaded("orcinus"), gen());
    public static final Block OVERWEIGHT_GINGER = register("overweight_ginger_block", new CropFullBlock(OVERWEIGHT_GINGER_STEM, FabricBlockSettings.of(OFMaterials.OVERWEIGHT_PLANT).strength(1.0F).sounds(BlockSoundGroup.CROP)),FabricLoader.getInstance().isModLoaded("snowyspirit"), gen());
    public static final Block SEEDED_PEELED_MELON = register("seeded_peeled_melon", new PeeledMelonBlock(PeeledMelonBlock.SeedState.SEEDED, FabricBlockSettings.copy(Blocks.MELON).sounds(BlockSoundGroup.WET_GRASS)), true, gen());
    public static final Block HALF_SEEDED_PEELED_MELON = register("half_seeded_peeled_melon", new PeeledMelonBlock(PeeledMelonBlock.SeedState.HALF_SEEDED, FabricBlockSettings.copy(Blocks.MELON).sounds(BlockSoundGroup.WET_GRASS)), true,gen());
    public static final Block SEEDLESS_PEELED_MELON = register("seedless_peeled_melon", new PeeledMelonBlock(PeeledMelonBlock.SeedState.SEEDLESS, FabricBlockSettings.copy(Blocks.MELON).sounds(BlockSoundGroup.WET_GRASS)),true,  gen());
    public static final Block WAXED_SEEDED_PEELED_MELON = register("waxed_seeded_peeled_melon", new Block(FabricBlockSettings.copy(SEEDED_PEELED_MELON)), true, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Block WAXED_HALF_SEEDED_PEELED_MELON = register("waxed_half_seeded_peeled_melon", new Block(FabricBlockSettings.copy(HALF_SEEDED_PEELED_MELON)), true, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Block WAXED_SEEDLESS_PEELED_MELON = register("waxed_seedless_peeled_melon", new Block(FabricBlockSettings.copy(SEEDLESS_PEELED_MELON)), true, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Block PEELED_OVERWEIGHT_BEETROOT = register("peeled_overweight_beetroot_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_BEETROOT)), true, gen());
    public static final Block PEELED_OVERWEIGHT_CARROT = register("peeled_overweight_carrot_block", new PillarBlock(FabricBlockSettings.copy(OVERWEIGHT_CARROT)),true, gen());
    public static final Block PEELED_OVERWEIGHT_POTATO = register("peeled_overweight_potato_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_POTATO)), true, gen());
    public static final Block PEELED_OVERWEIGHT_COCOA = register("peeled_overweight_cocoa_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_COCOA)), true, gen());
    public static final Block PEELED_OVERWEIGHT_ONION = register("peeled_overweight_onion_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_ONION)), FabricLoader.getInstance().isModLoaded("farmersdelight"), gen());
    public static final Block PEELED_OVERWEIGHT_KIWI = register( "peeled_overweight_kiwi_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_KIWI)), FabricLoader.getInstance().isModLoaded("orcinus"), gen());
    public static final Block PEELED_OVERWEIGHT_GINGER = register("peeled_overweight_ginger_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_GINGER)), FabricLoader.getInstance().isModLoaded("snowyspirit"), gen());

    public static final Block OVERWEIGHT_WEED = register("overweight_weed_block", new OverweightWeedBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque()),false, gen());



    //POTTED
    public static final Block POTTED_OVERWEIGHT_BEETROOT = register("potted_overweight_beetroot", new FlowerPotBlock(OVERWEIGHT_BEETROOT, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_CARROT = register("potted_overweight_carrot", new FlowerPotBlock(OVERWEIGHT_CARROT, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_COCOA = register("potted_overweight_cocoa", new FlowerPotBlock(OVERWEIGHT_COCOA, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_POTATO = register("potted_overweight_potato", new FlowerPotBlock(OVERWEIGHT_POTATO, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_GINGER = register("potted_overweight_ginger", new FlowerPotBlock(OVERWEIGHT_GINGER, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_POISONOUS_POTATO = register("potted_overweight_poisonous_potato", new FlowerPotBlock(OVERWEIGHT_POISONOUS_POTATO, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_APPLE = register("potted_overweight_apple", new FlowerPotBlock(OVERWEIGHT_APPLE, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_GOLDEN_APPLE = register("potted_overweight_golden_apple", new FlowerPotBlock(OVERWEIGHT_GOLDEN_APPLE, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_KIWI = register("potted_overweight_kiwi", new FlowerPotBlock(OVERWEIGHT_KIWI, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_ONION = register("potted_overweight_onion", new FlowerPotBlock(OVERWEIGHT_ONION, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_CABBAGE = register("potted_overweight_cabbage", new FlowerPotBlock(OVERWEIGHT_CABBAGE, FabricBlockSettings.of(Material.DECORATION).breakInstantly()), false, gen());
    public static final Block POTTED_OVERWEIGHT_NETHER_WART = register("potted_overweight_nether_wart", new FlowerPotBlock(OVERWEIGHT_NETHERWART, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()), false, gen());



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
        CompostingChanceRegistry compostRegistry = CompostingChanceRegistry.INSTANCE;
        compostRegistry.add(OVERWEIGHT_BEETROOT, 1.0f);
        compostRegistry.add(OVERWEIGHT_CARROT, 1.0f);
        compostRegistry.add(OVERWEIGHT_COCOA, 1.0f);
        compostRegistry.add(OVERWEIGHT_POTATO, 1.0f);
        compostRegistry.add(OVERWEIGHT_BAKED_POTATO, 1.0f);
        compostRegistry.add(OVERWEIGHT_POISONOUS_POTATO, 1.0f);
        compostRegistry.add(OVERWEIGHT_ONION, 1.0f);
        compostRegistry.add(OVERWEIGHT_CABBAGE, 1.0f);
        compostRegistry.add(PEELED_OVERWEIGHT_BEETROOT, 1.0f);
        compostRegistry.add(PEELED_OVERWEIGHT_CARROT, 1.0f);
        compostRegistry.add(PEELED_OVERWEIGHT_POTATO, 1.0f);
        compostRegistry.add(PEELED_OVERWEIGHT_ONION, 1.0f);
        compostRegistry.add(VEGETABLE_COMPOST, 1.0f);
        compostRegistry.add(ALLIUM_BUSH, 0.65f);
        compostRegistry.add(VEGETABLE_PEELS, 1.0f);

        compostRegistry.add(OVERWEIGHT_BLOODROOT, 1.0f);
        compostRegistry.add(OVERWEIGHT_MANDRAKE, 1.0f);
        compostRegistry.add(OVERWEIGHT_GARLIC, 1.0f);


    }

}
