package net.orcinus.overweightfarming.blocks;

import net.minecraft.block.Block;

public class OverweightCocoaBlock extends CropFullBlock{
    public OverweightCocoaBlock(Block stemBlock, Settings properties) {
        super(stemBlock, properties);
    }

    @Override
    public boolean shouldGrowRoots() {
        return false;
    }
}