package net.orcinus.overweightfarming.client.model;

import net.orcinus.overweightfarming.OverweightFarming;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class StrawHatModel<T extends LivingEntity> extends BipedEntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(OverweightFarming.MODID, "strawhatmodel"), "main");
    public final ModelPart hat1;

    public StrawHatModel(ModelPart root) {
        super(root, RenderLayer::getArmorCutoutNoCull);
        hat1 = head.getChild("armorHead").getChild("hat1");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = BipedEntityModel.getModelData(Dilation.NONE, 0);
        ModelPartData head = meshdefinition.getRoot().getChild(EntityModelPartNames.HEAD);
        ModelPartData armorHead = head.addChild("armorHead", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        ModelPartData hat1  = armorHead.addChild("hat1", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-8.0F, -4.0F, -8.0F, 16.0F, 0.0F, 16.0F, new Dilation(0.0F)).mirrored(false).uv(16, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));
        hat1.addChild("hat2", ModelPartBuilder.create().uv(0, -10).cuboid(0.0F, -26.0F, 4.0F, 0.0F, 5.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 25.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));


        return TexturedModelData.of(meshdefinition, 48, 48);
    }
}