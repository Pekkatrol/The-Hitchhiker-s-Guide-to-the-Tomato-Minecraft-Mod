package net.pekkatrol.hg2t.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import org.spongepowered.asm.mixin.extensibility.IActivityContext;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HG2Tomato.MOD_ID);

    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WHEAT_POUCH = ITEMS.register("wheat_pouch",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BIO_POWDER = ITEMS.register("bio_powder",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
