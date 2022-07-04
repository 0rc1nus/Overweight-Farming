package net.orcinus.overweightfarming.common.blocks;

import net.minecraft.block.Block;

public class OverweightCabbageBlock extends CropFullBlock{
    public OverweightCabbageBlock(Block stemBlock, Settings properties) {
        super(stemBlock, properties);
    }

    @Override
    public boolean shouldGrowRoots() {
        return false;
    }
}
