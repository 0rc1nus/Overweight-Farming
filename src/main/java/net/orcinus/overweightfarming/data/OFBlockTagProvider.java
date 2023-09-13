package net.orcinus.overweightfarming.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.common.blocks.CropFullBlock;
import net.orcinus.overweightfarming.common.blocks.PeeledMelonBlock;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import net.orcinus.overweightfarming.common.registry.OFTags;

import java.util.concurrent.CompletableFuture;

public class OFBlockTagProvider extends FabricTagProvider.BlockTagProvider {


    public OFBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        //Vanilla Tags
        for (Block block : OFObjects.BLOCKS.keySet().stream().filter(block -> block instanceof CropFullBlock).toList()) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(block);
        }
        for (Block block : OFObjects.BLOCKS.keySet().stream().filter(block -> block instanceof PeeledMelonBlock).toList()) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(block);
        }

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(OFObjects.VEGETABLE_COMPOST);

        getOrCreateTagBuilder(BlockTags.REPLACEABLE).add(OFObjects.ALLIUM_BUSH);
        getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(OFObjects.ALLIUM_BUSH);

        getOrCreateTagBuilder(OFTags.OVERWEIGHT_OBSTACLES).add(Blocks.WITHER_ROSE).add(Blocks.POTTED_WITHER_ROSE);

        getOrCreateTagBuilder(OFTags.OVERWEIGHT_COMPAT)
                .addOptional(new Identifier("bewitchment", "garlic"))
                .addOptional(new Identifier("bewitchment", "mandrake"))
                .addOptional(new Identifier("bwplus", "bloodroot"))
                .addOptional(new Identifier("immersive_weathering", "weeds"))

        ;

        getOrCreateTagBuilder(OFTags.OVERWEIGHT_APPLE_LEAVES)
                .add(Blocks.OAK_LEAVES)
                .add(Blocks.DARK_OAK_LEAVES);

    }
}