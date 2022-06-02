package com.binome.overweightfarming.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class OverweightGrowthData extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();

    public OverweightGrowthData() {
        super(GSON, "overweight_crops");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, ResourceManager manager, ProfilerFiller filler) {

    }
}
