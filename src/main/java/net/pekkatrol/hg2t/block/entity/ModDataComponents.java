package net.pekkatrol.hg2t.block.entity;

import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;

import static net.pekkatrol.hg2t.HG2Tomato.MOD_ID;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, "hgtotomato");

    public static final RegistryObject<DataComponentType<CompoundTag>> CARDBOX_INVENTORY =
            DATA_COMPONENTS.register("cardbox_inventory",
                    () -> DataComponentType.<CompoundTag>builder()
                            .persistent(CompoundTag.CODEC)
                            .build());
}

