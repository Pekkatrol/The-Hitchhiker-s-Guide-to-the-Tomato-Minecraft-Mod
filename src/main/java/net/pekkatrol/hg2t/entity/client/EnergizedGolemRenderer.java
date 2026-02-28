package net.pekkatrol.hg2t.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.entity.custom.EnergizedGolemEntity;

public class EnergizedGolemRenderer extends MobRenderer<EnergizedGolemEntity, EnergizedGolemModel<EnergizedGolemEntity>> {

    public EnergizedGolemRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EnergizedGolemModel<>(pContext.bakeLayer(EnergizedGolemModel.LAYER_LOCATION)), 0.86f);
    }

    @Override
    protected float getFlipDegrees(EnergizedGolemEntity entity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(EnergizedGolemEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "textures/entity/energized_golem/energized_golem.png");
    }

}
