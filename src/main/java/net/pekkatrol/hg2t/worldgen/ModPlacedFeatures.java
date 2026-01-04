package net.pekkatrol.hg2t.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.OverreachingTickFix;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> NICKEL_ORE_PLACED_KEY = registerKey("nickel_ore_placed");

    public static final ResourceKey<PlacedFeature> LUMIR_PLACED_KEY = registerKey("lumir_placed");

    public static final ResourceKey<PlacedFeature> MARBLE_PLACED_KEY = registerKey("marble_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


        register(context, NICKEL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_NICKEL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, LUMIR_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LUMIR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.LUMIR_SAPLING.get()));

        register(context, MARBLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MARBLE_KEY),
                ModOrePlacement.commonOrePlacement(
                        10, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(80))));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
