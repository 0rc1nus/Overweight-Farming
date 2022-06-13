package net.orcinus.overweightfarming.blocks;

import net.minecraft.world.level.block.Block;

public class OverweightCocoaBlock extends CropFullBlock {

    public OverweightCocoaBlock(Block stemBlock, Properties properties) {
        super(stemBlock, properties);
    }

    @Override
    public boolean shouldGrowRoots() {
        return false;
    }
}
