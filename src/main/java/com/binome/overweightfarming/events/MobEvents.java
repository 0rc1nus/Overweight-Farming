package com.binome.overweightfarming.events;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.init.OFItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
    }

    @SubscribeEvent
    public void onAnimalBabyCreation(BabyEntitySpawnEvent event) {
        Player player = event.getCausedByPlayer();
        if (player != null && player.isAlive()) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).is(OFItems.STRAW_HAT.get())) {
            }
        }
    }

}
