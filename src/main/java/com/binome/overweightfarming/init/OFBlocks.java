package com.binome.overweightfarming.init;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.blocks.*;
import com.sun.jna.platform.win32.OaIdl;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OFBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OverweightFarming.MODID);

    public static final RegistryObject<Block> OVERWEIGHT_BEETROOT_STEM = registerNoTabBlock("overweight_beetroot_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of(Material.VEGETABLE).instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> OVERWEIGHT_CARROT_STEM = registerNoTabBlock("overweight_carrot_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of(Material.VEGETABLE).instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> OVERWEIGHT_POTATO_STEM = registerNoTabBlock("overweight_potato_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of(Material.VEGETABLE).instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> OVERWEIGHT_GINGER_STEM = registerNoTabBlock("overweight_ginger_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of(Material.VEGETABLE).instabreak().noCollission().sound(SoundType.CROP)));
    public static final RegistryObject<Block> OVERWEIGHT_BEETROOT = registerBlock("overweight_beetroot_block", () -> new CropFullBlock(OVERWEIGHT_BEETROOT_STEM.get(), BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_CARROT = registerBlock("overweight_carrot_block", () -> new CropFullBlock(OVERWEIGHT_CARROT_STEM.get(), BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_COCOA = registerBlock("overweight_cocoa_block", () -> new Block(BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_POTATO = registerBlock("overweight_potato_block", () -> new OverweightPotatoBlock(OVERWEIGHT_POTATO_STEM.get(), BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_BAKED_POTATO = registerBlock("overweight_baked_potato_block", () -> new Block(BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_POISONOUS_POTATO = registerBlock("overweight_poisonous_potato_block", () -> new Block(BlockBehaviour.Properties.copy(OVERWEIGHT_BAKED_POTATO.get())), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> ALLIUM_BUSH = registerCompatBlock("farmersdelight", "allium_bush", () -> new TallFlowerBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> OVERWEIGHT_ONION = registerCompatBlock("farmersdelight", "overweight_onion_block", () -> new OverweightOnionBlock(ALLIUM_BUSH.get(), BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_CABBAGE = registerCompatBlock("farmersdelight", "overweight_cabbage_block", () -> new CropFullBlock(null, BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_KIWI = registerCompatBlock("hedgehog", "overweight_kiwi_block", () -> new Block(BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_SLICED_KIWI = registerCompatBlock("hedgehog", "overweight_sliced_kiwi_block", () -> new Block(BlockBehaviour.Properties.copy(OVERWEIGHT_KIWI.get())), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_GINGER = registerCompatBlock("snowyspirit", "overweight_ginger_block", () -> new CropFullBlock(OVERWEIGHT_GINGER_STEM.get(), BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> SEEDED_PEELED_MELON = registerBlock("seeded_peeled_melon", () -> new PeeledMelonBlock(PeeledMelonBlock.SeedState.SEEDED, BlockBehaviour.Properties.copy(Blocks.MELON).sound(SoundType.WET_GRASS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HALF_SEEDED_PEELED_MELON = registerBlock("half_seeded_peeled_melon", () -> new PeeledMelonBlock(PeeledMelonBlock.SeedState.HALF_SEEDED, BlockBehaviour.Properties.copy(Blocks.MELON).sound(SoundType.WET_GRASS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SEEDLESS_PEELED_MELON = registerBlock("seedless_peeled_melon", () -> new PeeledMelonBlock(PeeledMelonBlock.SeedState.SEEDLESS, BlockBehaviour.Properties.copy(Blocks.MELON).sound(SoundType.WET_GRASS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_SEEDED_PEELED_MELON = registerBlock("waxed_seeded_peeled_melon", () -> new Block(BlockBehaviour.Properties.copy(SEEDED_PEELED_MELON.get())), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_HALF_SEEDED_PEELED_MELON = registerBlock("waxed_half_seeded_peeled_melon", () -> new Block(BlockBehaviour.Properties.copy(HALF_SEEDED_PEELED_MELON.get())), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WAXED_SEEDLESS_PEELED_MELON = registerBlock("waxed_seedless_peeled_melon", () -> new Block(BlockBehaviour.Properties.copy(SEEDLESS_PEELED_MELON.get())), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PEELED_OVERWEIGHT_BEETROOT = registerBlock("peeled_overweight_beetroot_block", () -> new Block(BlockBehaviour.Properties.copy(OVERWEIGHT_BEETROOT.get())), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> PEELED_OVERWEIGHT_CARROT = registerBlock("peeled_overweight_carrot_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(OVERWEIGHT_CARROT.get())), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> PEELED_OVERWEIGHT_POTATO = registerBlock("peeled_overweight_potato_block", () -> new Block(BlockBehaviour.Properties.copy(OVERWEIGHT_POTATO.get())), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> PEELED_OVERWEIGHT_ONION = registerCompatBlock("farmersdelight", "peeled_overweight_onion_block", () -> new Block(BlockBehaviour.Properties.copy(OVERWEIGHT_ONION.get())), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> PEELED_OVERWEIGHT_KIWI = registerCompatBlock("hedgehog", "peeled_overweight_kiwi_block", () -> new Block(BlockBehaviour.Properties.copy(OVERWEIGHT_KIWI.get())), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> PEELED_OVERWEIGHT_GINGER = registerCompatBlock("snowyspirit", "peeled_overweight_ginger_block", () -> new Block(BlockBehaviour.Properties.copy(OVERWEIGHT_GINGER.get())), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> VEGETABLE_COMPOST = registerBlock("vegetable_compost", () -> new Block(BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.MOSS)), CreativeModeTab.TAB_MISC);

    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> block, CreativeModeTab tab) {
        RegistryObject<B> blocks = BLOCKS.register(name, block);
        OFItems.ITEMS.register(name, () -> new BlockItem(blocks.get(), new Item.Properties().tab(tab)));
        return blocks;
    }

    public static <B extends Block> RegistryObject<B> registerNoTabBlock(String name, Supplier<? extends B> block) {
        return BLOCKS.register(name, block);
    }

    public static <B extends Block> RegistryObject<B> registerCompatBlock(String modid, String name, Supplier<? extends B> block, CreativeModeTab tab) {
        return registerBlock(name, block, ModList.get().isLoaded(modid) ? tab : null);
    }

}
