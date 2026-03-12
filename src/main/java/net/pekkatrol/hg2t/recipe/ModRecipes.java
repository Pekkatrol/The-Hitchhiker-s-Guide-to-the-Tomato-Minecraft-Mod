package net.pekkatrol.hg2t.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pekkatrol.hg2t.HG2Tomato;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HG2Tomato.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, HG2Tomato.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PresentRecipe>> PRESENT_SERIALIZER =
            SERIALIZERS.register("present", PresentRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<PresentRecipe>> PRESENT_TYPE =
            TYPES.register("present", () -> new RecipeType<PresentRecipe>() {
                @Override
                public String toString() {
                    return "present";
                }
            });

    public static final RegistryObject<RecipeSerializer<CombustionGeneratorRecipe>> COMBUSTION_GENERATOR_SERIALIZER =
            SERIALIZERS.register("combustion_generator", CombustionGeneratorRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<CombustionGeneratorRecipe>> COMBUSTION_GENERATOR_TYPE =
            TYPES.register("combustion_generator", () -> new RecipeType<CombustionGeneratorRecipe>() {
                @Override
                public String toString() {
                    return "combustion_generator";
                }
            });

    public static final RegistryObject<RecipeSerializer<CompressorRecipe>> COMPRESSOR_SERIALIZER =
            SERIALIZERS.register("compressor", CompressorRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<CompressorRecipe>> COMPRESSOR_TYPE =
            TYPES.register("compressor", () -> new RecipeType<CompressorRecipe>() {
                @Override
                public String toString() {
                    return "compressor";
                }
            });

    public static final RegistryObject<RecipeSerializer<PulverisorRecipe>> PULVERISOR_SERIALIZER =
            SERIALIZERS.register("pulverisor", PulverisorRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<PulverisorRecipe>> PULVERISOR_TYPE =
            TYPES.register("pulverisor", () -> new RecipeType<PulverisorRecipe>() {
                @Override
                public String toString() {
                    return "pulverisor";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
