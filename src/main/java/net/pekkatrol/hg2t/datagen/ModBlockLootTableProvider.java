package net.pekkatrol.hg2t.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.Block;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.item.ModItems;

import java.util.Iterator;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.CARD_BOX.get());
        dropSelf(ModBlocks.MARBLE_PILLAR.get());
        dropSelf(ModBlocks.MARBLE_BRICK.get());
        dropSelf(ModBlocks.MARBLE_WALL.get());
        dropSelf(ModBlocks.MARBLE_BLOCK.get());
        dropSelf(ModBlocks.NICKEL_BLOCK.get());
        dropSelf(ModBlocks.NICKEL_ORE.get());
        dropSelf(ModBlocks.LUMIR_LOG.get());
        dropSelf(ModBlocks.LUMIR_PLANKS.get());
        dropSelf(ModBlocks.LUMIR_STAIRS.get());
        dropSelf(ModBlocks.LUMIR_FENCE.get());
        dropSelf(ModBlocks.LUMIR_FENCE_GATE.get());
        dropSelf(ModBlocks.LUMIR_TRAPDOOR.get());
        dropSelf(ModBlocks.LUMIR_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LUMIR_BUTTON.get());


        this.add(ModBlocks.LUMIR_SLAB.get(), block -> createSlabItemTable(ModBlocks.LUMIR_SLAB.get()));

        this.add(ModBlocks.LUMIR_DOOR.get(), block -> createDoorTable(ModBlocks.LUMIR_DOOR.get()));

        this.add(ModBlocks.NICKEL_ORE.get(),
        block -> createMultipleOreDrops(ModBlocks.NICKEL_ORE.get(), ModItems.RAW_NICKEL.get(), 2, 6));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
