package net.pekkatrol.hg2t;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.block.entity.ModDataComponents;
import net.pekkatrol.hg2t.block.entity.renderer.ModBlockEntities;
import net.pekkatrol.hg2t.entity.ModEntities;
import net.pekkatrol.hg2t.entity.client.ChairRenderer;
import net.pekkatrol.hg2t.entity.client.EnergizedGolemRenderer;
import net.pekkatrol.hg2t.item.ModCreativeModeTabs;
import net.pekkatrol.hg2t.item.ModItems;
import net.pekkatrol.hg2t.loot.ModLootModifiers;
import net.pekkatrol.hg2t.networking.ModMessages;
import net.pekkatrol.hg2t.particle.EnergizedParticles;
import net.pekkatrol.hg2t.particle.ModParticles;
import net.pekkatrol.hg2t.potion.ModPotions;
import net.pekkatrol.hg2t.recipe.ModRecipes;
import net.pekkatrol.hg2t.screen.ModMenuTypes;
import net.pekkatrol.hg2t.screen.custom.*;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(HG2Tomato.MOD_ID)
public class HG2Tomato
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "hgtotomato";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public HG2Tomato()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModEntities.register(modEventBus);

        ModParticles.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModMessages.register();


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        ModDataComponents.DATA_COMPONENTS.register(FMLJavaModLoadingContext.get().getModEventBus());


        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.BANANA.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO_SEEDS.get(), 0.15f);
        });
    }
    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        //if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
        //    event.accept(ModItems.BANANA);
        //}
        //if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
        //    event.accept(ModBlocks.MARBLE_BLOCK);
        //}
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }
    //You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.PRESENT_MENU.get(), PresentScreen::new);
            MenuScreens.register(ModMenuTypes.CARDBOARD_MENU.get(), CardboardScreen::new);
            MenuScreens.register(ModMenuTypes.COMBUSTION_GENERATOR_MENU.get(), CombustionGeneratorScreen::new);
            MenuScreens.register(ModMenuTypes.TOMATITE_TANK_MENU.get(), TomatiteTankScreen::new);
            MenuScreens.register(ModMenuTypes.COMPRESSOR_MENU.get(), CompressorScreen::new);
            MenuScreens.register(ModMenuTypes.PULVERISOR_MENU.get(), PulverisorScreen::new);

            EntityRenderers.register(ModEntities.ENERGIZED_GOLEM.get(), EnergizedGolemRenderer::new);

            EntityRenderers.register(ModEntities.CHAIR.get(), ChairRenderer::new);
        }

        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.ENERGIZED_PARTICLES.get(), EnergizedParticles.Provider::new);
        }
    }
}
