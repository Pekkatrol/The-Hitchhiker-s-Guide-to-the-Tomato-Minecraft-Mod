package net.pekkatrol.hg2t.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.recipe.*;
import net.pekkatrol.hg2t.screen.custom.CombustionGeneratorScreen;
import net.pekkatrol.hg2t.screen.custom.CompressorScreen;
import net.pekkatrol.hg2t.screen.custom.PresentScreen;
import net.pekkatrol.hg2t.screen.custom.PulverisorScreen;

import java.util.List;

@JeiPlugin
public class JEIHGtotomatoPlugin implements IModPlugin {


    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PresentRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new CombustionGeneratorRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new CompressorRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new PulverisorRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<PresentRecipe> presentRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.PRESENT_TYPE.get()).stream().map(RecipeHolder::value).toList();

        registration.addRecipes(PresentRecipeCategory.PRESENT_RECIPE_RECIPE_TYPE, presentRecipes);

        List<CombustionGeneratorRecipe> combustionGeneratorRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.COMBUSTION_GENERATOR_TYPE.get()).stream().map(RecipeHolder::value).toList();

        registration.addRecipes(CombustionGeneratorRecipeCategory.COMBUSTION_GENERATOR_RECIPE_RECIPE_TYPE, combustionGeneratorRecipes);

        List<CompressorRecipe> compressorRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.COMPRESSOR_TYPE.get()).stream().map(RecipeHolder::value).toList();

        registration.addRecipes(CompressorRecipeCategory.COMPRESSOR_RECIPE_RECIPE_TYPE, compressorRecipes);

        List<PulverisorRecipe> pulverisorRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.PULVERISOR_TYPE.get()).stream().map(RecipeHolder::value).toList();

        registration.addRecipes(PulverisorRecipeCategory.PULVERISOR_RECIPE_RECIPE_TYPE, pulverisorRecipes);

    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(PresentScreen.class, 70, 30, 25, 20,
                PresentRecipeCategory.PRESENT_RECIPE_RECIPE_TYPE);

        registration.addRecipeClickArea(CombustionGeneratorScreen.class, 50, 35, 25, 20,
                CombustionGeneratorRecipeCategory.COMBUSTION_GENERATOR_RECIPE_RECIPE_TYPE);

        registration.addRecipeClickArea(CompressorScreen.class, 50, 35, 25, 20,
                CompressorRecipeCategory.COMPRESSOR_RECIPE_RECIPE_TYPE);

        registration.addRecipeClickArea(PulverisorScreen.class, 50, 35, 25, 20,
                PulverisorRecipeCategory.PULVERISOR_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PRESENT.get().asItem()),
                PresentRecipeCategory.PRESENT_RECIPE_RECIPE_TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMBUSTION_GENERATOR.get().asItem()),
                CombustionGeneratorRecipeCategory.COMBUSTION_GENERATOR_RECIPE_RECIPE_TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMPRESSOR.get().asItem()),
                CompressorRecipeCategory.COMPRESSOR_RECIPE_RECIPE_TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PULVERISOR.get().asItem()),
                PulverisorRecipeCategory.PULVERISOR_RECIPE_RECIPE_TYPE);
    }
}
