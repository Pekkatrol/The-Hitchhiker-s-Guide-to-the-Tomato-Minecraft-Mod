package net.pekkatrol.hg2t.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.item.custom.BienvaillanceItem;
import net.pekkatrol.hg2t.item.custom.FuelItem;
import net.pekkatrol.hg2t.item.custom.NookiaItem;
import org.spongepowered.asm.mixin.extensibility.IActivityContext;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HG2Tomato.MOD_ID);

    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BANANA)));

    public static final RegistryObject<Item> WHEAT_POUCH = ITEMS.register("wheat_pouch",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BIO_POWDER = ITEMS.register("bio_powder",
            () -> new BoneMealItem(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    if (Screen.hasShiftDown()) {
                        pTooltipComponents.add(Component.translatable("tooltip.hgtotomato.bio_powder.shift_down"));
                    } else {
                        pTooltipComponents.add(Component.translatable("tooltip.hgtotomato.bio_powder"));
                    }
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_NICKEL = ITEMS.register("raw_nickel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COMPACT_LOG = ITEMS.register("compact_log",
            () -> new FuelItem(new Item.Properties(), 16000));

    public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds",
            () -> new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties().food(ModFoodProperties.TOMATO)));

    public static final RegistryObject<Item> KETCHOUP = ITEMS.register("ketchoup",
            () -> new Item(new Item.Properties().stacksTo(4).food(ModFoodProperties.KETCHOUP)));

    public static final RegistryObject<Item> TOMATO_POUCH = ITEMS.register("tomato_pouch",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NOOKIA = ITEMS.register("nookia",
            () -> new NookiaItem(ModToolTiers.NOOKIA, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.NOOKIA, 1, -2.8f)))
            {
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.hgtotomato.nookia"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> BIENVAILLANCE = ITEMS.register("bienvaillance",
            () -> new BienvaillanceItem(ModToolTiers.NOOKIA, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.NOOKIA, 4, -2.4f))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
