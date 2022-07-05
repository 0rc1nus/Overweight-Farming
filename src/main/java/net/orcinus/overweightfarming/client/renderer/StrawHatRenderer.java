package net.orcinus.overweightfarming.client.renderer;

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
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.client.model.StrawHatModel;
import org.jetbrains.annotations.Nullable;

public class StrawHatRenderer implements ArmorRenderer {
    private static final Identifier TEXTURE = new Identifier(OverweightFarming.MODID, "textures/entity/straw_hat/straw_hat.png");
    private static final Identifier TRANS_TEXTURE = new Identifier(OverweightFarming.MODID, "textures/entity/straw_hat/trans_rights.png");
    private static final Identifier STRAW_TEXTURE = new Identifier(OverweightFarming.MODID, "textures/entity/straw_hat/straw_hat_straw.png");
    private static final Identifier TEXTURE_420 = new Identifier(OverweightFarming.MODID, "textures/entity/straw_hat/420.png");
    private static StrawHatModel<LivingEntity> armorModel;
    private final Item hatItem;

    public StrawHatRenderer(@Nullable Item hatItem) {
        this.hatItem = hatItem;
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

    private static String getContents(ItemStack stack) {
        return stack.getName().getString();
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
        if (armorModel == null) {
            armorModel = new StrawHatModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(StrawHatModel.LAYER_LOCATION));
        } else {
            contextModel.setAttributes(armorModel);
            armorModel.setVisible(false);
            armorModel.head.visible = slot == EquipmentSlot.HEAD;
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, armorModel, is420(stack) ? TEXTURE_420 : isStraw(stack) ? STRAW_TEXTURE : isTrans(stack) ? TRANS_TEXTURE : TEXTURE);
        }
    }
}
