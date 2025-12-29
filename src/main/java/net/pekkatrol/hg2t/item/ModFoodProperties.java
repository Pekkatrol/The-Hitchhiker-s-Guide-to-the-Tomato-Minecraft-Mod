package net.pekkatrol.hg2t.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    public static final FoodProperties BANANA = new FoodProperties.Builder().
            nutrition(3)
            .saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 400), 0.30f).build();
}
