package net.orcinus.overweightfarming.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.items.MelonJuiceItem;
import net.orcinus.overweightfarming.items.StrawHatItem;

public class OFItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, OverweightFarming.MODID);

    public static final DeferredHolder<Item, Item> STRAW_HAT = ITEMS.register("straw_hat", () -> new StrawHatItem(ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1).durability(ArmorItem.Type.HELMET.getDurability(5))));
    public static final DeferredHolder<Item, Item> MELON_JUICE = ITEMS.register("melon_juice", () -> new MelonJuiceItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().alwaysEdible().nutrition(3).saturationModifier(0.6F).build())));
    public static final DeferredHolder<Item, Item> VEGETABLE_PEELS = ITEMS.register("vegetable_peels", () -> new Item(new Item.Properties()));

}
