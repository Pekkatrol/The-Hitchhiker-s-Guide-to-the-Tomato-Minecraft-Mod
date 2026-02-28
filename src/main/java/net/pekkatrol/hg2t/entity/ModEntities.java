package net.pekkatrol.hg2t.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.entity.custom.ChairEntity;
import net.pekkatrol.hg2t.entity.custom.EnergizedGolemEntity;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HG2Tomato.MOD_ID);

    public static final RegistryObject<EntityType<ChairEntity>> CHAIR =
            ENTITY_TYPES.register("chair", () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("chair_entity"));

    public static final RegistryObject<EntityType<EnergizedGolemEntity>> ENERGIZED_GOLEM =
           ENTITY_TYPES.register("energized_golem",
                   () -> EntityType.Builder.of(EnergizedGolemEntity::new, MobCategory.MONSTER)
                   .sized(1.5f, 1.5f)
                   .build("energized_golem"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
