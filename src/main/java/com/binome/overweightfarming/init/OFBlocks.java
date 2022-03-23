package com.binome.overweightfarming.init;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.blocks.CropFullBlock;
import com.binome.overweightfarming.blocks.CropStemBlock;
import com.binome.overweightfarming.blocks.OverweightOnionBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IPlantable;
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
    public static final RegistryObject<Block> ALLIUM_BUSH = registerCompatBlock("farmersdelight", "allium_bush", () -> new TallFlowerBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> OVERWEIGHT_BEETROOT = registerBlock("overweight_beetroot_block", () -> new CropFullBlock(OVERWEIGHT_BEETROOT_STEM.get(), BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_CARROT = registerBlock("overweight_carrot_block", () -> new CropFullBlock(OVERWEIGHT_CARROT_STEM.get(), BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_COCOA = registerBlock("overweight_cocoa_block", () -> new Block(BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_POTATO = registerBlock("overweight_potato_block", () -> new CropFullBlock(OVERWEIGHT_POTATO_STEM.get(), BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_ONION = registerCompatBlock("farmersdelight", "overweight_onion_block", () -> new OverweightOnionBlock(ALLIUM_BUSH.get(), BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> OVERWEIGHT_CABBAGE = registerCompatBlock("farmersdelight", "overweight_cabbage_block", () -> new CropFullBlock(null, BlockBehaviour.Properties.of(Material.PLANT).strength(1.0F).sound(SoundType.CROP)), CreativeModeTab.TAB_FOOD);

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
