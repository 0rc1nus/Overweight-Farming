package net.orcinus.overweightfarming;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FallingBlockEntityRenderer;
import net.orcinus.overweightfarming.client.model.StrawHatModel;
import net.orcinus.overweightfarming.client.renderer.DandelionEntityRenderer;
import net.orcinus.overweightfarming.client.renderer.StrawHatRenderer;
import net.orcinus.overweightfarming.registry.OFEntityTypes;
import net.orcinus.overweightfarming.registry.OFObjects;
import net.orcinus.overweightfarming.registry.OFParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.RenderLayer;

public class OverweightFarmingClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        OFParticleTypes.init();

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                OFObjects.ALLIUM_BUSH,
                OFObjects.OVERWEIGHT_BEETROOT_STEM,
                OFObjects.OVERWEIGHT_CARROT_STEM,
                OFObjects.OVERWEIGHT_POTATO_STEM,
                OFObjects.OVERWEIGHT_BLOODROOT_STEM,
                OFObjects.OVERWEIGHT_MANDRAKE_STEM,
                OFObjects.OVERWEIGHT_GARLIC_STEM,
                OFObjects.OVERWEIGHT_APPLE_STEM,
                OFObjects.OVERWEIGHT_GOLDEN_APPLE_STEM,

                OFObjects.POTTED_OVERWEIGHT_APPLE,
                OFObjects.POTTED_OVERWEIGHT_BEETROOT,
                OFObjects.POTTED_OVERWEIGHT_CABBAGE,
                OFObjects.POTTED_OVERWEIGHT_CARROT,
                OFObjects.POTTED_OVERWEIGHT_COCOA,
                OFObjects.POTTED_OVERWEIGHT_GINGER,
                OFObjects.POTTED_OVERWEIGHT_GOLDEN_APPLE,
                OFObjects.POTTED_OVERWEIGHT_KIWI,
                OFObjects.POTTED_OVERWEIGHT_ONION,
                OFObjects.POTTED_OVERWEIGHT_POISONOUS_POTATO,
                OFObjects.POTTED_OVERWEIGHT_POTATO,
                OFObjects.POTTED_OVERWEIGHT_NETHER_WART,

                OFObjects.OVERWEIGHT_NETHER_WART_STEM
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), OFObjects.OVERWEIGHT_WEED);

        EntityRendererRegistry.register(OFEntityTypes.OVERWEIGHT_APPLE_FALLING_BLOCK, FallingBlockEntityRenderer::new);
        EntityRendererRegistry.register(OFEntityTypes.DANDELION_FLUFF_ENTITY, DandelionEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(StrawHatModel.LAYER_LOCATION, StrawHatModel::createBodyLayer);
        ArmorRenderer.register(new StrawHatRenderer(null), OFObjects.STRAW_HAT);
    }
}
