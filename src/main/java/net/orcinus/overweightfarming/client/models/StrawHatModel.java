package net.orcinus.overweightfarming.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrawHatModel<T extends LivingEntity> extends HumanoidModel<T> {
    private final ModelPart Head;

    public StrawHatModel(ModelPart root) {
        super(root);
        this.Head = root.getChild("Head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-8.0F, -4.0F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(16, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition straw_r1 = Head.addOrReplaceChild("straw_r1", CubeListBuilder.create().texOffs(0, -10).addBox(0.0F, -26.0F, 4.0F, 0.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 25.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

        return LayerDefinition.create(meshdefinition, 48, 48);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        this.Head.copyFrom(this.head);
        if (this.young) {
            poseStack.scale(0.75F, 0.75F, 0.75F);
            this.Head.setPos(0.0F, 15.0F, 0.0F);
        }
        this.Head.render(poseStack, buffer, packedLight, packedOverlay);
        poseStack.popPose();
    }
}
