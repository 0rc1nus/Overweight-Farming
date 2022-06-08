package net.orcinus.overweightfarming.util;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraftforge.registries.ForgeRegistries;
import net.orcinus.overweightfarming.blocks.CropFullBlock;
import net.orcinus.overweightfarming.blocks.OverweightCarrotBlock;
import net.orcinus.overweightfarming.init.OFBlockTags;
import net.orcinus.overweightfarming.init.OFBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import net.minecraft.util.RandomSource;

public record OverweightGrowthManager(RandomSource random) {

    public Map<Block, Pair<OverweightType, Block>> getOverweightMap() {
        return Util.make(Maps.newHashMap(), map -> {
            map.put(Blocks.CARROTS, Pair.of(OverweightType.SPROUT, OFBlocks.OVERWEIGHT_CARROT.get()));
            map.put(Blocks.POTATOES, Pair.of(OverweightType.DEFAULT, this.random.nextInt(20) == 0 ? OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get() : OFBlocks.OVERWEIGHT_POTATO.get()));
            map.put(Blocks.BEETROOTS, Pair.of(OverweightType.DEFAULT, OFBlocks.OVERWEIGHT_BEETROOT.get()));
            map.put(Blocks.COCOA, Pair.of(OverweightType.SIMPLE, OFBlocks.OVERWEIGHT_COCOA.get()));
            map.put(this.getCompatBlock("farmersdelight", "cabbages"), Pair.of(OverweightType.SIMPLE, OFBlocks.OVERWEIGHT_CABBAGE.get()));
            map.put(this.getCompatBlock("farmersdelight", "onions"), Pair.of(OverweightType.DEFAULT, OFBlocks.OVERWEIGHT_ONION.get()));
            map.put(this.getCompatBlock("hedgehog", "kiwi_vines"), Pair.of(OverweightType.SIMPLE, OFBlocks.OVERWEIGHT_KIWI.get()));
            map.put(this.getCompatBlock("snowyspirit", "ginger"), Pair.of(OverweightType.DEFAULT, OFBlocks.OVERWEIGHT_GINGER.get()));
        });
    }

    public void growOverweightCrops(ServerLevel serverLevel, BlockPos blockPos, BlockState state, RandomSource random) {
        for (Block block : this.getOverweightMap().keySet()) {
            if (state.is(block)) {
                if (!this.isNearOvergrowthObstacles(serverLevel, blockPos)) return;
                Pair<OverweightType, Block> pair = this.getOverweightMap().get(block);
                OverweightType overweightType = pair.getFirst();
                Block overweightBlock = pair.getSecond();
                if (overweightBlock instanceof CropFullBlock cropFullBlock) {
                    BlockState overweightState = cropFullBlock instanceof OverweightCarrotBlock carrotBlock ? carrotBlock.defaultBlockState().setValue(OverweightCarrotBlock.FACING, Direction.UP) : cropFullBlock.defaultBlockState();
                    Block stemBlock = cropFullBlock.getStemBlock();
                    BlockState stemState = null;
                    if (stemBlock != null) stemState = stemBlock.defaultBlockState();
                    switch (overweightType) {
                        case DEFAULT -> this.simpleOverweightGrowth(serverLevel, blockPos, overweightState, stemState);
                        case SIMPLE -> this.setBlock(serverLevel, blockPos, overweightState);
                        case SPROUT -> this.sproutGrowth(serverLevel, blockPos, random, overweightState, stemState);
                        case INVERTED -> this.invertedGrowth(serverLevel, blockPos, overweightState, stemState);
                    }
                }
            }
        }
    }

    private void invertedGrowth(ServerLevel world, BlockPos blockPos, BlockState overweightState, BlockState stemState) {
        if (!world.isStateAtPosition(blockPos.above(), DripstoneUtils::isEmptyOrWater)) {
            return;
        }
        this.setBlock(world, blockPos.above(), overweightState);
        this.setBlock(world, blockPos, stemState);
    }

    @Nullable
    private Block getCompatBlock(String modid, String name) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modid, name));
    }

    private void simpleOverweightGrowth(ServerLevel world, BlockPos blockPos, BlockState overweightCrop, BlockState stemBlock) {
        this.setBlock(world, blockPos, overweightCrop);
        if (stemBlock != null) {
            if (stemBlock.getBlock() instanceof DoublePlantBlock) {
                boolean flag = world.isEmptyBlock(blockPos.above()) && world.isEmptyBlock(blockPos.above(2));
                if (!flag) return;
                DoublePlantBlock.placeAt(world, stemBlock.getBlock().defaultBlockState(), blockPos.above(), 2);
            } else {
                this.setBlock(world, blockPos.above(), stemBlock);
            }
        }
    }

    private void sproutGrowth(ServerLevel world, BlockPos blockPos, RandomSource random, BlockState blockState, BlockState stemState) {
        int height = random.nextBoolean() && random.nextInt(5) == 0 ? random.nextBoolean() && random.nextInt(10) == 0 ? 4 : 3 : 2;
        BlockPos startPos = blockPos.above();
        BlockPos.MutableBlockPos mutableBlockPos = startPos.mutable();
        for (int i = 0; i < height; i++) {
            BlockState placeState = blockState;
            if (i == 0) {
                if (stemState != null) {
                    placeState = stemState;
                }
            }
            this.setBlock(world, mutableBlockPos, placeState);
            mutableBlockPos.move(Direction.DOWN);
        }
    }

    private void setBlock(ServerLevel world, BlockPos blockPos, BlockState overweightState) {
        for (Block cropBlock : this.getOverweightMap().keySet()) {
            BlockState state = world.getBlockState(blockPos);
            if (state.isAir() || state.getBlock() == cropBlock || state.is(Blocks.FARMLAND) || state.is(Blocks.DIRT)) {
                world.setBlock(blockPos, overweightState, 2);
            }
        }
    }

    private boolean isNearOvergrowthObstacles(ServerLevel world, BlockPos blockPos) {
        boolean flag = true;
        int radius = 10;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                if (world.getBlockState(pos).is(OFBlockTags.OVERWEIGHT_OBSTACLES)) {
                    flag = false;
                }
            }
        }
        return flag;
    }

}
