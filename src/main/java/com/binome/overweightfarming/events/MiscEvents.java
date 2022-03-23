package com.binome.overweightfarming.events;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.util.OvergrowthHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MiscEvents {

    @SubscribeEvent
    public void onCropsGrow(BlockEvent.CropGrowEvent.Pre event) {
        LevelAccessor level = event.getWorld();
        BlockPos blockPos = event.getPos();
        BlockState state = event.getState();
        Random random = level.getRandom();
        if (level instanceof ServerLevel world) {
            for (Block cropBlock : OvergrowthHandler.CROPS_TO_OVERGROWN.keySet()) {
                if (state.is(cropBlock)) {
                    boolean flag = state.hasProperty(CropBlock.AGE) && state.getValue(CropBlock.AGE) < 7 && state.getValue(CropBlock.AGE) == 3;
                    boolean flag1 = state.hasProperty(CocoaBlock.AGE) && state.getValue(CocoaBlock.AGE) == 1;
                    boolean flag2 = state.hasProperty(BeetrootBlock.AGE) && state.getValue(BeetrootBlock.AGE) < BeetrootBlock.MAX_AGE && state.getValue(BeetrootBlock.AGE) > 1;
                    if (flag || flag1 || flag2) {
                        float chance = world.isNight() && world.getMoonPhase() == 0 ? 0.0010538863F : 3.4290552E-4F;
                        if (random.nextFloat() < chance) {
                            event.setResult(Event.Result.DENY);
                            OvergrowthHandler.overweightGrowth(random, state, world, blockPos, cropBlock);
                        }
                    }
                }
            }
        }
    }

}
