package net.pekkatrol.hg2t.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HG2Tomato.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MARBLE_BLOCK.get())
                .add(ModBlocks.MARBLE_PILLAR.get())
                .add(ModBlocks.MARBLE_BRICK.get())
                .add(ModBlocks.MARBLE_WALL.get())
                .add(ModBlocks.NICKEL_BLOCK.get())
                .add(ModBlocks.NICKEL_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.NICKEL_BLOCK.get())
                .add(ModBlocks.NICKEL_ORE.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.CARD_BOX.get())
                .add(ModBlocks.LUMIR_LOG.get())
                .add(ModBlocks.LUMIR_PLANKS.get())
                .add(ModBlocks.LUMIR_SLAB.get())
                .add(ModBlocks.LUMIR_STAIRS.get())
                .add(ModBlocks.LUMIR_FENCE.get())
                .add(ModBlocks.LUMIR_FENCE_GATE.get())
                .add(ModBlocks.LUMIR_BUTTON.get())
                .add(ModBlocks.LUMIR_DOOR.get())
                .add(ModBlocks.LUMIR_TRAPDOOR.get());

        tag(BlockTags.FENCES).add(ModBlocks.LUMIR_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.LUMIR_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.MARBLE_WALL.get());
        tag(BlockTags.LOGS).add(ModBlocks.LUMIR_LOG.get());
        tag(BlockTags.SAND).add(ModBlocks.BLACK_SAND.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.LUMIR_LOG.get())
                .add(ModBlocks.STRIPPED_LUMIR_LOG.get());
    }
}
