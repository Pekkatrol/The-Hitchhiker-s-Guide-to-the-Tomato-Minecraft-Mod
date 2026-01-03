package net.pekkatrol.hg2t.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.recipe.PresentRecipe;
import org.checkerframework.checker.nullness.qual.Nullable;

public class PresentRecipeCategory implements IRecipeCategory {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "present");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID,
        "textures/gui/present/present_gui.png");
    public static final RecipeType<PresentRecipe> PRESENT_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, PresentRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public PresentRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.PRESENT.get()));
    }


    @Override
    public RecipeType<PresentRecipe> getRecipeType() {
        return PRESENT_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.hgtotomato.present");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, Object recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(((PresentRecipe)recipe).getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(((PresentRecipe)recipe).getResultItem(null));
    }
}
