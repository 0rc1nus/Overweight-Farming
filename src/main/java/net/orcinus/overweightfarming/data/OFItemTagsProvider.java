package net.orcinus.overweightfarming.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class OFItemTagsProvider extends ItemTagsProvider {

    public OFItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTagLookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, blockTagLookup, OverweightFarming.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TALL_FLOWERS).add(OFBlocks.ALLIUM_BUSH.get().asItem());
        this.tag(OFItemTags.OVERWEIGHT_HARVESTABLES).add(Items.DIAMOND_HOE, Items.GOLDEN_HOE, Items.IRON_HOE, Items.NETHERITE_HOE, Items.STONE_HOE, Items.WOODEN_HOE);
    }

}
