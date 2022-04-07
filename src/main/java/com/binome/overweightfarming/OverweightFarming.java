package com.binome.overweightfarming;

import com.binome.overweightfarming.events.MiscEvents;
import com.binome.overweightfarming.events.MobEvents;
import com.binome.overweightfarming.init.OFBlocks;
import com.binome.overweightfarming.init.OFItems;
import com.binome.overweightfarming.items.StrawHatItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BundleItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
        modEventBus.addListener(this::clientSetup);

        OFBlocks.BLOCKS.register(modEventBus);
        OFItems.ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new MobEvents());
        MinecraftForge.EVENT_BUS.register(new MiscEvents());
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemProperties.register(OFItems.STRAW_HAT.get(),
                new ResourceLocation(OverweightFarming.MODID, "420"), (stack, world, entity, p_174628_) -> {
            /*if (entity != null) {
                if (StrawHatItem.is420(stack)) {
                    return 1.0F;
                } else {
                    return 0.0F;
                }
            } else {
                return 0.0F;
            }*/
                    return entity != null && StrawHatItem.is420(stack) ? 1.0F : 0.0F;
                })
        );
    }

}
