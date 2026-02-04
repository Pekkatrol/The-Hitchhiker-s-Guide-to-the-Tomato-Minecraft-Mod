package net.pekkatrol.hg2t.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    public static final FoodProperties BANANA = new FoodProperties.Builder().
            nutrition(3)
            .saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 400), 0.30f).build();

    public static final FoodProperties TOMATO = new FoodProperties.Builder().
            nutrition(3)
            .saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400), 0.30f).build();

    public static final FoodProperties ALMOND = new FoodProperties.Builder().
            nutrition(3)
            .saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400), 0.30f).build();

    public static final FoodProperties KETCHOUP = new FoodProperties.Builder().
            nutrition(6)
            .saturationModifier(0.50f)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1600), 1f).build();
}
