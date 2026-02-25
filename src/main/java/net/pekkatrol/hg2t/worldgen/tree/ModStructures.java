package net.pekkatrol.hg2t.worldgen.tree;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.pekkatrol.hg2t.HG2Tomato;

import java.util.Map;

public class ModStructures {

    public static final ResourceKey<Structure> HOUSE_STRUCTURE =
            ResourceKey.create(
                    Registries.STRUCTURE,
                    ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "house")
            );

    public static void bootstrap(BootstrapContext<Structure> context) {

        HolderGetter<StructureTemplatePool> pools =
                context.lookup(Registries.TEMPLATE_POOL);

        context.register(
                HOUSE_STRUCTURE,
                new JigsawStructure(
                        new Structure.StructureSettings(
                                context.lookup(Registries.BIOME)
                                        .getOrThrow(BiomeTags.IS_OVERWORLD),
                                Map.of(),
                                GenerationStep.Decoration.SURFACE_STRUCTURES,
                                TerrainAdjustment.BEARD_THIN
                        ),
                        pools.getOrThrow(
                                ResourceKey.create(
                                        Registries.TEMPLATE_POOL,
                                        ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "house/start_pool")
                                )
                        ),
                        1, // depth
                        ConstantHeight.of(VerticalAnchor.absolute(0)),
                        false
                )
        );
    }



}

