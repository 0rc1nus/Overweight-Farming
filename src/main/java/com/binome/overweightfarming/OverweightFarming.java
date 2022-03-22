package com.binome.overweightfarming;

import com.binome.overweightfarming.events.MiscEvents;
import com.binome.overweightfarming.events.MobEvents;
import com.binome.overweightfarming.init.OFBlocks;
import com.binome.overweightfarming.init.OFItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OverweightFarming.MODID)
public class OverweightFarming {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "overweight_farming";

    public OverweightFarming() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        OFBlocks.BLOCKS.register(modEventBus);
        OFItems.ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new MiscEvents());
        MinecraftForge.EVENT_BUS.register(new MobEvents());
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

}
