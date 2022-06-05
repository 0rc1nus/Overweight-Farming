package com.binome.overweightfarming.blocks;

import net.minecraft.block.Block;

public class OverweightAppleBlock extends CropFullBlock{
    public OverweightAppleBlock(Block stemBlock, Settings properties) {
        super(stemBlock, properties);
    }

    @Override
    public boolean shouldGrowRoots() {
        return false;
    }
}
