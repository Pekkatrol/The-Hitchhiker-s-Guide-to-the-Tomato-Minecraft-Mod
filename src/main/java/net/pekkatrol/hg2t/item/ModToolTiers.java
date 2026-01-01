package net.pekkatrol.hg2t.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.pekkatrol.hg2t.util.ModTags;

public class ModToolTiers {
    public static final Tier NOOKIA = new ForgeTier(2147483647,
            0.15f,
            10.0f,
            0,
            ModTags.Blocks.NEEDS_NOOKIA_TOOL,
            () -> Ingredient.EMPTY,
            ModTags.Blocks.INCORRECT_FOR_NOOKIA_TOOL);
}
