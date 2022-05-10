package com.binome.overweightfarming.init;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.items.MelonJuiceItem;
import com.binome.overweightfarming.items.StrawHatItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OFItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OverweightFarming.MODID);

    public static final RegistryObject<Item> STRAW_HAT = ITEMS.register("straw_hat", () -> new StrawHatItem(EquipmentSlot.HEAD, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> MELON_JUICE = ITEMS.register("melon_juice", () -> new MelonJuiceItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(16)));
    public static final RegistryObject<Item> VEGETABLE_PEELS = ITEMS.register("vegetable_peels", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

}
