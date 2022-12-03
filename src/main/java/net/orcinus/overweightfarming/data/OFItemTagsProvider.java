package net.orcinus.overweightfarming.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFItemTags;
import org.jetbrains.annotations.Nullable;

public class OFItemTagsProvider extends ItemTagsProvider {

    public OFItemTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, new OFBlockTagsProvider(dataGenerator, existingFileHelper), OverweightFarming.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ItemTags.TALL_FLOWERS).add(OFBlocks.ALLIUM_BUSH.get().asItem());
        this.tag(OFItemTags.OVERWEIGHT_HARVESTABLES).add(Items.DIAMOND_HOE, Items.GOLDEN_HOE, Items.IRON_HOE, Items.NETHERITE_HOE, Items.STONE_HOE, Items.WOODEN_HOE);
    }
}
