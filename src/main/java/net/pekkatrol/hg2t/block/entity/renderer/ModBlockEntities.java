package net.pekkatrol.hg2t.block.entity.renderer;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.block.entity.custom.PresentBlockEntity;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HG2Tomato.MOD_ID);

    public static final RegistryObject<BlockEntityType<PresentBlockEntity>> PRESENT_BE =
            BLOCK_ENTITIES.register("present_be", () -> BlockEntityType.Builder.of(
                    PresentBlockEntity::new, ModBlocks.PRESENT.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
