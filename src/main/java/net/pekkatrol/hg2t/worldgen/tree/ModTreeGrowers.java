package net.pekkatrol.hg2t.worldgen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrowers {

    public static final TreeGrower LUMIR = new TreeGrower(HG2Tomato.MOD_ID + "lumir",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LUMIR_KEY), Optional.empty());
}
