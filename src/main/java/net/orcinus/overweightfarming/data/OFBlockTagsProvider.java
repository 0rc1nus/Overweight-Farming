package net.orcinus.overweightfarming.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.init.OFBlockTags;
import net.orcinus.overweightfarming.init.OFBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class OFBlockTagsProvider extends BlockTagsProvider {

    public OFBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, OverweightFarming.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(OFBlockTags.OVERWEIGHT_APPLE_LEAVES).add(Blocks.OAK_LEAVES, Blocks.DARK_OAK_LEAVES);
        this.tag(OFBlockTags.OVERWEIGHT_OBSTACLES).add(Blocks.WITHER_ROSE, Blocks.POTTED_WITHER_ROSE);
        this.tag(BlockTags.REPLACEABLE).add(OFBlocks.ALLIUM_BUSH.get());
        this.tag(BlockTags.TALL_FLOWERS).add(OFBlocks.ALLIUM_BUSH.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                OFBlocks.OVERWEIGHT_BEETROOT.get(),
                OFBlocks.OVERWEIGHT_CARROT.get(),
                OFBlocks.OVERWEIGHT_POTATO.get(),
                OFBlocks.OVERWEIGHT_COCOA.get(),
                OFBlocks.OVERWEIGHT_ONION.get(),
                OFBlocks.OVERWEIGHT_CABBAGE.get(),
                OFBlocks.OVERWEIGHT_SLICED_KIWI.get(),
                OFBlocks.OVERWEIGHT_KIWI.get(),
                OFBlocks.OVERWEIGHT_GINGER.get(),
                OFBlocks.PEELED_OVERWEIGHT_BEETROOT.get(),
                OFBlocks.PEELED_OVERWEIGHT_CARROT.get(),
                OFBlocks.PEELED_OVERWEIGHT_POTATO.get(),
                OFBlocks.PEELED_OVERWEIGHT_COCOA.get(),
                OFBlocks.PEELED_OVERWEIGHT_ONION.get(),
                OFBlocks.PEELED_OVERWEIGHT_KIWI.get(),
                OFBlocks.PEELED_OVERWEIGHT_GINGER.get(),
                OFBlocks.SEEDED_PEELED_MELON.get(),
                OFBlocks.SEEDLESS_PEELED_MELON.get(),
                OFBlocks.HALF_SEEDED_PEELED_MELON.get(),
                OFBlocks.WAXED_SEEDED_PEELED_MELON.get(),
                OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get(),
                OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get(),
                OFBlocks.OVERWEIGHT_BAKED_POTATO.get(),
                OFBlocks.OVERWEIGHT_NETHER_WART.get(),
                OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get(),
                OFBlocks.OVERWEIGHT_APPLE.get(),
                OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                OFBlocks.OVERWEIGHT_BEETROOT.get(),
                OFBlocks.OVERWEIGHT_CARROT.get(),
                OFBlocks.OVERWEIGHT_POTATO.get(),
                OFBlocks.OVERWEIGHT_COCOA.get(),
                OFBlocks.OVERWEIGHT_ONION.get(),
                OFBlocks.OVERWEIGHT_CABBAGE.get(),
                OFBlocks.OVERWEIGHT_SLICED_KIWI.get(),
                OFBlocks.OVERWEIGHT_KIWI.get(),
                OFBlocks.OVERWEIGHT_GINGER.get(),
                OFBlocks.PEELED_OVERWEIGHT_BEETROOT.get(),
                OFBlocks.PEELED_OVERWEIGHT_CARROT.get(),
                OFBlocks.PEELED_OVERWEIGHT_POTATO.get(),
                OFBlocks.PEELED_OVERWEIGHT_COCOA.get(),
                OFBlocks.PEELED_OVERWEIGHT_ONION.get(),
                OFBlocks.PEELED_OVERWEIGHT_KIWI.get(),
                OFBlocks.PEELED_OVERWEIGHT_GINGER.get(),
                OFBlocks.SEEDED_PEELED_MELON.get(),
                OFBlocks.SEEDLESS_PEELED_MELON.get(),
                OFBlocks.HALF_SEEDED_PEELED_MELON.get(),
                OFBlocks.WAXED_SEEDED_PEELED_MELON.get(),
                OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get(),
                OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get(),
                OFBlocks.OVERWEIGHT_BAKED_POTATO.get(),
                OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get(),
                OFBlocks.OVERWEIGHT_NETHER_WART.get(),
                OFBlocks.OVERWEIGHT_APPLE.get(),
                OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get(),
                OFBlocks.VEGETABLE_COMPOST.get()
        );
    }

}
