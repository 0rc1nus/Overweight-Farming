package net.orcinus.overweightfarming.client.renderer;

import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.client.model.StrawHatModel;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

public class StrawHatRenderer implements ArmorRenderer {
    private static final Identifier TEXTURE = new Identifier(OverweightFarming.MODID, "textures/entity/straw_hat/straw_hat.png");
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

        list.add("trans_rights");
        list.add("straw_hat_straw");
        list.add("420");
    });

    private static StrawHatModel<LivingEntity> armorModel;
    private final Item hatItem;

    public StrawHatRenderer(@Nullable Item hatItem) {
        this.hatItem = hatItem;
    }

    private static String getContents(ItemStack stack) {
        return stack.getName().getString();
    }

    public Identifier getArmorTexture(ItemStack stack) {
        for (String id : LISTS) {
            String namingContent = id.toLowerCase().replace('_', ' ');

            if (!getContents(stack).equals(namingContent)) continue;
            return getContents(stack).equals(namingContent) ? new Identifier(OverweightFarming.MODID, "textures/entity/straw_hat/" + id + ".png") : TEXTURE;
        }
        return TEXTURE;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
        if (armorModel == null) {
            armorModel = new StrawHatModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(StrawHatModel.LAYER_LOCATION));
        } else {
            contextModel.copyStateTo(armorModel);
            armorModel.setVisible(false);
            armorModel.head.visible = slot == EquipmentSlot.HEAD;

            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, armorModel, getArmorTexture(stack));
        }
    }
}
