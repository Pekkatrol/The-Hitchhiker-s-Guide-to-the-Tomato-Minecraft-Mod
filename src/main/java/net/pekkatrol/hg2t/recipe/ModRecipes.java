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

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
