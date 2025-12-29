package net.pekkatrol.hg2t.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KETCHOUP.get())
                .pattern(" B ")
                .pattern("CAC")
                .define('A', Items.GLASS_BOTTLE)
                .define('B', Items.SUGAR)
                .define('C', ModItems.TOMATO.get())
                .unlockedBy(getHasName(ModItems.TOMATO.get()), has(ModItems.TOMATO.get()))
                .save(pRecipeOutput, HG2Tomato.MOD_ID + ":ketchoup_from_tomato");



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

        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MARBLE_WALL.get(), ModBlocks.LUMIR_PLANKS.get());

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
