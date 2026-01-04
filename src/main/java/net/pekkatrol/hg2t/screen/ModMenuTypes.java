package net.pekkatrol.hg2t.screen;

import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.screen.custom.CardboardMenu;
import net.pekkatrol.hg2t.screen.custom.PresentMenu;
import net.pekkatrol.hg2t.screen.custom.PresentScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, HG2Tomato.MOD_ID);


    public static final RegistryObject<MenuType<PresentMenu>> PRESENT_MENU =
            MENUS.register("present_menu", () -> IForgeMenuType.create(PresentMenu::new));

    public static final RegistryObject<MenuType<CardboardMenu>> CARDBOARD_MENU =
            MENUS.register("cardboard_menu", () -> IForgeMenuType.create(CardboardMenu::new));


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}