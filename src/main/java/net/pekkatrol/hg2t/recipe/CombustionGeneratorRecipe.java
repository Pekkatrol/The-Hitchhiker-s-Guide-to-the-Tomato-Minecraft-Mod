package net.pekkatrol.hg2t.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record CombustionGeneratorRecipe(Ingredient inputItem, ItemStack output) implements Recipe<CombustionGeneratorRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(CombustionGeneratorRecipeInput pInput, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        return inputItem.test(pInput.getItem(0));
    }

    @Override
    public ItemStack assemble(CombustionGeneratorRecipeInput pInput, HolderLookup.Provider pRegistries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COMBUSTION_GENERATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.COMBUSTION_GENERATOR_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<CombustionGeneratorRecipe> {

        public static final MapCodec<CombustionGeneratorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CombustionGeneratorRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(CombustionGeneratorRecipe::output)
        ).apply(inst, CombustionGeneratorRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CombustionGeneratorRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, CombustionGeneratorRecipe::inputItem,
                        ItemStack.STREAM_CODEC, CombustionGeneratorRecipe::output,
                        CombustionGeneratorRecipe::new);

        @Override
        public MapCodec<CombustionGeneratorRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CombustionGeneratorRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
