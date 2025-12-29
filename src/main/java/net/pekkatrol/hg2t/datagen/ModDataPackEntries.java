package net.pekkatrol.hg2t.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.worldgen.ModBiomeModifiers;
import net.pekkatrol.hg2t.worldgen.ModConfiguredFeatures;
import net.pekkatrol.hg2t.worldgen.ModPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataPackEntries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public ModDataPackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(HG2Tomato.MOD_ID));
    }
}
