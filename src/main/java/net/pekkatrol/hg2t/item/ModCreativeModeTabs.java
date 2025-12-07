package net.pekkatrol.hg2t.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HG2Tomato.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HGTOTOMATO_ITEMS_TAB = CREATIVE_MODE_TABS.register("hgtotomato_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BANANA.get()))
                    .title(Component.translatable("creativetab.hgtotomato.hgtotomato_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BANANA.get());
                        output.accept(ModItems.WHEAT_POUCH.get());
                        output.accept(ModItems.BIO_POWDER.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> HGTOTOMATO_BLOCKS_TAB = CREATIVE_MODE_TABS.register("hgtotomato_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.MARBLE_BLOCK.get()))
                    .withTabsBefore(HGTOTOMATO_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.hgtotomato.hgtotomato_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.MARBLE_BLOCK.get());
                        output.accept(ModBlocks.MARBLE_BRICK.get());
                        output.accept(ModBlocks.MARBLE_PILLAR.get());
                        output.accept(ModBlocks.CARD_BOX.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
