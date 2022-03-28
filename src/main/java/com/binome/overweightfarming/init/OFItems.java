package com.binome.overweightfarming.init;

import com.binome.overweightfarming.OverweightFarming;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OFItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OverweightFarming.MODID);

}
