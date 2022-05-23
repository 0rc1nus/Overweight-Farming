package net.orcinus.overweightfarming;

import net.orcinus.overweightfarming.init.OFObjects;
import net.orcinus.overweightfarming.util.EmeraldToItemOffer;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.village.VillagerProfession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OverweightFarming implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "overweight_farming";
    public static OFConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(OFConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(OFConfig.class).getConfig();
        OFObjects.init();

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5, factories -> {
            factories.add(new EmeraldToItemOffer(new ItemStack(OFObjects.STRAW_HAT), 20, 1, 12, 0.05F));
        });
    }

}
