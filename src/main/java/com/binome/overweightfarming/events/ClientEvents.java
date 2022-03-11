package com.binome.overweightfarming.events;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.init.OFBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_BEETROOT_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_CARROT_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_POTATO_STEM.get(), RenderType.cutout());
    }

}
