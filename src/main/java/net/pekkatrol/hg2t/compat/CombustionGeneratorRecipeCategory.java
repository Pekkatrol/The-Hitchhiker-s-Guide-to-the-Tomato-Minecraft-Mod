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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.block.ModBlocks;
import net.pekkatrol.hg2t.recipe.CombustionGeneratorRecipe;
import net.pekkatrol.hg2t.recipe.PresentRecipe;
import org.checkerframework.checker.nullness.qual.Nullable;

import static net.pekkatrol.hg2t.compat.PresentRecipeCategory.PRESENT_RECIPE_RECIPE_TYPE;

public class CombustionGeneratorRecipeCategory implements IRecipeCategory<CombustionGeneratorRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "combustion_generator");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID,
            "textures/gui/combustion_generator/combustion_generator_gui.png");
    public static final RecipeType<CombustionGeneratorRecipe> COMBUSTION_GENERATOR_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, CombustionGeneratorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CombustionGeneratorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.COMBUSTION_GENERATOR.get()));
    }

    @Override
    public RecipeType<CombustionGeneratorRecipe> getRecipeType() {
        return COMBUSTION_GENERATOR_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.hgtotomato.combustion_generator");
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
    public void setRecipe(IRecipeLayoutBuilder builder, CombustionGeneratorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 34)
                .addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 34)
                .addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(CombustionGeneratorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int totalEnergy = recipe.energyProduced() * 72;
        guiGraphics.drawString(
                Minecraft.getInstance().font,
                "\u26a1 " + totalEnergy + " FE",
                5, 5, 0xFF_FF6600, false
        );
    }
}
