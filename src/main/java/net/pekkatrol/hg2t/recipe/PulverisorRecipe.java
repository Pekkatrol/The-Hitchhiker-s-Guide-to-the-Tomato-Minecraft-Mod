package net.pekkatrol.hg2t.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record PulverisorRecipe(Ingredient inputItem, ItemStack output, int energyCost) implements Recipe<PulverisorRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(PulverisorRecipeInput pInput, Level pLevel) {
        if (pLevel.isClientSide()) return false;
        return inputItem.test(pInput.getItem(0));
    }

    @Override
    public ItemStack assemble(PulverisorRecipeInput pInput, HolderLookup.Provider pRegistries) {
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
        return ModRecipes.PULVERISOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.PULVERISOR_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<PulverisorRecipe> {

        public static final MapCodec<PulverisorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(PulverisorRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(PulverisorRecipe::output),
                Codec.INT.optionalFieldOf("energy_cost", 30).forGetter(PulverisorRecipe::energyCost)
        ).apply(inst, PulverisorRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, PulverisorRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, PulverisorRecipe::inputItem,
                        ItemStack.STREAM_CODEC, PulverisorRecipe::output,
                        ByteBufCodecs.INT, PulverisorRecipe::energyCost,
                        PulverisorRecipe::new);

        @Override
        public MapCodec<PulverisorRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PulverisorRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
