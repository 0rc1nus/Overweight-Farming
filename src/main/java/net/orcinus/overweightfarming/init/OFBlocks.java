package net.orcinus.overweightfarming.init;

import com.google.common.collect.Maps;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.blocks.CropFullBlock;
import net.orcinus.overweightfarming.blocks.CropStemBlock;
import net.orcinus.overweightfarming.blocks.NetherCropFullBlock;
import net.orcinus.overweightfarming.blocks.NetherCropStemBlock;
import net.orcinus.overweightfarming.blocks.OverweightAppleBlock;
import net.orcinus.overweightfarming.blocks.OverweightCarrotBlock;
import net.orcinus.overweightfarming.blocks.OverweightCocoaBlock;
import net.orcinus.overweightfarming.blocks.OverweightOnionBlock;
import net.orcinus.overweightfarming.blocks.OverweightPotatoBlock;
import net.orcinus.overweightfarming.blocks.PeeledMelonBlock;

import java.util.Map;
import java.util.function.Supplier;

public class OFBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, OverweightFarming.MODID);
    public static final Map<DeferredHolder<Block, Block>, String> COMPAT = Maps.newLinkedHashMap();

    public static final DeferredHolder<Block, Block> OVERWEIGHT_BEETROOT_STEM = registerNoTabBlock("overweight_beetroot_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of().instabreak().noCollission().noLootTable().sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_CARROT_STEM = registerNoTabBlock("overweight_carrot_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of().instabreak().noCollission().noLootTable().sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_POTATO_STEM = registerNoTabBlock("overweight_potato_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of().instabreak().noCollission().noLootTable().sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_GINGER_STEM = registerNoTabBlock("overweight_ginger_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of().instabreak().noCollission().noLootTable().sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_NETHER_WART_STEM = registerNoTabBlock("overweight_nether_wart_stem", () -> new NetherCropStemBlock(BlockBehaviour.Properties.of().instabreak().noCollission().noLootTable().sound(SoundType.NETHER_WART)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_APPLE_STEM = registerNoTabBlock("overweight_apple_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of().instabreak().noCollission().noLootTable().sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_GOLDEN_APPLE_STEM = registerNoTabBlock("overweight_golden_apple_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of().instabreak().noCollission().noLootTable().sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_BEETROOT = registerBlock("overweight_beetroot_block", () -> new CropFullBlock(OVERWEIGHT_BEETROOT_STEM.get(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_CARROT = registerBlock("overweight_carrot_block", () -> new OverweightCarrotBlock(OVERWEIGHT_CARROT_STEM.get(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_COCOA = registerBlock("overweight_cocoa_block", () -> new OverweightCocoaBlock(null, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_POTATO = registerBlock("overweight_potato_block", () -> new OverweightPotatoBlock(OVERWEIGHT_POTATO_STEM.get(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_BAKED_POTATO = registerBlock("overweight_baked_potato_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_NETHER_WART = registerBlock("overweight_nether_wart_block", () -> new NetherCropFullBlock(OVERWEIGHT_NETHER_WART_STEM.get(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_POISONOUS_POTATO = registerBlock("overweight_poisonous_potato_block", () -> new CropFullBlock(null, BlockBehaviour.Properties.ofFullCopy(OVERWEIGHT_BAKED_POTATO.get())));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_APPLE = registerBlock("overweight_apple_block", () -> new OverweightAppleBlock(false, OVERWEIGHT_APPLE_STEM.get(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_GOLDEN_APPLE = registerBlock("overweight_golden_apple_block", () -> new OverweightAppleBlock(true, OVERWEIGHT_GOLDEN_APPLE_STEM.get(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> ALLIUM_BUSH = registerCompatBlock("farmersdelight", "allium_bush", () -> new TallFlowerBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_CABBAGE_STEM = registerNoTabBlock("overweight_cabbage_stem", () -> new CropStemBlock(BlockBehaviour.Properties.of().instabreak().noCollission().noLootTable().sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> VEGETABLE_COMPOST = registerBlock("vegetable_compost", () -> new Block(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.MOSS)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_ONION = registerCompatBlock("farmersdelight", "overweight_onion_block", () -> new OverweightOnionBlock(ALLIUM_BUSH.get(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_CABBAGE = registerCompatBlock("farmersdelight", "overweight_cabbage_block", () -> new CropFullBlock(OVERWEIGHT_CABBAGE_STEM.get(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_KIWI = registerCompatBlock("hedgehog", "overweight_kiwi_block", () -> new CropFullBlock(null, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_SLICED_KIWI = registerCompatBlock("hedgehog", "overweight_sliced_kiwi_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(OVERWEIGHT_KIWI.get())));
    public static final DeferredHolder<Block, Block> OVERWEIGHT_GINGER = registerCompatBlock("snowyspirit", "overweight_ginger_block", () -> new CropFullBlock(OVERWEIGHT_GINGER_STEM.get(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.CROP)));
    public static final DeferredHolder<Block, Block> SEEDED_PEELED_MELON = registerBlock("seeded_peeled_melon", () -> new PeeledMelonBlock(PeeledMelonBlock.SeedState.SEEDED, BlockBehaviour.Properties.ofFullCopy(Blocks.MELON).sound(SoundType.WET_GRASS)));
    public static final DeferredHolder<Block, Block> HALF_SEEDED_PEELED_MELON = registerBlock("half_seeded_peeled_melon", () -> new PeeledMelonBlock(PeeledMelonBlock.SeedState.HALF_SEEDED, BlockBehaviour.Properties.ofFullCopy(Blocks.MELON).sound(SoundType.WET_GRASS)));
    public static final DeferredHolder<Block, Block> SEEDLESS_PEELED_MELON = registerBlock("seedless_peeled_melon", () -> new PeeledMelonBlock(PeeledMelonBlock.SeedState.SEEDLESS, BlockBehaviour.Properties.ofFullCopy(Blocks.MELON).sound(SoundType.WET_GRASS)));
    public static final DeferredHolder<Block, Block> WAXED_SEEDED_PEELED_MELON = registerBlock("waxed_seeded_peeled_melon", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SEEDED_PEELED_MELON.get())));
    public static final DeferredHolder<Block, Block> WAXED_HALF_SEEDED_PEELED_MELON = registerBlock("waxed_half_seeded_peeled_melon", () -> new Block(BlockBehaviour.Properties.ofFullCopy(HALF_SEEDED_PEELED_MELON.get())));
    public static final DeferredHolder<Block, Block> WAXED_SEEDLESS_PEELED_MELON = registerBlock("waxed_seedless_peeled_melon", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SEEDLESS_PEELED_MELON.get())));
    public static final DeferredHolder<Block, Block> PEELED_OVERWEIGHT_BEETROOT = registerBlock("peeled_overweight_beetroot_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(OVERWEIGHT_BEETROOT.get())));
    public static final DeferredHolder<Block, Block> PEELED_OVERWEIGHT_CARROT = registerBlock("peeled_overweight_carrot_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(OVERWEIGHT_CARROT.get())));
    public static final DeferredHolder<Block, Block> PEELED_OVERWEIGHT_POTATO = registerBlock("peeled_overweight_potato_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(OVERWEIGHT_POTATO.get())));
    public static final DeferredHolder<Block, Block> PEELED_OVERWEIGHT_COCOA = registerBlock("peeled_overweight_cocoa_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(OVERWEIGHT_COCOA.get())));
    public static final DeferredHolder<Block, Block> PEELED_OVERWEIGHT_ONION = registerCompatBlock("farmersdelight", "peeled_overweight_onion_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(OVERWEIGHT_ONION.get())));
    public static final DeferredHolder<Block, Block> PEELED_OVERWEIGHT_KIWI = registerCompatBlock("hedgehog", "peeled_overweight_kiwi_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(OVERWEIGHT_KIWI.get())));
    public static final DeferredHolder<Block, Block> PEELED_OVERWEIGHT_GINGER = registerCompatBlock("snowyspirit", "peeled_overweight_ginger_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(OVERWEIGHT_GINGER.get())));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_BEETROOT = registerNoTabBlock("potted_overweight_beetroot", () -> new FlowerPotBlock(OVERWEIGHT_BEETROOT.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_CARROT = registerNoTabBlock("potted_overweight_carrot", () -> new FlowerPotBlock(OVERWEIGHT_CARROT.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_POTATO = registerNoTabBlock("potted_overweight_potato", () -> new FlowerPotBlock(OVERWEIGHT_POTATO.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_COCOA = registerNoTabBlock("potted_overweight_cocoa", () -> new FlowerPotBlock(OVERWEIGHT_COCOA.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_APPLE = registerNoTabBlock("potted_overweight_apple", () -> new FlowerPotBlock(OVERWEIGHT_APPLE.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_GOLDEN_APPLE = registerNoTabBlock("potted_overweight_golden_apple", () -> new FlowerPotBlock(OVERWEIGHT_GOLDEN_APPLE.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_ONION = registerNoTabBlock("potted_overweight_onion", () -> new FlowerPotBlock(OVERWEIGHT_ONION.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_CABBAGE = registerNoTabBlock("potted_overweight_cabbage", () -> new FlowerPotBlock(OVERWEIGHT_CABBAGE.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_GINGER = registerNoTabBlock("potted_overweight_ginger", () -> new FlowerPotBlock(OVERWEIGHT_GINGER.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_KIWI = registerNoTabBlock("potted_overweight_kiwi", () -> new FlowerPotBlock(OVERWEIGHT_KIWI.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_POISONOUS_POTATO = registerNoTabBlock("potted_overweight_poisonous_potato", () -> new FlowerPotBlock(OVERWEIGHT_POISONOUS_POTATO.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final DeferredHolder<Block, Block> POTTED_OVERWEIGHT_NETHER_WART = registerNoTabBlock("potted_overweight_nether_wart", () -> new FlowerPotBlock(OVERWEIGHT_NETHER_WART.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()));

    public static DeferredHolder<Block, Block> registerCompatBlock(String modid, String name, Supplier<? extends Block> block) {
        DeferredHolder<Block, Block> blocks = registerBlock(name, block);
        COMPAT.put(blocks, modid);
        return blocks;
    }

    public static DeferredHolder<Block, Block> registerBlock(String name, Supplier<? extends Block> block) {
        DeferredHolder<Block, Block> blocks = registerNoTabBlock(name, block);
        OFItems.ITEMS.register(name, () -> new BlockItem(blocks.get(), new Item.Properties()));
        return blocks;
    }

    public static DeferredHolder<Block, Block> registerNoTabBlock(String name, Supplier<? extends Block> block) {
        return BLOCKS.register(name, block);
    }

}
