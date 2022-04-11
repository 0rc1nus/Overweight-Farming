package com.binome.overweightfarming;

import com.binome.overweightfarming.client.model.StrawHatModel;
import com.binome.overweightfarming.client.renderer.StrawHatRenderer;
import com.binome.overweightfarming.init.OFObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.RenderLayer;

public class OverweightFarmingClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),OFObjects.ALLIUM_BUSH, OFObjects.OVERWEIGHT_BEETROOT_STEM, OFObjects.OVERWEIGHT_CARROT_STEM, OFObjects.OVERWEIGHT_POTATO_STEM, OFObjects.OVERWEIGHT_BLOODROOT_STEM, OFObjects.OVERWEIGHT_MANDRAKE_STEM, OFObjects.OVERWEIGHT_GARLIC_STEM);
        EntityModelLayerRegistry.registerModelLayer(StrawHatModel.LAYER_LOCATION, StrawHatModel::createBodyLayer);
        ArmorRenderer.register(new StrawHatRenderer(null), OFObjects.STRAW_HAT);
    }
}
