package net.orcinus.overweightfarming.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.init.OFPaintingVariants;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class OFPaintingTagsProvider extends PaintingVariantTagsProvider {

    public OFPaintingTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, OverweightFarming.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(PaintingVariantTags.PLACEABLE).addOptional(OFPaintingVariants.FUN_LAND.location());
    }
}
