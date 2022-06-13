package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.blocks.CropFullBlock;
import net.orcinus.overweightfarming.blocks.OverweightWeedBlock;
import net.orcinus.overweightfarming.registry.OFObjects;
import net.orcinus.overweightfarming.util.TripleBlockHalf;

import java.util.Optional;
import java.util.function.Consumer;

import static net.minecraft.data.client.BlockStateModelGenerator.createSingletonBlockState;
import static net.minecraft.data.client.TextureMap.getSubId;
import static net.minecraft.data.client.TexturedModel.makeFactory;

public class OFModelProvider extends FabricModelProvider {
    public static final Model TEMPLATE_OVERWEIGHT_POT_MODEL = block("overweight_pot", TextureKey.PLANT);
    public static final TexturedModel.Factory TEMPLATE_OVERWEIGHT_POT = makeFactory(TextureMap::plant, TEMPLATE_OVERWEIGHT_POT_MODEL);
    public static final Model TEMPLATE_OVERWEIGHT_CABBAGE_POT_MODEL = block("overweight_cabbage_pot", TextureKey.PLANT);
    public static final TexturedModel.Factory TEMPLATE_OVERWEIGHT_CABBAGE_POT = makeFactory(TextureMap::plant, TEMPLATE_OVERWEIGHT_CABBAGE_POT_MODEL);
    public static final Model TEMPLATE_OVERWEIGHT_NETHER_WART_POT_MODEL = block("overweight_nether_wart_pot", TextureKey.PLANT);
    public static final TexturedModel.Factory TEMPLATE_OVERWEIGHT_NETHER_WART_POT = makeFactory(TextureMap::plant, TEMPLATE_OVERWEIGHT_NETHER_WART_POT_MODEL);
    public static final Model TEMPLATE_OVERWEIGHT_ONION_POT_MODEL = block("overweight_onion_pot", TextureKey.PLANT);
    public static final TexturedModel.Factory TEMPLATE_OVERWEIGHT_ONION_POT = makeFactory(TextureMap::plant, TEMPLATE_OVERWEIGHT_ONION_POT_MODEL);

    public static final Model ORIENTABLE_VERTICAL = block("orientable_vertical", TextureKey.FRONT, TextureKey.SIDE);
    public OFModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for(Block block : OFObjects.BLOCKS.keySet().stream().filter(block -> block instanceof CropFullBlock && !block.getDefaultState().isOf(OFObjects.OVERWEIGHT_WEED) && !block.getDefaultState().isOf(OFObjects.OVERWEIGHT_KIWI) && !block.getDefaultState().isOf(OFObjects.OVERWEIGHT_SLICED_KIWI) && !block.getDefaultState().isOf(OFObjects.PEELED_OVERWEIGHT_KIWI)).toList()){
            blockStateModelGenerator.registerSingleton(block, TexturedModel.CUBE_BOTTOM_TOP);
        }

        //STEMS
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_APPLE_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_BEETROOT_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_BLOODROOT_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_CABBAGE_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_CARROT_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_GARLIC_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_GINGER_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_GOLDEN_APPLE_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_MANDRAKE_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_NETHER_WART_STEM, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerTintableCross(OFObjects.OVERWEIGHT_POTATO_STEM, BlockStateModelGenerator.TintType.TINTED);

        //MISC
        blockStateModelGenerator.registerSimpleCubeAll(OFObjects.OVERWEIGHT_KIWI);
        blockStateModelGenerator.registerCubeAllModelTexturePool(OFObjects.VEGETABLE_COMPOST);
        blockStateModelGenerator.registerDoubleBlock(OFObjects.ALLIUM_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSingleton(OFObjects.OVERWEIGHT_BAKED_POTATO, bakedPotatoMap(OFObjects.OVERWEIGHT_BAKED_POTATO), Models.CUBE);
        registerTripleBlock(blockStateModelGenerator.blockStateCollector, OFObjects.OVERWEIGHT_WEED,
                getSubId(OFObjects.OVERWEIGHT_WEED, "_flower"),
                getSubId(OFObjects.OVERWEIGHT_WEED, "_stem"),
                getSubId(OFObjects.OVERWEIGHT_WEED, "_stem")
        );
        blockStateModelGenerator.registerSimpleCubeAll(OFObjects.WAXED_SEEDLESS_PEELED_MELON);
        blockStateModelGenerator.registerSimpleCubeAll(OFObjects.WAXED_SEEDED_PEELED_MELON);
        blockStateModelGenerator.registerSimpleCubeAll(OFObjects.WAXED_HALF_SEEDED_PEELED_MELON);

        //PEELED
        blockStateModelGenerator.registerCubeAllModelTexturePool(OFObjects.PEELED_OVERWEIGHT_BEETROOT);
        blockStateModelGenerator.registerSingleton(OFObjects.PEELED_OVERWEIGHT_CARROT, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(OFObjects.PEELED_OVERWEIGHT_COCOA, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(OFObjects.PEELED_OVERWEIGHT_GINGER, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(OFObjects.PEELED_OVERWEIGHT_ONION, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerCubeAllModelTexturePool(OFObjects.PEELED_OVERWEIGHT_POTATO);
        blockStateModelGenerator.registerCubeAllModelTexturePool(OFObjects.SEEDED_PEELED_MELON);
        blockStateModelGenerator.registerCubeAllModelTexturePool(OFObjects.SEEDLESS_PEELED_MELON);
        blockStateModelGenerator.registerCubeAllModelTexturePool(OFObjects.HALF_SEEDED_PEELED_MELON);

        //POTS
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_APPLE);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_BEETROOT);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_CARROT);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_COCOA);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_GINGER);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_GOLDEN_APPLE);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_POISONOUS_POTATO);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_POTATO);

        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_KIWI);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_NETHER_WART);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_CABBAGE);
        registerPot(blockStateModelGenerator, OFObjects.POTTED_OVERWEIGHT_ONION);

        String kiwiBlockFront = "block/overweight_sliced_kiwi_block_front";
        registerSliced(blockStateModelGenerator, OFObjects.PEELED_OVERWEIGHT_KIWI,"block/peeled_overweight_kiwi_block",kiwiBlockFront);
        registerSliced(blockStateModelGenerator, OFObjects.OVERWEIGHT_SLICED_KIWI,"block/overweight_kiwi_block",kiwiBlockFront);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(OFObjects.STRAW_HAT, Models.GENERATED);
        itemModelGenerator.register(OFObjects.MELON_JUICE, Models.GENERATED);
        itemModelGenerator.register(OFObjects.VEGETABLE_PEELS, Models.GENERATED);
    }

    public final void registerSliced(BlockStateModelGenerator blockStateModelGenerator, Block block, String sideIdentifier, String frontIdentifier) {
        TextureMap textureMap = (new TextureMap()).put(TextureKey.SIDE, new Identifier(OverweightFarming.MODID, sideIdentifier)).put(TextureKey.FRONT, new Identifier(OverweightFarming.MODID, frontIdentifier));
        Identifier identifier = ORIENTABLE_VERTICAL.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(block, identifier));
    }

    public final void registerPot(BlockStateModelGenerator blockStateModelGenerator, Block potted) {
        Identifier identifier;
        if(potted.getDefaultState().isOf(OFObjects.POTTED_OVERWEIGHT_ONION)){
            identifier = TEMPLATE_OVERWEIGHT_ONION_POT.upload(potted, blockStateModelGenerator.modelCollector);
        }else if(potted.getDefaultState().isOf(OFObjects.POTTED_OVERWEIGHT_CABBAGE)){
            identifier = TEMPLATE_OVERWEIGHT_CABBAGE_POT.upload(potted, blockStateModelGenerator.modelCollector);
        }else if(potted.getDefaultState().isOf(OFObjects.POTTED_OVERWEIGHT_NETHER_WART)){
            identifier = TEMPLATE_OVERWEIGHT_NETHER_WART_POT.upload(potted, blockStateModelGenerator.modelCollector);
        }else{
            identifier = TEMPLATE_OVERWEIGHT_POT.upload(potted, blockStateModelGenerator.modelCollector);
        }


        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(potted, identifier));
    }

    public final void registerTripleBlock(Consumer<BlockStateSupplier> blockStateCollector, Block block, Identifier upperHalfModelId, Identifier middleHalfModelId , Identifier lowerHalfModelId) {
        var fluffId = getSubId(OFObjects.OVERWEIGHT_WEED, "_fluff");
        blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateVariantMap.create(TripleBlockHalf.TRIPLE_BLOCK_HALF, OverweightWeedBlock.FLUFF).register(TripleBlockHalf.LOWER, false, BlockStateVariant.create().put(VariantSettings.MODEL, lowerHalfModelId)).register(TripleBlockHalf.MIDDLE, false, BlockStateVariant.create().put(VariantSettings.MODEL, middleHalfModelId)).register(TripleBlockHalf.UPPER,false, BlockStateVariant.create().put(VariantSettings.MODEL, upperHalfModelId)).register(TripleBlockHalf.LOWER, true, BlockStateVariant.create().put(VariantSettings.MODEL, fluffId)).register(TripleBlockHalf.MIDDLE, true, BlockStateVariant.create().put(VariantSettings.MODEL, fluffId)).register(TripleBlockHalf.UPPER,true, BlockStateVariant.create().put(VariantSettings.MODEL, fluffId))));
    }


    public static TextureMap bakedPotatoMap(Block block) {
        return (new TextureMap()).put(TextureKey.PARTICLE, getSubId(block, "_top")).put(TextureKey.DOWN, getSubId(block, "_bottom")).put(TextureKey.UP, getSubId(block, "_top")).put(TextureKey.NORTH, getSubId(block, "_north_south")).put(TextureKey.EAST, getSubId(block, "_east_west")).put(TextureKey.SOUTH, getSubId(block, "_north_south")).put(TextureKey.WEST, getSubId(block, "_east_west"));
    }


    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(OverweightFarming.MODID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
}
