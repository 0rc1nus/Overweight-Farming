package com.binome.overweightfarming.util;

import com.binome.overweightfarming.OverweightFarming;
import
com.binome.overweightfarming.blocks.CropFullBlock;
import com.binome.overweightfarming.registry.OFObjects;
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
        if(OverweightFarming.config.crops.allowOverweightCarrot){
            map.put(Blocks.CARROTS, OFObjects.OVERWEIGHT_CARROT);
        }
        if(OverweightFarming.config.crops.allowOverweightPotato){
            map.put(Blocks.POTATOES, OFObjects.OVERWEIGHT_POTATO);
        }
        if(OverweightFarming.config.crops.allowOverweightCocoa){
            map.put(Blocks.COCOA, OFObjects.OVERWEIGHT_COCOA);
        }
        if(OverweightFarming.config.crops.allowOverweightBeetroot) {
            map.put(Blocks.BEETROOTS, OFObjects.OVERWEIGHT_BEETROOT);
        }
        if(OverweightFarming.config.compatCrops.allowOverweightCabbage){
            map.put(getCompatBlock("farmersdelight", "cabbages"), OFObjects.OVERWEIGHT_CABBAGE);
        }
        if(OverweightFarming.config.compatCrops.allowOverweightOnion){
            map.put(getCompatBlock("farmersdelight", "onions"), OFObjects.OVERWEIGHT_ONION);
        }
        if(OverweightFarming.config.compatCrops.allowOverweightMandrake){
            map.put(getCompatBlock("bewitchment", "mandrake"), OFObjects.OVERWEIGHT_MANDRAKE);
        }
        if(OverweightFarming.config.compatCrops.allowOverweightGarlic){
            map.put(getCompatBlock("bewitchment", "garlic"), OFObjects.OVERWEIGHT_GARLIC);
        }
        if(OverweightFarming.config.compatCrops.allowOverweightBloodroot){
            map.put(getCompatBlock("bwplus", "bloodroot"), OFObjects.OVERWEIGHT_BLOODROOT);
        }
    });

    @Nullable
    private static Block getCompatBlock(String modid, String name) {
        return Registry.BLOCK.get(new Identifier(modid, name));
    }

    public static void overweightGrowth(Random random, BlockState state, ServerWorld world, BlockPos blockPos , Block cropBlock) {
        growOverweightCrop(random, state, world, blockPos, cropBlock);
    }

    public static void growOverweightCrop(Random random, BlockState state, ServerWorld world, BlockPos blockPos, Block cropBlock) {
        if (state.isOf(Blocks.CARROTS) || state.isOf(getCompatBlock("bewitchment", "mandrake"))) {
            growDoubleStem(world, blockPos, random, state);
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

    public static void growDoubleStem(ServerWorld world, BlockPos blockPos, Random random, BlockState blockState) {
        int height = random.nextBoolean() && random.nextInt(5) == 0 ? random.nextBoolean() && random.nextInt(10) == 0 ? 4 : 3 : 2;
        BlockPos startPos = blockPos.up();
        BlockPos.Mutable mutableBlockPos = startPos.mutableCopy();
        for (int i = 0; i < height; i++) {
            BlockState placeState = blockState.isOf(Blocks.CARROTS) ? OFObjects.OVERWEIGHT_CARROT.getDefaultState() : OFObjects.OVERWEIGHT_MANDRAKE.getDefaultState();
            if (i == 0){
                placeState = blockState.isOf(Blocks.CARROTS) ? OFObjects.OVERWEIGHT_CARROT_STEM.getDefaultState() : OFObjects.OVERWEIGHT_MANDRAKE_STEM.getDefaultState();
            }
            setBlock(world, mutableBlockPos, placeState);
            mutableBlockPos.move(Direction.DOWN);
        }
    }

    private static void setBlock(ServerWorld world, BlockPos blockPos, BlockState blockState) {
        for (Block cropBlock : CROPS_TO_OVERGROWN.keySet()) {
            if (world.getBlockState(blockPos).isAir() || world.getBlockState(blockPos).getBlock() == cropBlock || world.getBlockState(blockPos).isOf(Blocks.FARMLAND) || world.getBlockState(blockPos).isOf(Blocks.DIRT)) {
                world.setBlockState(blockPos, blockState, 2);
            }
        }
    }

}
