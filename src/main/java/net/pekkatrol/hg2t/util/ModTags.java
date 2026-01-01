package net.pekkatrol.hg2t.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.pekkatrol.hg2t.HG2Tomato;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> NEEDS_NOOKIA_TOOL = createTag("needs_nookia_tool");
        public static final TagKey<Block> INCORRECT_FOR_NOOKIA_TOOL = createTag("incorrect_for_nookia_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> MAGICAL_CROPS = createTag("magical_crops");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, name));
        }
    }
}
