package net.pekkatrol.hg2t.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(packOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {

        List<ItemLike> NICKEL_SMELTABLES = List.of(ModItems.RAW_NICKEL.get(),
                ModBlocks.NICKEL_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NICKEL_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.NICKEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.NICKEL_INGOT.get()), has(ModItems.NICKEL_INGOT.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":nickel_block_from_ingots");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NICKEL_INGOT.get(), 9)
                .requires(ModBlocks.NICKEL_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.NICKEL_BLOCK.get()), has(ModBlocks.NICKEL_BLOCK.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":nickel_ingots_from_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CARD_BOX.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModItems.BIO_POWDER.get())
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(ModItems.BIO_POWDER.get()), has(ModItems.BIO_POWDER.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":card_box_from_bio_powder_and_paper");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOLANUM.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.DIAMOND)
                .define('B', ModBlocks.MARVIN.get())
                .unlockedBy(getHasName(ModBlocks.MARVIN.get()), has(ModBlocks.MARVIN.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":solanum_from_marvin");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.SOLANUM.get()),
                        Ingredient.of(ModItems.EMPTY_CORE.get()),
                        Ingredient.of(ItemStack.EMPTY), RecipeCategory.MISC, ModItems.CORE.get()
                )
                .unlocks(getHasName(ModItems.EMPTY_CORE.get()), has(ModItems.EMPTY_CORE.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":core_from_smithing");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LUMIR_PLANKS.get(), 4)
                .requires(ModBlocks.LUMIR_LOG.get())
                .unlockedBy(getHasName(ModBlocks.LUMIR_LOG.get()), has(ModBlocks.LUMIR_LOG.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":lumir_planks_from_log");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COMPACT_LOG.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModBlocks.LUMIR_LOG.get())
                .unlockedBy(getHasName(ModBlocks.LUMIR_LOG.get()), has(ModBlocks.LUMIR_LOG.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":compact_log_from_logs");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 4)
                .pattern("A")
                .pattern("A")
                .define('A', ModBlocks.LUMIR_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.LUMIR_LOG.get()), has(ModBlocks.LUMIR_LOG.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":sticks_from_lumir_planks");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.WHEAT_SEEDS, 9)
                .requires(ModItems.WHEAT_POUCH.get())
                .unlockedBy(getHasName(ModItems.WHEAT_POUCH.get()), has(ModItems.WHEAT_POUCH.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":wheat_seeds_from_wheat_pouch");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WHEAT_POUCH.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Items.WHEAT_SEEDS)
                .unlockedBy(getHasName(Items.WHEAT_SEEDS), has(Items.WHEAT_SEEDS))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":wheat_pouch_from_seeds");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHAIR.get(), 2)
                .pattern("  A")
                .pattern("AAA")
                .pattern("A A")
                .define('A', ItemTags.PLANKS)
                .unlockedBy(getHasName(ModBlocks.LUMIR_PLANKS.get()), has(ItemTags.PLANKS))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":chair_from_planks");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TABLE.get(), 2)
                .pattern("   ")
                .pattern("AAA")
                .pattern("A A")
                .define('A', ItemTags.PLANKS)
                .unlockedBy(getHasName(ModBlocks.LUMIR_PLANKS.get()), has(ItemTags.PLANKS))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":table_from_planks");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATO_POUCH.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.TOMATO_SEEDS.get())
                .unlockedBy(getHasName(ModItems.TOMATO_SEEDS.get()), has(ModItems.TOMATO_SEEDS.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomato_pouch_from_seeds");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TOMATO_SEEDS.get(), 9)
                .requires(ModItems.TOMATO_POUCH.get())
                .unlockedBy(getHasName(ModItems.TOMATO_POUCH.get()), has(ModItems.TOMATO_POUCH.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomato_seeds_from_tomato_pouch");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TOMATO_SEEDS.get(), 1)
                .requires(ModItems.TOMATO.get())
                .unlockedBy(getHasName(ModItems.TOMATO.get()), has(ModItems.TOMATO.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomato_seeds_from_tomato");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KETCHOUP.get())
                .pattern(" B ")
                .pattern("CAC")
                .define('A', Items.GLASS_BOTTLE)
                .define('B', Items.SUGAR)
                .define('C', ModItems.TOMATO.get())
                .unlockedBy(getHasName(ModItems.TOMATO.get()), has(ModItems.TOMATO.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":ketchoup_from_tomato");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NOOKIA.get())
                .pattern("BCB")
                .pattern("CAC")
                .pattern("BCB")
                .define('A', Items.REDSTONE_BLOCK)
                .define('B', Items.NETHERITE_INGOT)
                .define('C', Items.OBSIDIAN)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":nookia_from_obsidian");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PRESENT.get())
                .pattern("BCB")
                .pattern("CAC")
                .pattern("BCB")
                .define('A', ModBlocks.CARD_BOX.get())
                .define('B', Items.RED_DYE)
                .define('C', Items.GREEN_DYE)
                .unlockedBy(getHasName(ModBlocks.CARD_BOX.get()), has(ModBlocks.CARD_BOX.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":present_from_card_box");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BIENVAILLANCE.get())
                .pattern("CAC")
                .pattern("CAC")
                .pattern("CBC")
                .define('A', Items.NETHER_STAR)
                .define('B', ModItems.COMPACT_LOG.get())
                .define('C', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":bienvaillance_from_nether_star");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MARVIN.get())
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('A', ModBlocks.NICKEL_BLOCK.get())
                .define('B', ModBlocks.MARBLE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.NICKEL_BLOCK.get()), has(ModBlocks.NICKEL_BLOCK.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":marvin_head");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.GLASS)
                .pattern("AA")
                .pattern("AA")
                .define('A', ModItems.GLASS_SHARDS.get())
                .unlockedBy(getHasName(ModItems.GLASS_SHARDS.get()), has(ModItems.GLASS_SHARDS.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":glass_from_glass_shard");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATITE_NUGGET.get())
                .pattern("AB")
                .pattern("CD")
                .define('A', ModItems.GLASS_SHARDS.get())
                .define('B', ModItems.ASH.get())
                .define('C', Items.LAPIS_LAZULI)
                .define('D', ModItems.TOMATO.get())
                .unlockedBy(getHasName(ModItems.GLASS_SHARDS.get()), has(ModItems.GLASS_SHARDS.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_from_craft");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TOMATITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.TOMATITE.get())
                .unlockedBy(getHasName(ModItems.TOMATITE.get()), has(ModItems.TOMATITE.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_block_from_tomatite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATITE.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.TOMATITE_NUGGET.get())
                .unlockedBy(getHasName(ModItems.TOMATITE_NUGGET.get()), has(ModItems.TOMATITE_NUGGET.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_from_tomatite_nugget");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TOMATITE_NUGGET.get(), 9)
                .requires(ModItems.TOMATITE.get())
                .unlockedBy(getHasName(ModItems.TOMATITE.get()), has(ModItems.TOMATITE.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_nugget_from_tomatite");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TOMATITE.get(), 9)
                .requires(ModBlocks.TOMATITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.TOMATITE_BLOCK.get()), has(ModBlocks.TOMATITE_BLOCK.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_from_tomatite_block");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATITE_GEAR.get())
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .define('A', ModItems.TOMATITE_NUGGET.get())
                .define('B', ModItems.TOMATITE.get())
                .unlockedBy(getHasName(ModItems.TOMATITE.get()), has(ModItems.TOMATITE.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_gear_from_tomatite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATITE_PLATE.get())
                .pattern("AA")
                .define('A', ModItems.TOMATITE.get())
                .unlockedBy(getHasName(ModItems.TOMATITE.get()), has(ModItems.TOMATITE.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_plate_from_tomatite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATITE_STICK.get())
                .pattern("A")
                .pattern("A")
                .define('A', ModItems.TOMATITE.get())
                .unlockedBy(getHasName(ModItems.TOMATITE.get()), has(ModItems.TOMATITE.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_stick_from_tomatite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TOMATITE_CASING.get())
                .pattern("CBC")
                .pattern("BAB")
                .pattern("CBC")
                .define('A', ModItems.SPAWNER_FRAGMENT.get())
                .define('B', ModItems.TOMATITE.get())
                .define('C', ModItems.TOMATITE_PLATE.get())
                .unlockedBy(getHasName(ModItems.SPAWNER_FRAGMENT.get()), has(ModItems.SPAWNER_FRAGMENT.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_casing_from_spawner_fragment");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATITE_BATTERY.get())
                .pattern("BAB")
                .pattern("BAB")
                .pattern("BAB")
                .define('A', Items.REDSTONE_BLOCK)
                .define('B', ModItems.TOMATITE_PLATE.get())
                .unlockedBy(getHasName(ModItems.TOMATITE_PLATE.get()), has(ModItems.TOMATITE_PLATE.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_battery_from_tomatite_plate");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATITE_TRANSISTOR.get())
                .pattern("BBB")
                .pattern("AAA")
                .pattern("BBB")
                .define('A', Items.GLOWSTONE)
                .define('B', ModItems.TOMATITE_PLATE.get())
                .unlockedBy(getHasName(ModItems.TOMATITE_PLATE.get()), has(ModItems.TOMATITE_PLATE.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_transistor_from_tomatite_plate");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENERGY_PIPE.get())
                .pattern("BBB")
                .pattern("AAA")
                .pattern("BBB")
                .define('A', ModItems.NICKEL_INGOT.get())
                .define('B', ModItems.TOMATITE_PLATE.get())
                .unlockedBy(getHasName(ModItems.TOMATITE_PLATE.get()), has(ModItems.TOMATITE_PLATE.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":energy_pipe_from_tomatite_plate");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOMATITE_CHIP.get())
                .pattern("BAB")
                .pattern("CCC")
                .pattern("BAB")
                .define('A', Items.QUARTZ_BLOCK)
                .define('B', ModItems.TOMATITE_TRANSISTOR.get())
                .define('C', ModItems.TOMATITE_PLATE.get())
                .unlockedBy(getHasName(ModItems.TOMATITE_TRANSISTOR.get()), has(ModItems.TOMATITE_TRANSISTOR.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":tomatite_chip_from_tomatite_transistor");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COMBUSTION_GENERATOR.get())
                .pattern("BCB")
                .pattern("BDB")
                .pattern("BAB")
                .define('A', ModBlocks.TOMATITE_CASING.get())
                .define('B', Items.IRON_BARS)
                .define('C', ModItems.TOMATITE_BATTERY.get())
                .define('D', Items.FURNACE)
                .unlockedBy(getHasName(ModBlocks.TOMATITE_CASING.get()), has(ModBlocks.TOMATITE_CASING.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":combustion_generator_from_tomatite_casing");



        oreSmelting(pRecipeOutput, NICKEL_SMELTABLES, RecipeCategory.MISC, ModItems.NICKEL_INGOT.get(), 0.25f, 200, "nickel");
        oreBlasting(pRecipeOutput, NICKEL_SMELTABLES, RecipeCategory.MISC, ModItems.NICKEL_INGOT.get(), 0.25f, 100, "nickel");

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.MISC, ModBlocks.MARBLE_BRICK.get(), ModBlocks.MARBLE_BLOCK.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.MISC, ModBlocks.MARBLE_PILLAR.get(), ModBlocks.MARBLE_BLOCK.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.MISC, ModBlocks.MARBLE_WALL.get(), ModBlocks.MARBLE_BLOCK.get());

        stairBuilder(ModBlocks.LUMIR_STAIRS.get(), Ingredient.of(ModBlocks.LUMIR_PLANKS.get())).group("lumir")
                .unlockedBy(getHasName(ModBlocks.LUMIR_PLANKS.get()), has(ModBlocks.LUMIR_PLANKS.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMIR_SLAB.get(), ModBlocks.LUMIR_PLANKS.get());
        buttonBuilder(ModBlocks.LUMIR_BUTTON.get(), Ingredient.of(ModBlocks.LUMIR_PLANKS.get())).group("lumir")
                .unlockedBy(getHasName(ModBlocks.LUMIR_PLANKS.get()), has(ModBlocks.LUMIR_PLANKS.get())).save(pRecipeOutput);
        pressurePlate(pRecipeOutput, ModBlocks.LUMIR_PRESSURE_PLATE.get(), ModBlocks.LUMIR_PLANKS.get());
        fenceBuilder(ModBlocks.LUMIR_FENCE.get(), Ingredient.of(ModBlocks.LUMIR_PLANKS.get())).group("lumir")
                .unlockedBy(getHasName(ModBlocks.LUMIR_PLANKS.get()), has(ModBlocks.LUMIR_PLANKS.get())).save(pRecipeOutput);
        fenceGateBuilder(ModBlocks.LUMIR_FENCE_GATE.get(), Ingredient.of(ModBlocks.LUMIR_PLANKS.get())).group("lumir")
                .unlockedBy(getHasName(ModBlocks.LUMIR_PLANKS.get()), has(ModBlocks.LUMIR_PLANKS.get())).save(pRecipeOutput);

        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MARBLE_WALL.get(), ModBlocks.MARBLE_WALL.get());

        doorBuilder(ModBlocks.LUMIR_DOOR.get(), Ingredient.of(ModBlocks.LUMIR_PLANKS.get())).group("lumir")
                .unlockedBy(getHasName(ModBlocks.LUMIR_PLANKS.get()), has(ModBlocks.LUMIR_PLANKS.get())).save(pRecipeOutput);
        trapdoorBuilder(ModBlocks.LUMIR_TRAPDOOR.get(), Ingredient.of(ModBlocks.LUMIR_PLANKS.get())).group("lumir")
                .unlockedBy(getHasName(ModBlocks.LUMIR_PLANKS.get()), has(ModBlocks.LUMIR_PLANKS.get())).save(pRecipeOutput);

    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult
            ,float pExperience, int pCookingTime, String pGroup) {
         oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                 pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult
            ,float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemLike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemLike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemLike), has(itemLike))
                    .save(recipeOutput, HG2Tomato.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemLike));
        }
    }
}
