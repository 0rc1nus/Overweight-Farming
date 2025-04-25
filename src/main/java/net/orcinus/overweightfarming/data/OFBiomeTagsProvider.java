package net.orcinus.overweightfarming.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.init.OFBiomeTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class OFBiomeTagsProvider extends BiomeTagsProvider {

    public OFBiomeTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, OverweightFarming.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(OFBiomeTags.APPLE_TREE_GENERATES).addTag(BiomeTags.IS_FOREST);
    }
}
