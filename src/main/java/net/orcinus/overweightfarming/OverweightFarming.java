package net.orcinus.overweightfarming;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import net.orcinus.overweightfarming.registry.OFObjects;
import net.orcinus.overweightfarming.util.EmeraldToItemOffer;
import net.orcinus.overweightfarming.util.OFUtils;
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

        UseBlockCallback.EVENT.register(OFUtils::stripMelon);
    }
}
