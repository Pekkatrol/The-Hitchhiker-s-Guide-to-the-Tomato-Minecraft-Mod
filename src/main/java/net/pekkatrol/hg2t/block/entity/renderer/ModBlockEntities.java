package net.pekkatrol.hg2t.block.entity.renderer;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.block.entity.custom.*;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HG2Tomato.MOD_ID);

    public static final RegistryObject<BlockEntityType<CombustionGeneratorBlockEntity>> COMBUSTION_GENERATOR_BE =
            BLOCK_ENTITIES.register("combustion_generator_be", () -> BlockEntityType.Builder.of(
                    CombustionGeneratorBlockEntity::new, ModBlocks.COMBUSTION_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<EnergyPipeBlockEntity>> ENERGY_PIPE_BE =
            BLOCK_ENTITIES.register("energy_pipe_be", () -> BlockEntityType.Builder.of(
                    EnergyPipeBlockEntity::new, ModBlocks.ENERGY_PIPE.get()).build(null));

    public static final RegistryObject<BlockEntityType<TomatiteTankBlockEntity>> TOMATITE_TANK_BE =
            BLOCK_ENTITIES.register("tomatite_tank_be", () -> BlockEntityType.Builder.of(
                    TomatiteTankBlockEntity::new, ModBlocks.TOMATITE_TANK.get()).build(null));

    public static final RegistryObject<BlockEntityType<PresentBlockEntity>> PRESENT_BE =
            BLOCK_ENTITIES.register("present_be", () -> BlockEntityType.Builder.of(
                    PresentBlockEntity::new, ModBlocks.PRESENT.get()).build(null));

    public static final RegistryObject<BlockEntityType<CardboardBlockEntity>> CARDBOARD_BE =
            BLOCK_ENTITIES.register("cardboard_be", () -> BlockEntityType.Builder.of(
                    CardboardBlockEntity::new, ModBlocks.CARD_BOX.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
