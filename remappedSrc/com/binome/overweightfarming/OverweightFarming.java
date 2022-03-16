package com.binome.overweightfarming;

import com.binome.overweightfarming.init.OFObjects;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OverweightFarming implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "overweight_farming";


    @Override
    public void onInitialize() {
        OFObjects.init();
    }
}
