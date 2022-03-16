package com.binome.overweightfarming;

import com.binome.overweightfarming.init.OFObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class OverweightFarmingClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), OFObjects.OVERWEIGHT_BEETROOT_STEM, OFObjects.OVERWEIGHT_CARROT_STEM, OFObjects.OVERWEIGHT_POTATO_STEM);
    }
}
