package net.orcinus.overweightfarming.events;

import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.client.models.StrawHatModel;
import net.orcinus.overweightfarming.client.particles.MelonHangProvider;
import net.orcinus.overweightfarming.client.particles.MelonFallProvider;
import net.orcinus.overweightfarming.client.particles.MelonLandProvider;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFItems;
import net.orcinus.overweightfarming.init.OFModelLayers;
import net.orcinus.overweightfarming.init.OFParticleTypes;
import net.orcinus.overweightfarming.items.StrawHatItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
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
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_GINGER_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.ALLIUM_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_CABBAGE_STEM.get(), RenderType.cutout());
        event.enqueueWork(() -> ItemProperties.register(OFItems.STRAW_HAT.get(),
                new ResourceLocation(OverweightFarming.MODID, "420"), (stack, world, entity, p_174628_) -> entity != null && StrawHatItem.is420(stack) ? 1.0F : 0.0F)
        );
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OFModelLayers.STRAW_HAT, StrawHatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        ParticleEngine particleEngine = minecraft.particleEngine;
        particleEngine.register(OFParticleTypes.DRIPPING_MELON.get(), MelonHangProvider::new);
        particleEngine.register(OFParticleTypes.FALLING_MELON.get(), MelonFallProvider::new);
        particleEngine.register(OFParticleTypes.LANDING_MELON.get(), MelonLandProvider::new);
    }

}
