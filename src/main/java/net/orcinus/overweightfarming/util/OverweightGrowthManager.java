package net.orcinus.overweightfarming.util;

import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.blocks.CropFullBlock;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.registry.OFObjects;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Random;

public record OverweightGrowthManager(Random random) {

    public Map<Block, Pair<Pair<Boolean, OverweightType>, Block>> getOverweightMap() {
        return Util.make(Maps.newHashMap(), map -> {
            map.put(Blocks.CARROTS, Pair.of(Pair.of(OverweightFarming.config.crops.allowOverweightCarrot, OverweightType.SPROUT), OFObjects.OVERWEIGHT_CARROT));
            map.put(Blocks.POTATOES, Pair.of(Pair.of(OverweightFarming.config.crops.allowOverweightPotato, OverweightType.DEFAULT), OFObjects.OVERWEIGHT_POTATO));
            map.put(Blocks.BEETROOTS, Pair.of(Pair.of(OverweightFarming.config.crops.allowOverweightBeetroot, OverweightType.DEFAULT), OFObjects.OVERWEIGHT_BEETROOT));
            map.put(Blocks.COCOA, Pair.of(Pair.of(OverweightFarming.config.crops.allowOverweightCocoa, OverweightType.SIMPLE), OFObjects.OVERWEIGHT_COCOA));
            map.put(getCompatBlock("farmersdelight", "cabbages"), Pair.of(Pair.of(OverweightFarming.config.compatCrops.allowOverweightCabbage, OverweightType.SIMPLE), OFObjects.OVERWEIGHT_CABBAGE));
            map.put(getCompatBlock("farmersdelight", "onions"), Pair.of(Pair.of(OverweightFarming.config.compatCrops.allowOverweightOnion, OverweightType.DEFAULT), OFObjects.OVERWEIGHT_ONION));
            map.put(getCompatBlock("bewitchment", "mandrake"), Pair.of(Pair.of(OverweightFarming.config.compatCrops.allowOverweightMandrake, OverweightType.DEFAULT), OFObjects.OVERWEIGHT_MANDRAKE));
            map.put(getCompatBlock("bewitchment", "garlic"), Pair.of(Pair.of(OverweightFarming.config.compatCrops.allowOverweightGarlic, OverweightType.DEFAULT), OFObjects.OVERWEIGHT_GARLIC));
            map.put(getCompatBlock("bwplus", "bloodroot"), Pair.of(Pair.of(OverweightFarming.config.compatCrops.allowOverweightBloodroot, OverweightType.DEFAULT), OFObjects.OVERWEIGHT_BLOODROOT));
        });
    }

    public void growOverweightCrops(ServerWorld serverLevel, BlockPos blockPos, BlockState state, Random random) {
        for (Block block : this.getOverweightMap().keySet()) {
            if (state.isOf(block)) {
                Pair<Pair<Boolean, OverweightType>, Block> pair = this.getOverweightMap().get(block);
                Pair<Boolean, OverweightType> firstPair = pair.getFirst();
                Boolean configValue = firstPair.getFirst();
                if (!configValue) return;
                OverweightType overweightType = firstPair.getSecond();
                Block overweightBlock = pair.getSecond();
                BlockState overweightState = overweightBlock.getDefaultState();
                Block stemBlock = ((CropFullBlock) overweightBlock).getStemBlock();
                BlockState stemState = null;
                if (stemBlock != null) stemState = stemBlock.getDefaultState();
                switch (overweightType) {
                    case DEFAULT -> simpleOverweightGrowth(serverLevel, blockPos, overweightState, stemState);
                    case SIMPLE -> setBlock(serverLevel, blockPos, overweightState);
                    case SPROUT -> growCarrotStem(serverLevel, blockPos, random, overweightState, stemState);
                }
            }
        }
    }

    @Nullable
    private Block getCompatBlock(String modid, String name) {
        return Registry.BLOCK.get(new Identifier(modid, name));
    }

    private void simpleOverweightGrowth(ServerWorld world, BlockPos blockPos, BlockState overweightCrop, BlockState stemBlock) {
        this.setBlock(world, blockPos, overweightCrop);
        if (stemBlock != null) {
            if (stemBlock.getBlock() instanceof TallPlantBlock) {
                boolean flag = world.isAir(blockPos.up()) && world.isAir(blockPos.up(2));
                if (!flag) return;
                TallPlantBlock.placeAt(world, stemBlock.getBlock().getDefaultState(), blockPos.up(), 2);
            } else {
                this.setBlock(world, blockPos.up(), stemBlock);
            }
        }
    }

    private void growCarrotStem(ServerWorld world, BlockPos blockPos, Random random, BlockState blockState, BlockState stemState) {
        int height = random.nextBoolean() && random.nextInt(5) == 0 ? random.nextBoolean() && random.nextInt(10) == 0 ? 4 : 3 : 2;
        BlockPos startPos = blockPos.up();
        BlockPos.Mutable mutableBlockPos = startPos.mutableCopy();
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

    private void setBlock(ServerWorld world, BlockPos blockPos, BlockState overweightState) {
        for (Block cropBlock : this.getOverweightMap().keySet()) {
            BlockState state = world.getBlockState(blockPos);
            if (state.isAir() || state.getBlock() == cropBlock || state.isOf(Blocks.FARMLAND) || state.isOf(Blocks.DIRT)) {
                world.setBlockState(blockPos, overweightState, 2);
            }
        }
    }

}