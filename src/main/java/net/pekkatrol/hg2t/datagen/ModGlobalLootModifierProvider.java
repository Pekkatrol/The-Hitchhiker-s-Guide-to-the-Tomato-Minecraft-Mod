package net.pekkatrol.hg2t.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.item.ModItems;
import net.pekkatrol.hg2t.loot.AddItemModifier;
import net.pekkatrol.hg2t.loot.AddMultipleItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {


    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, HG2Tomato.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {
        this.add("tomato_seeds_from_short_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, ModItems.TOMATO_SEEDS.get()));

        this.add("tomato_seeds_from_tall_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, ModItems.TOMATO_SEEDS.get()));

        this.add("snow_ball_from_spawner",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SPAWNER).build(),
                        LootItemRandomChanceCondition.randomChance(1f).build() }, ModItems.SPAWNER_FRAGMENT.get()));

        this.add("glass_shards_from_glass",
                new AddMultipleItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GLASS).build(),
                        LootItemRandomChanceCondition.randomChance(1f).build() }, ModItems.GLASS_SHARDS.get(),
                        3));
    }
}
