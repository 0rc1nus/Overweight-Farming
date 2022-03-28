package com.binome.overweightfarming.util;

import
com.binome.overweightfarming.blocks.CropFullBlock;
import com.binome.overweightfarming.init.OFObjects;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Random;
public class OvergrowthHandler {

    //TODO: Remove this, convert this to datagen
    public static final LinkedHashMap<Block, Block> CROPS_TO_OVERGROWN = Util.make(Maps.newLinkedHashMap(), map -> {
        map.put(Blocks.CARROTS, OFObjects.OVERWEIGHT_CARROT);
        map.put(Blocks.POTATOES, OFObjects.OVERWEIGHT_POTATO);
        map.put(Blocks.COCOA, OFObjects.OVERWEIGHT_COCOA);
        map.put(Blocks.BEETROOTS, OFObjects.OVERWEIGHT_BEETROOT);
        map.put(getCompatBlock("farmersdelight", "cabbages"), OFObjects.OVERWEIGHT_CABBAGE);
        map.put(getCompatBlock("farmersdelight", "onions"), OFObjects.OVERWEIGHT_ONION);
    });

    @Nullable
    private static Block getCompatBlock(String modid, String name) {
        return Registry.BLOCK.get(new Identifier(modid, name));
    }

    public static void overweightGrowth(Random random, BlockState state, ServerWorld world, BlockPos blockPos , Block cropBlock) {
        growOverweightCrop(random, state, world, blockPos, cropBlock);
    }

    public static void growOverweightCrop(Random random, BlockState state, ServerWorld world, BlockPos blockPos, Block cropBlock) {
        if (state.isOf(Blocks.CARROTS)) {
            growCarrotStem(world, blockPos, random);
        } else if (state.isOf(Blocks.COCOA)) {
            world.setBlockState(blockPos, OFObjects.OVERWEIGHT_COCOA.getDefaultState(), 2);
        } else {
            simpleOverweightGrowth(world, blockPos, OvergrowthHandler.CROPS_TO_OVERGROWN.get(cropBlock).getDefaultState(), ((CropFullBlock) OvergrowthHandler.CROPS_TO_OVERGROWN.get(cropBlock)).getStemBlock().getDefaultState());
        }
    }

    private static void simpleOverweightGrowth(ServerWorld world, BlockPos blockPos, BlockState CROPS_TO_OVERGROWN, BlockState CROPS_TO_OVERGROWN1) {
        setBlock(world, blockPos, CROPS_TO_OVERGROWN);
        setBlock(world, blockPos.up(), CROPS_TO_OVERGROWN1);
    }

    public static void growCarrotStem(ServerWorld world, BlockPos blockPos, Random random) {
        int height = random.nextBoolean() && random.nextInt(5) == 0 ? random.nextBoolean() && random.nextInt(10) == 0 ? 4 : 3 : 2;
        BlockPos startPos = blockPos.up();
        BlockPos.Mutable mutableBlockPos = startPos.mutableCopy();
        for (int i = 0; i < height; i++) {
            BlockState placeState = OFObjects.OVERWEIGHT_CARROT.getDefaultState();
            if (i == 0)
                placeState = OFObjects.OVERWEIGHT_CARROT_STEM.getDefaultState();
            setBlock(world, mutableBlockPos, placeState);
            mutableBlockPos.move(Direction.DOWN);
        }
    }

    private static void setBlock(ServerWorld world, BlockPos blockPos, BlockState OVERWEIGHT_CARROT_STEM) {
        for (Block cropBlock : CROPS_TO_OVERGROWN.keySet()) {
            if (world.getBlockState(blockPos).isAir() || world.getBlockState(blockPos).getBlock() == cropBlock || world.getBlockState(blockPos).isOf(Blocks.FARMLAND) || world.getBlockState(blockPos).isOf(Blocks.DIRT)) {
                world.setBlockState(blockPos, OVERWEIGHT_CARROT_STEM, 2);
            }
        }
    }

}
