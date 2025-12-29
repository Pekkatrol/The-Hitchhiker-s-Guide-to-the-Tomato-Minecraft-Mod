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
                        output.accept(ModItems.RAW_NICKEL.get());
                        output.accept(ModItems.NICKEL_INGOT.get());
                        output.accept(ModItems.COMPACT_LOG.get());
                        output.accept(ModItems.TOMATO_SEEDS.get());
                        output.accept(ModItems.TOMATO.get());
                        output.accept(ModItems.TOMATO_POUCH.get());
                        output.accept(ModItems.KETCHOUP.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> HGTOTOMATO_BLOCKS_TAB = CREATIVE_MODE_TABS.register("hgtotomato_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.MARBLE_BLOCK.get()))
                    .withTabsBefore(HGTOTOMATO_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.hgtotomato.hgtotomato_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.BLACK_SAND.get());
                        output.accept(ModBlocks.MARBLE_BLOCK.get());
                        output.accept(ModBlocks.MARBLE_BRICK.get());
                        output.accept(ModBlocks.MARBLE_PILLAR.get());
                        output.accept(ModBlocks.MARBLE_WALL.get());
                        output.accept(ModBlocks.CARD_BOX.get());
                        output.accept(ModBlocks.NICKEL_BLOCK.get());
                        output.accept(ModBlocks.NICKEL_ORE.get());
                        output.accept(ModBlocks.LUMIR_SAPLING.get());
                        output.accept(ModBlocks.LUMIR_LEAVES.get());
                        output.accept(ModBlocks.LUMIR_LOG.get());
                        output.accept(ModBlocks.STRIPPED_LUMIR_LOG.get());
                        output.accept(ModBlocks.LUMIR_PLANKS.get());
                        output.accept(ModBlocks.LUMIR_SLAB.get());
                        output.accept(ModBlocks.LUMIR_FENCE.get());
                        output.accept(ModBlocks.LUMIR_FENCE_GATE.get());
                        output.accept(ModBlocks.LUMIR_STAIRS.get());
                        output.accept(ModBlocks.LUMIR_DOOR.get());
                        output.accept(ModBlocks.LUMIR_TRAPDOOR.get());
                        output.accept(ModBlocks.LUMIR_BUTTON.get());
                        output.accept(ModBlocks.LUMIR_PRESSURE_PLATE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
