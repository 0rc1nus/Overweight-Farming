package com.binome.overweightfarming.items;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.client.StrawHatModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class StrawHatItem extends ArmorItem {
    public static final StrawHatMaterial MATERIAL = new StrawHatMaterial();
    private static final ResourceLocation TEXTURE = new ResourceLocation(OverweightFarming.MODID, "textures/entity/straw_hat/straw_hat.png");
    private static final ResourceLocation STRAW_TEXTURE = new ResourceLocation(OverweightFarming.MODID, "textures/entity/straw_hat/straw_hat_straw.png");
    private static final ResourceLocation TEXTURE_420 = new ResourceLocation(OverweightFarming.MODID, "textures/entity/straw_hat/420.png");

    public StrawHatItem(EquipmentSlot slot, Properties properties) {
        super(MATERIAL, slot, properties);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return is420(stack) ? TEXTURE_420.toString() : isStraw(stack) ? STRAW_TEXTURE.toString() : TEXTURE.toString();
    }

    public static boolean is420(ItemStack stack) {
        return stack.getHoverName().getContents().equals("420");
    }

    public static boolean isStraw(ItemStack stack) {
        return stack.getHoverName().getContents().equals("Straw");
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                return new StrawHatModel<>(StrawHatModel.createBodyLayer().bakeRoot());
            }
        });
    }

    private static class StrawHatMaterial implements ArmorMaterial {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return 5;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return 1;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.WHEAT);
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

