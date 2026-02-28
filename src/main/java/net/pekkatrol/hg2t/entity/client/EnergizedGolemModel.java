package net.pekkatrol.hg2t.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.entity.custom.EnergizedGolemEntity;

public class EnergizedGolemModel<T extends EnergizedGolemEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "energized_golem"), "main");
    private final ModelPart mob;
    private final ModelPart tete;
    private final ModelPart bras;
    private final ModelPart bras1;
    private final ModelPart bras2;
    private final ModelPart chest;
    private final ModelPart pieds;
    private final ModelPart pied1;
    private final ModelPart pied2;

    public EnergizedGolemModel(ModelPart root) {
        this.mob = root.getChild("mob");
        this.tete = this.mob.getChild("tete");
        this.bras = this.mob.getChild("bras");
        this.bras1 = this.bras.getChild("bras1");
        this.bras2 = this.bras.getChild("bras2");
        this.chest = this.mob.getChild("chest");
        this.pieds = this.mob.getChild("pieds");
        this.pied1 = this.pieds.getChild("pied1");
        this.pied2 = this.pieds.getChild("pied2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition mob = partdefinition.addOrReplaceChild("mob", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tete = mob.addOrReplaceChild("tete", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-3.0F, -5.0F, 2.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 36).addBox(-3.0F, -5.0F, 3.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 26).addBox(3.0F, -4.0F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(-4.0F, -4.0F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -25.0F, 0.0F));

        PartDefinition bras = mob.addOrReplaceChild("bras", CubeListBuilder.create(), PartPose.offset(-7.0F, -18.0F, 0.0F));

        PartDefinition bras1 = bras.addOrReplaceChild("bras1", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -0.5F));

        PartDefinition bras2 = bras.addOrReplaceChild("bras2", CubeListBuilder.create().texOffs(22, 18).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, 0.0F, 0.0F));

        PartDefinition chest = mob.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition pieds = mob.addOrReplaceChild("pieds", CubeListBuilder.create(), PartPose.offset(-2.0F, -5.0F, 0.0F));

        PartDefinition pied1 = pieds.addOrReplaceChild("pied1", CubeListBuilder.create().texOffs(32, 8).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

        PartDefinition pied2 = pieds.addOrReplaceChild("pied2", CubeListBuilder.create().texOffs(32, 17).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(EnergizedGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimationState, EnergizedGolemAnimations.IDLE, ageInTicks, 1f);
        this.animate(entity.attackAnimationState, EnergizedGolemAnimations.ATTACK, ageInTicks, 1f);
        this.animate(entity.deathAnimationState, EnergizedGolemAnimations.DIE, ageInTicks, 1f);
        this.animateWalk(EnergizedGolemAnimations.ROAMING, limbSwing, limbSwingAmount, 2f, 2.5f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        mob.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return mob;
    }
}
