package net.orcinus.overweightfarming.client.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.common.entities.DandelionFluffEntity;

public class DandelionEntityRenderer extends EntityRenderer<DandelionFluffEntity> {
    private static final Identifier TEXTURE = new Identifier("textures/items/wheat.png");

    public DandelionEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(DandelionFluffEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(DandelionFluffEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        MinecraftClient.getInstance().getItemRenderer().renderItem(Items.WHEAT.getDefaultStack(), ModelTransformation.Mode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
