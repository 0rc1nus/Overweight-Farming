package com.binome.overweightfarming;

import com.binome.overweightfarming.init.OFObjects;
import com.binome.overweightfarming.util.EmeraldToItemOffer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.village.VillagerProfession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OverweightFarming implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "overweight_farming";

    @Override
    public void onInitialize() {
        OFObjects.init();

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5, factories -> {
            factories.add(new EmeraldToItemOffer(new ItemStack(OFObjects.STRAW_HAT), 20, 1, 12, 0.05F));
        });
    }

}
