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
import net.pekkatrol.hg2t.recipe.ModRecipes;
import net.pekkatrol.hg2t.recipe.PresentRecipe;
import net.pekkatrol.hg2t.screen.custom.PresentScreen;

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
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<PresentRecipe> presentRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.PRESENT_TYPE.get()).stream().map(RecipeHolder::value).toList();

        registration.addRecipes(PresentRecipeCategory.PRESENT_RECIPE_RECIPE_TYPE, presentRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(PresentScreen.class, 70, 30, 25, 20,
                PresentRecipeCategory.PRESENT_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PRESENT.get().asItem()),
                PresentRecipeCategory.PRESENT_RECIPE_RECIPE_TYPE);
    }
}
