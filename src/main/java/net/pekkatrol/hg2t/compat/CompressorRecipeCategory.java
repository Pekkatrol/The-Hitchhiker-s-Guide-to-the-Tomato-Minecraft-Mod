package net.pekkatrol.hg2t.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
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
import net.pekkatrol.hg2t.recipe.CombustionGeneratorRecipe;
import net.pekkatrol.hg2t.recipe.CompressorRecipe;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CompressorRecipeCategory implements IRecipeCategory<CompressorRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "compressor");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID,
            "textures/gui/compressor/compressor_gui.png");
    public static final RecipeType<CompressorRecipe> COMPRESSOR_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, CompressorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CompressorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.COMPRESSOR.get()));
    }

    @Override
    public RecipeType<CompressorRecipe> getRecipeType() {
        return COMPRESSOR_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.hgtotomato.compressor");
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
    public void setRecipe(IRecipeLayoutBuilder builder, CompressorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 34)
                .addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 34)
                .addItemStack(recipe.getResultItem(null));
    }

    @Override
    public List<Component> getTooltipStrings(CompressorRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        List<Component> tooltips = new ArrayList<>();
        tooltips.add(Component.literal("Energy requiered : " + recipe.energyCost() * 72 + " FE"));
        return tooltips;
    }
}
