package net.orcinus.overweightfarming.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.items.MelonJuiceItem;
import net.orcinus.overweightfarming.items.StrawHatItem;
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
    public static final RegistryObject<Item> MELON_JUICE = ITEMS.register("melon_juice", () -> new MelonJuiceItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)
            .food(new FoodProperties.Builder()
                    .alwaysEat()
                    .nutrition(3)
                    .saturationMod(0.6f)
                    .build()
            )));
    public static final RegistryObject<Item> VEGETABLE_PEELS = ITEMS.register("vegetable_peels", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

}
