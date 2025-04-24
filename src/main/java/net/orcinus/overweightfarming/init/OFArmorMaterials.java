package net.orcinus.overweightfarming.init;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class OFArmorMaterials {

    public static final Holder<ArmorMaterial> STRAW = register("straw", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.BODY, 5);
    }), 0, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.WHEAT));


    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> map,
            int enchantmentValue,
            Holder<SoundEvent> sound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairItem
    ) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace(name)));
        return register(name, map, enchantmentValue, sound, toughness, knockbackResistance, repairItem, list);
    }

    private static Holder<ArmorMaterial> register(
            String p_323865_,
            EnumMap<ArmorItem.Type, Integer> map,
            int p_324319_,
            Holder<SoundEvent> p_324145_,
            float p_323494_,
            float p_324549_,
            Supplier<Ingredient> p_323845_,
            List<ArmorMaterial.Layer> p_323990_
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, map.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.withDefaultNamespace(p_323865_),
                new ArmorMaterial(enummap, p_324319_, p_324145_, p_323845_, p_323990_, p_323494_, p_324549_)
        );
    }

}
