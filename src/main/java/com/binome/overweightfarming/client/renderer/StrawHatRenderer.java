package com.binome.overweightfarming.client.renderer;

import com.binome.overweightfarming.client.model.StrawHatModel;
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
import org.jetbrains.annotations.Nullable;

public class StrawHatRenderer implements ArmorRenderer {
    private static StrawHatModel<LivingEntity> armorModel;
    private final Identifier texture;
    private final Item hatItem;

    public StrawHatRenderer(Identifier texture, @Nullable Item hatItem) {
        this.texture = texture;
        this.hatItem = hatItem;
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
        if (armorModel == null) {
            armorModel = new StrawHatModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(StrawHatModel.LAYER_LOCATION));
        }else{
            contextModel.setAttributes(armorModel);
            armorModel.setVisible(false);
            armorModel.head.visible = slot == EquipmentSlot.HEAD;
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, armorModel, texture);
        }
    }
}
