package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.blocks.CropFullBlock;
import net.orcinus.overweightfarming.blocks.PeeledMelonBlock;
import net.orcinus.overweightfarming.registry.OFObjects;
import net.orcinus.overweightfarming.registry.OFTags;

public class OFBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public OFBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        //Vanilla Tags
        for(Block block : OFObjects.BLOCKS.keySet().stream().filter(block -> block instanceof CropFullBlock).toList()){
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(block);
        }
        for(Block block : OFObjects.BLOCKS.keySet().stream().filter(block -> block instanceof PeeledMelonBlock).toList()){
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(block);
        }

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(OFObjects.VEGETABLE_COMPOST);

        getOrCreateTagBuilder(BlockTags.REPLACEABLE_PLANTS).add(OFObjects.ALLIUM_BUSH);
        getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(OFObjects.ALLIUM_BUSH);

        getOrCreateTagBuilder(OFTags.OVERWEIGHT_OBSTACLES).add(Blocks.WITHER_ROSE);
        getOrCreateTagBuilder(OFTags.OVERWEIGHT_OBSTACLES).add(Blocks.POTTED_WITHER_ROSE);

        getOrCreateTagBuilder(OFTags.OVERWEIGHT_COMPAT).addOptional(new Identifier("bewitchment","garlic"));
        getOrCreateTagBuilder(OFTags.OVERWEIGHT_COMPAT).addOptional(new Identifier("bewitchment","mandrake"));
        getOrCreateTagBuilder(OFTags.OVERWEIGHT_COMPAT).addOptional(new Identifier("bwplus","bloodroot"));
        getOrCreateTagBuilder(OFTags.OVERWEIGHT_COMPAT).addOptional(new Identifier("immersive_weathering","weeds"));

    }
}