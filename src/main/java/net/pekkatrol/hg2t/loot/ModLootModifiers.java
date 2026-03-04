package net.pekkatrol.hg2t.loot;

import com.mojang.serialization.MapCodec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;

public class ModLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, HG2Tomato.MOD_ID);

    public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

    public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> ADD_MULTIPLE_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_multiple_item", () -> AddMultipleItemModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
