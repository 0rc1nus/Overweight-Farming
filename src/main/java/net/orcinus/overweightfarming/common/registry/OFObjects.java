package net.orcinus.overweightfarming.common.registry;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.common.blocks.*;
import net.orcinus.overweightfarming.common.items.MelonJuiceItem;
import net.orcinus.overweightfarming.common.items.StrawHatItem;

import java.util.LinkedHashMap;
import java.util.Map;

public interface OFObjects {
    Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    //ITEMS
    Item STRAW_HAT = register("straw_hat", new StrawHatItem(new FabricItemSettings().maxCount(1)));

    Item MELON_JUICE = register("melon_juice", new MelonJuiceItem(new FabricItemSettings()
            .recipeRemainder(Items.GLASS_BOTTLE)
            .food((new FoodComponent.Builder())
                    .hunger(3)
                    .saturationModifier(0.6F).build())
            .maxCount(16)));

    Item VEGETABLE_PEELS = register("vegetable_peels", new Item(new FabricItemSettings()));

    //STEM
    Block OVERWEIGHT_BEETROOT_STEM = register("overweight_beetroot_stem", new CropStemBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.CROP).breakInstantly().noCollision().pistonBehavior(PistonBehavior.DESTROY).strength(1)), false, gen());
    Block OVERWEIGHT_BEETROOT = register("overweight_beetroot_block", new CropFullBlock(OVERWEIGHT_BEETROOT_STEM, FabricBlockSettings.create().sounds(BlockSoundGroup.CROP).breakInstantly().pistonBehavior(PistonBehavior.DESTROY).strength(1)), true, gen());
    Block PEELED_OVERWEIGHT_BEETROOT = register("peeled_overweight_beetroot_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_BEETROOT)), true, gen());
    //POTTED
    Block OVERWEIGHT_CARROT_STEM = register("overweight_carrot_stem", new CropStemBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()), false, gen());
    Block OVERWEIGHT_CARROT = register("overweight_carrot_block", new OverweightCarrotBlock(OVERWEIGHT_CARROT_STEM, FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).sounds(BlockSoundGroup.CROP).breakInstantly()), true, gen());
    Block PEELED_OVERWEIGHT_CARROT = register("peeled_overweight_carrot_block", new PillarBlock(FabricBlockSettings.copy(OVERWEIGHT_CARROT)), true, gen());
    Block OVERWEIGHT_POTATO_STEM = register("overweight_potato_stem", new CropStemBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()), false, gen());
    Block OVERWEIGHT_POTATO = register("overweight_potato_block", new OverweightPotatoBlock(OVERWEIGHT_POTATO_STEM, FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).sounds(BlockSoundGroup.CROP).breakInstantly()), true, gen());
    Block PEELED_OVERWEIGHT_POTATO = register("peeled_overweight_potato_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_POTATO)), true, gen());
    Block OVERWEIGHT_MANDRAKE_STEM = register("overweight_mandrake_stem", new CropStemBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()), false, gen());
    Block OVERWEIGHT_MANDRAKE = register("overweight_mandrake_block", new CropFullBlock(OVERWEIGHT_MANDRAKE_STEM, FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    Block OVERWEIGHT_GARLIC_STEM = register("overweight_garlic_stem", new CropStemBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).sounds(BlockSoundGroup.CROP).breakInstantly().noCollision()), false, gen());
    Block OVERWEIGHT_GARLIC = register("overweight_garlic_block", new CropFullBlock(OVERWEIGHT_GARLIC_STEM, FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    Block OVERWEIGHT_APPLE_STEM = register("overweight_apple_stem", new CropStemBlock(FabricBlockSettings.create().breakInstantly().noCollision().nonOpaque().sounds(BlockSoundGroup.CROP)), false, gen());
    Block OVERWEIGHT_APPLE = register("overweight_apple_block", new OverweightAppleBlock(false, OVERWEIGHT_APPLE_STEM, FabricBlockSettings.create().strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    Block OVERWEIGHT_GOLDEN_APPLE_STEM = register("overweight_golden_apple_stem", new CropStemBlock(FabricBlockSettings.create().breakInstantly().noCollision().nonOpaque().sounds(BlockSoundGroup.CROP)), false, gen());
    Block OVERWEIGHT_GOLDEN_APPLE = register("overweight_golden_apple_block", new OverweightAppleBlock(true, OVERWEIGHT_GOLDEN_APPLE_STEM, FabricBlockSettings.create().strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    Block OVERWEIGHT_NETHER_WART_STEM = register("overweight_nether_wart_stem", new NetherCropStemBlock(FabricBlockSettings.create().breakInstantly().noCollision().sounds(BlockSoundGroup.NETHER_WART)), false, gen());
    Block OVERWEIGHT_NETHER_WART = register("overweight_nether_wart_block", new NetherCropFullBlock(OVERWEIGHT_NETHER_WART_STEM, FabricBlockSettings.create().strength(1.0F).sounds(BlockSoundGroup.NETHER_WART)), true, gen());
    Block OVERWEIGHT_GINGER_STEM = register("overweight_ginger_stem", new CropStemBlock(FabricBlockSettings.create().breakInstantly().noCollision().sounds(BlockSoundGroup.CROP)), false, gen());
    Block OVERWEIGHT_GINGER = register("overweight_ginger_block", new CropFullBlock(OVERWEIGHT_GINGER_STEM, FabricBlockSettings.create().strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    Block PEELED_OVERWEIGHT_GINGER = register("peeled_overweight_ginger_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_GINGER)), true, gen());
   //BLOCKS
    Block ALLIUM_BUSH = register("allium_bush", new AlliumBushBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)), true, new Item.Settings());
    Block OVERWEIGHT_ONION = register("overweight_onion_block", new OverweightOnionBlock(ALLIUM_BUSH, FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    Block PEELED_OVERWEIGHT_ONION = register("peeled_overweight_onion_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_ONION)), true, gen());
    Block OVERWEIGHT_COCOA = register("overweight_cocoa_block", new OverweightCocoaBlock(null, FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).sounds(BlockSoundGroup.CROP).breakInstantly()), true, gen());
    Block PEELED_OVERWEIGHT_COCOA = register("peeled_overweight_cocoa_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_COCOA)), true, gen());
    Block OVERWEIGHT_CABBAGE = register("overweight_cabbage_block", new CropFullBlock(null, FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    Block OVERWEIGHT_BAKED_POTATO = register("overweight_baked_potato_block", new Block(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    Block OVERWEIGHT_POISONOUS_POTATO = register("overweight_poisonous_potato_block", new CropFullBlock(null, FabricBlockSettings.copy(OVERWEIGHT_BAKED_POTATO)), true, gen());
    Block OVERWEIGHT_CABBAGE_STEM = register("overweight_cabbage_stem", new CropStemBlock(FabricBlockSettings.create().breakInstantly().pistonBehavior(PistonBehavior.DESTROY).strength(1).noCollision().sounds(BlockSoundGroup.CROP)), false, gen());
    Block VEGETABLE_COMPOST = register("vegetable_compost", new Block(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).strength(1.0F).sounds(BlockSoundGroup.MOSS_BLOCK)), true, gen());
    Block OVERWEIGHT_KIWI = register("overweight_kiwi_block", new CropFullBlock(null, FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).strength(1).strength(1.0F).sounds(BlockSoundGroup.CROP)), true, gen());
    Block OVERWEIGHT_SLICED_KIWI = register("overweight_sliced_kiwi_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_KIWI)), true, gen());
    Block PEELED_OVERWEIGHT_KIWI = register("peeled_overweight_kiwi_block", new Block(FabricBlockSettings.copy(OVERWEIGHT_KIWI)), true, gen());
    Block SEEDED_PEELED_MELON = register("seeded_peeled_melon", new PeeledMelonBlock(PeeledMelonBlock.SeedState.SEEDED, FabricBlockSettings.copy(Blocks.MELON).pistonBehavior(PistonBehavior.DESTROY).strength(1).sounds(BlockSoundGroup.WET_GRASS)), true, gen());
    Block WAXED_SEEDED_PEELED_MELON = register("waxed_seeded_peeled_melon", new Block(FabricBlockSettings.copy(SEEDED_PEELED_MELON)), true, new Item.Settings());
    Block HALF_SEEDED_PEELED_MELON = register("half_seeded_peeled_melon", new PeeledMelonBlock(PeeledMelonBlock.SeedState.HALF_SEEDED, FabricBlockSettings.copy(Blocks.MELON).sounds(BlockSoundGroup.WET_GRASS)), true, gen());
    Block WAXED_HALF_SEEDED_PEELED_MELON = register("waxed_half_seeded_peeled_melon", new Block(FabricBlockSettings.copy(HALF_SEEDED_PEELED_MELON)), true, new Item.Settings());
    Block SEEDLESS_PEELED_MELON = register("seedless_peeled_melon", new PeeledMelonBlock(PeeledMelonBlock.SeedState.SEEDLESS, FabricBlockSettings.copy(Blocks.MELON).pistonBehavior(PistonBehavior.DESTROY).strength(1).sounds(BlockSoundGroup.WET_GRASS)), true, gen());
    Block WAXED_SEEDLESS_PEELED_MELON = register("waxed_seedless_peeled_melon", new Block(FabricBlockSettings.copy(SEEDLESS_PEELED_MELON)), true, new Item.Settings());
    Block OVERWEIGHT_WEED = register("overweight_weed_block", new OverweightWeedBlock(FabricBlockSettings.create().mapColor(MapColor.GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().pistonBehavior(PistonBehavior.DESTROY).strength(1)), false, gen());

    Block POTTED_OVERWEIGHT_KIWI = register("potted_overweight_kiwi", new FlowerPotBlock(OVERWEIGHT_KIWI, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_POISONOUS_POTATO = register("potted_overweight_poisonous_potato", new FlowerPotBlock(OVERWEIGHT_POISONOUS_POTATO, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_COCOA = register("potted_overweight_cocoa", new FlowerPotBlock(OVERWEIGHT_COCOA, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_CABBAGE = register("potted_overweight_cabbage", new FlowerPotBlock(OVERWEIGHT_CABBAGE, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_ONION = register("potted_overweight_onion", new FlowerPotBlock(OVERWEIGHT_ONION, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_BEETROOT = register("potted_overweight_beetroot", new FlowerPotBlock(OVERWEIGHT_BEETROOT, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_CARROT = register("potted_overweight_carrot", new FlowerPotBlock(OVERWEIGHT_CARROT, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_POTATO = register("potted_overweight_potato", new FlowerPotBlock(OVERWEIGHT_POTATO, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_APPLE = register("potted_overweight_apple", new FlowerPotBlock(OVERWEIGHT_APPLE, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_GINGER = register("potted_overweight_ginger", new FlowerPotBlock(OVERWEIGHT_GINGER, FabricBlockSettings.create().breakInstantly()), false, gen());
    Block POTTED_OVERWEIGHT_NETHER_WART = register("potted_overweight_nether_wart", new FlowerPotBlock(OVERWEIGHT_NETHER_WART, FabricBlockSettings.create().breakInstantly().nonOpaque()), false, gen());
    Block POTTED_OVERWEIGHT_GOLDEN_APPLE = register("potted_overweight_golden_apple", new FlowerPotBlock(OVERWEIGHT_GOLDEN_APPLE, FabricBlockSettings.create().breakInstantly()), false, gen());


    static Item.Settings gen() {
        return new Item.Settings();
    }

    static <T extends Item> T register(String name, T item) {
        ITEMS.put(item, new Identifier(OverweightFarming.MODID, name));
        return item;
    }

    static <T extends Block> T register(String name, T block, boolean createItem, Item.Settings settings) {
        BLOCKS.put(block, new Identifier(OverweightFarming.MODID, name));
        if (createItem) {
            ITEMS.put(new BlockItem(block, settings), BLOCKS.get(block));
        }
        return block;
    }

    static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registries.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> {
            Registry.register(Registries.ITEM, ITEMS.get(item), item);
        });
        ItemGroupEvents.modifyEntriesEvent(OverweightFarming.ITEM_GROUP).register(entries -> ITEMS.keySet().forEach(entries::add));


        CompostingChanceRegistry compostRegistry = CompostingChanceRegistry.INSTANCE;
        compostRegistry.add(OVERWEIGHT_BEETROOT, 1.0f);
        compostRegistry.add(OVERWEIGHT_CARROT, 1.0f);
        compostRegistry.add(OVERWEIGHT_COCOA, 1.0f);
        compostRegistry.add(OVERWEIGHT_POTATO, 1.0f);
        compostRegistry.add(OVERWEIGHT_BAKED_POTATO, 1.0f);
        compostRegistry.add(OVERWEIGHT_POISONOUS_POTATO, 1.0f);
        compostRegistry.add(PEELED_OVERWEIGHT_BEETROOT, 1.0f);
        compostRegistry.add(PEELED_OVERWEIGHT_CARROT, 1.0f);
        compostRegistry.add(PEELED_OVERWEIGHT_POTATO, 1.0f);
        compostRegistry.add(VEGETABLE_COMPOST, 1.0f);
        compostRegistry.add(ALLIUM_BUSH, 0.65f);
        compostRegistry.add(VEGETABLE_PEELS, 1.0f);

        if(FabricLoader.getInstance().isModLoaded("farmersdelight")){
            compostRegistry.add(OVERWEIGHT_ONION, 1.0f);
            compostRegistry.add(OVERWEIGHT_CABBAGE, 1.0f);
            compostRegistry.add(PEELED_OVERWEIGHT_ONION, 1.0f);
        }
        if(FabricLoader.getInstance().isModLoaded("bewitchment")){
            compostRegistry.add(OVERWEIGHT_MANDRAKE, 1.0f);
            compostRegistry.add(OVERWEIGHT_GARLIC, 1.0f);

        }
    }
}
