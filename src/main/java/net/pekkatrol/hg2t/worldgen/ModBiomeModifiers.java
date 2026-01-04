package net.pekkatrol.hg2t.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.pekkatrol.hg2t.HG2Tomato;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_NICKEL_ORE = registerKey("add_nickel_ore");

    public static final ResourceKey<BiomeModifier> ADD_LUMIR_TREE = registerKey("add_lumir_tree");

    public static final ResourceKey<BiomeModifier> ADD_MARBLE = registerKey("add_marble");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_NICKEL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.NICKEL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(
                ADD_MARBLE,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.getOrThrow(Biomes.PLAINS),
                                biomes.getOrThrow(Biomes.BEACH),
                                biomes.getOrThrow(Biomes.WINDSWEPT_HILLS)),
                        HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.MARBLE_PLACED_KEY)), GenerationStep.Decoration.UNDERGROUND_DECORATION));

        context.register(ADD_LUMIR_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.BEACH)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.LUMIR_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, name));
    }
}
