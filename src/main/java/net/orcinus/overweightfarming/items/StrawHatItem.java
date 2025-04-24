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
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.client.models.StrawHatModel;
import net.orcinus.overweightfarming.init.OFArmorMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.function.Consumer;

public class StrawHatItem extends ArmorItem {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(OverweightFarming.MODID, "textures/entity/straw_hat/straw_hat.png");
    private static final ResourceLocation TRANS_TEXTURE = ResourceLocation.fromNamespaceAndPath(OverweightFarming.MODID, "textures/entity/straw_hat/trans_rights.png");
    private static final ResourceLocation STRAW_TEXTURE = ResourceLocation.fromNamespaceAndPath(OverweightFarming.MODID, "textures/entity/straw_hat/straw_hat_straw.png");
    private static final ResourceLocation TEXTURE_420 = ResourceLocation.fromNamespaceAndPath(OverweightFarming.MODID, "textures/entity/straw_hat/420.png");
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

    public StrawHatItem(Type slot, Properties properties) {
        super(OFArmorMaterials.STRAW, slot, properties);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        for (String id : LISTS) {
            String namingContent = id.replace('_', ' ');
            if (!getContents(stack).equals(namingContent)) continue;
            return getContents(stack).equals(namingContent) ? OverweightFarming.id("textures/entity/straw_hat/" + id + ".png") : TEXTURE;
        }
        return is420(stack) ? TEXTURE_420 : isStraw(stack) ? STRAW_TEXTURE : isTrans(stack) ? TRANS_TEXTURE : TEXTURE;
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
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                return new StrawHatModel<>(StrawHatModel.createBodyLayer().bakeRoot());
            }
        });
    }
}

