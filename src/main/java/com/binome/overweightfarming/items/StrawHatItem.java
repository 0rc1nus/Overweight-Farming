package com.binome.overweightfarming.items;


import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.client.model.StrawHatModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class StrawHatItem extends ArmorItem {
    public static final StrawHatMaterial MATERIAL = new StrawHatMaterial();

    public StrawHatItem(Settings properties) {
        super(MATERIAL, EquipmentSlot.HEAD, properties);
    }


    private static class StrawHatMaterial implements ArmorMaterial {
        @Override
        public int getDurability(EquipmentSlot slot) {
            return 300;
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return 2;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofStacks(Items.LEATHER.getDefaultStack());
        }

        @Override
        public String getName() {
            return "straw";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    }
}