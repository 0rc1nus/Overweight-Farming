package com.binome.overweightfarming.util;

import com.binome.overweightfarming.blocks.CropFullBlock;
import com.binome.overweightfarming.init.OFBlocks;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.LinkedHashMap;
import java.util.Random;

public class OvergrowthHandler {

    //TODO: Remove this, convert this to datagen
    public static final LinkedHashMap<Block, Block> CROPS_TO_OVERGROWN = Util.make(Maps.newLinkedHashMap(), map -> {
        map.put(Blocks.CARROTS, OFBlocks.OVERWEIGHT_CARROT.get());
        map.put(Blocks.POTATOES, OFBlocks.OVERWEIGHT_POTATO.get());
        map.put(Blocks.COCOA, OFBlocks.OVERWEIGHT_COCOA.get());
        map.put(Blocks.BEETROOTS, OFBlocks.OVERWEIGHT_BEETROOT.get());
    });

    public static void overweightGrowth(Random random, BlockState state, ServerLevel world, BlockPos blockPos , Block cropBlock) {
        growOverweightCrop(random, state, world, blockPos, cropBlock);
    }

    public static void growOverweightCrop(Random random, BlockState state, ServerLevel world, BlockPos blockPos, Block cropBlock) {
        if (state.is(Blocks.CARROTS)) {
            growCarrotStem(world, blockPos, random);
        } else if (state.is(Blocks.COCOA)) {
            world.setBlock(blockPos, OFBlocks.OVERWEIGHT_COCOA.get().defaultBlockState(), 2);
        } else {
            simpleOverweightGrowth(world, blockPos, OvergrowthHandler.CROPS_TO_OVERGROWN.get(cropBlock).defaultBlockState(), ((CropFullBlock) OvergrowthHandler.CROPS_TO_OVERGROWN.get(cropBlock)).getStemBlock().defaultBlockState());
        }
    }

    private static void simpleOverweightGrowth(ServerLevel world, BlockPos blockPos, BlockState CROPS_TO_OVERGROWN, BlockState CROPS_TO_OVERGROWN1) {
        setBlock(world, blockPos, CROPS_TO_OVERGROWN);
        setBlock(world, blockPos.above(), CROPS_TO_OVERGROWN1);
    }

    public static void growCarrotStem(ServerLevel world, BlockPos blockPos, Random random) {
        int height = random.nextBoolean() && random.nextInt(5) == 0 ? random.nextBoolean() && random.nextInt(10) == 0 ? 4 : 3 : 2;
        BlockPos startPos = blockPos.above();
        BlockPos.MutableBlockPos mutableBlockPos = startPos.mutable();
        for (int i = 0; i < height; i++) {
            BlockState placeState = OFBlocks.OVERWEIGHT_CARROT.get().defaultBlockState();
            if (i == 0)
                placeState = OFBlocks.OVERWEIGHT_CARROT_STEM.get().defaultBlockState();
            setBlock(world, mutableBlockPos, placeState);
            mutableBlockPos.move(Direction.DOWN);
        }
    }

    private static void setBlock(ServerLevel world, BlockPos blockPos, BlockState OVERWEIGHT_CARROT_STEM) {
        for (Block cropBlock : CROPS_TO_OVERGROWN.keySet()) {
            if (world.getBlockState(blockPos).isAir() || world.getBlockState(blockPos).getBlock() == cropBlock || world.getBlockState(blockPos).is(Blocks.FARMLAND) || world.getBlockState(blockPos).is(BlockTags.DIRT)) {
                world.setBlock(blockPos, OVERWEIGHT_CARROT_STEM, 2);
            }
        }
    }

}
