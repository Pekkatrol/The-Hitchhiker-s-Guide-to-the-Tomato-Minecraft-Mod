package net.pekkatrol.hg2t.event;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.entity.ModEntities;
import net.pekkatrol.hg2t.entity.client.EnergizedGolemModel;
import net.pekkatrol.hg2t.entity.custom.EnergizedGolemEntity;

@Mod.EventBusSubscriber(modid = HG2Tomato.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EnergizedGolemModel.LAYER_LOCATION, EnergizedGolemModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ENERGIZED_GOLEM.get(), EnergizedGolemEntity.createAttributes().build());
    }
}
