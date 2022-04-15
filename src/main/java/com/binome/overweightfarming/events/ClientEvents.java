package com.binome.overweightfarming.events;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.client.StrawHatModel;
import com.binome.overweightfarming.init.OFBlocks;
import com.binome.overweightfarming.init.OFItems;
import com.binome.overweightfarming.init.OFModelLayers;
import com.binome.overweightfarming.items.StrawHatItem;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.ALLIUM_BUSH.get(), RenderType.cutout());
        event.enqueueWork(() -> ItemProperties.register(OFItems.STRAW_HAT.get(),
                new ResourceLocation(OverweightFarming.MODID, "420"), (stack, world, entity, p_174628_) -> entity != null && StrawHatItem.is420(stack) ? 1.0F : 0.0F)
        );
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OFModelLayers.STRAW_HAT, StrawHatModel::createBodyLayer);
    }

}