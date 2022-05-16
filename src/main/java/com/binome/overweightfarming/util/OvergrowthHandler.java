package com.binome.overweightfarming.util;

import com.binome.overweightfarming.blocks.CropFullBlock;
import com.binome.overweightfarming.init.OFBlocks;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Random;

public class OvergrowthHandler {

    //TODO: Remove this, convert this to datagen
    public static final LinkedHashMap<Block, Block> CROPS_TO_OVERGROWN = Util.make(Maps.newLinkedHashMap(), map -> {
        map.put(Blocks.CARROTS, OFBlocks.OVERWEIGHT_CARROT.get());
        map.put(Blocks.POTATOES, OFBlocks.OVERWEIGHT_POTATO.get());
        map.put(Blocks.COCOA, OFBlocks.OVERWEIGHT_COCOA.get());
        map.put(Blocks.BEETROOTS, OFBlocks.OVERWEIGHT_BEETROOT.get());
        map.put(getCompatBlock("farmersdelight", "cabbages"), OFBlocks.OVERWEIGHT_CABBAGE.get());
        map.put(getCompatBlock("farmersdelight", "onions"), OFBlocks.OVERWEIGHT_ONION.get());
        map.put(getCompatBlock("hedgehog", "kiwi_vines"), OFBlocks.OVERWEIGHT_KIWI.get());
        map.put(getCompatBlock("snowyspirit", "ginger"), OFBlocks.OVERWEIGHT_GINGER.get());
    });

    @Nullable
    private static Block getCompatBlock(String modid, String name) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modid, name));
    }

    public static void overweightGrowth(Random random, BlockState state, ServerLevel world, BlockPos blockPos, Block cropBlock) {
        growOverweightCrop(random, state, world, blockPos, cropBlock);
    }

    public static void growOverweightCrop(Random random, BlockState state, ServerLevel world, BlockPos blockPos, Block cropBlock) {
        if (state.is(Blocks.CARROTS)) {
            growCarrotStem(world, blockPos, random);
        } else if (state.is(Blocks.COCOA)) {
            world.setBlock(blockPos, OFBlocks.OVERWEIGHT_COCOA.get().defaultBlockState(), 2);
        } else if (state.is(Blocks.POTATOES)) {
            Block stemBlock = ((CropFullBlock) OvergrowthHandler.CROPS_TO_OVERGROWN.get(cropBlock)).getStemBlock();
            Block block = random.nextInt(5) == 0 ? OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get() : OFBlocks.OVERWEIGHT_POTATO.get();
            simpleOverweightGrowth(world, blockPos, block.defaultBlockState(), stemBlock.defaultBlockState());
        } else if (state.is(Objects.requireNonNull(getCompatBlock("hedgehog", "kiwi_vines")))) {
            setBlock(world, blockPos, OFBlocks.OVERWEIGHT_KIWI.get().defaultBlockState());
        }
        else if (state.is(Objects.requireNonNull(getCompatBlock("farmersdelight", "onions")))) {
            setBlock(world, blockPos, OFBlocks.OVERWEIGHT_ONION.get().defaultBlockState());
            if (world.isEmptyBlock(blockPos.above()) && world.isEmptyBlock(blockPos.above(2))) {
                DoublePlantBlock.placeAt(world, OFBlocks.ALLIUM_BUSH.get().defaultBlockState(), blockPos.above(), 2);
            }
        }
        else {
            Block stemBlock = ((CropFullBlock) OvergrowthHandler.CROPS_TO_OVERGROWN.get(cropBlock)).getStemBlock();
            if (stemBlock != null) {
                simpleOverweightGrowth(world, blockPos, OvergrowthHandler.CROPS_TO_OVERGROWN.get(cropBlock).defaultBlockState(), stemBlock.defaultBlockState());
            } else  {
                world.setBlock(blockPos, OvergrowthHandler.CROPS_TO_OVERGROWN.get(cropBlock).defaultBlockState(), 2);
            }
        }
    }

    private static void simpleOverweightGrowth(ServerLevel world, BlockPos blockPos, BlockState overweightCrop, BlockState stemBlock) {
        setBlock(world, blockPos, overweightCrop);
        setBlock(world, blockPos.above(), stemBlock);
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
            BlockState state = world.getBlockState(blockPos);
            if (state.isAir() || state.getBlock() == cropBlock || state.is(Blocks.FARMLAND) || state.is(Blocks.DIRT)) {
                world.setBlock(blockPos, OVERWEIGHT_CARROT_STEM, 2);
            }
        }
    }

}
