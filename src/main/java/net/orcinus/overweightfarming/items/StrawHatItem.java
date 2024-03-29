package net.orcinus.overweightfarming.items;

import com.google.common.collect.Lists;
import net.minecraft.Util;
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
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.client.models.StrawHatModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.function.Consumer;

public class StrawHatItem extends ArmorItem {
    public static final StrawHatMaterial MATERIAL = new StrawHatMaterial();
    private static final ResourceLocation TEXTURE = new ResourceLocation(OverweightFarming.MODID, "textures/entity/straw_hat/straw_hat.png");
    private static final ResourceLocation TRANS_TEXTURE = new ResourceLocation(OverweightFarming.MODID, "textures/entity/straw_hat/trans_rights.png");
    private static final ResourceLocation STRAW_TEXTURE = new ResourceLocation(OverweightFarming.MODID, "textures/entity/straw_hat/straw_hat_straw.png");
    private static final ResourceLocation TEXTURE_420 = new ResourceLocation(OverweightFarming.MODID, "textures/entity/straw_hat/420.png");
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

    public StrawHatItem(EquipmentSlot slot, Properties properties) {
        super(MATERIAL, slot, properties);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        for (String id : LISTS) {
            String namingContent = id.replace('_', ' ');
            if (!getContents(stack).equals(namingContent)) continue;
            return getContents(stack).equals(namingContent) ? new ResourceLocation(OverweightFarming.MODID, "textures/entity/straw_hat/" + id + ".png").toString() : TEXTURE.toString();
        }
        return is420(stack) ? TEXTURE_420.toString() : isStraw(stack) ? STRAW_TEXTURE.toString() : isTrans(stack) ? TRANS_TEXTURE.toString() : TEXTURE.toString();
    }

    public static boolean is420(ItemStack stack) {
        return getContents(stack).equals("420");
    }

    public static boolean isStraw(ItemStack stack) {
        return getContents(stack).equals("Straw");
    }

    public static boolean isTrans(ItemStack stack) {
        return getContents(stack).equals("Trans Rights");
    }

    @NotNull
    private static String getContents(ItemStack stack) {
        return stack.getHoverName().getString();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
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

