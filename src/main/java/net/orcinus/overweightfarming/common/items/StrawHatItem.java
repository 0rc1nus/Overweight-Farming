package net.orcinus.overweightfarming.common.items;

import com.google.common.collect.Lists;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.LinkedList;

public class StrawHatItem extends ArmorItem {
    public static final StrawHatMaterial MATERIAL = new StrawHatMaterial();
    public static final LinkedList<String> LISTS = Util.make(Lists.newLinkedList(), list -> {
        list.add("accessible_knowledge");
        list.add("amogus");
        list.add("atroxic");
        list.add("birb");
        list.add("black_hat");
        list.add("convenient_cauldron");
        list.add("delightful");
        list.add("doset");
        list.add("electrum_hat");
        list.add("frog_tongue");
        list.add("froggy_straw_hat");
        list.add("lead_hat");
        list.add("leadly_hat");
        list.add("pebble_hat");
        list.add("pirates");
        list.add("potat");
        list.add("silver_hat");
        list.add("there_is_a_frog_on_your_head_yknow");
    });

    public StrawHatItem(Settings properties) {
        super(MATERIAL, Type.HELMET, properties);
    }

    private static class StrawHatMaterial implements ArmorMaterial {


        @Override
        public int getDurability(Type type) {
            return 5;
        }

        @Override
        public int getProtection(Type type) {
            return 1;
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
            return Ingredient.ofStacks(Items.WHEAT.getDefaultStack());
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