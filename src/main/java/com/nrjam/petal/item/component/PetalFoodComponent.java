package com.nrjam.petal.item.component;

import net.minecraft.world.food.FoodProperties;

public class PetalFoodComponent {
    public static final FoodProperties TURNIP = new FoodProperties.Builder().nutrition(1).saturationModifier(1f).build();
    public static final FoodProperties ROASTED_TURNIP = new FoodProperties.Builder().nutrition(4).saturationModifier(.5f).build();
    public static final FoodProperties GLAZED_TURNIP = new FoodProperties.Builder().nutrition(6).saturationModifier(.5f).build();
    public static final FoodProperties TURNIP_PIE = new FoodProperties.Builder().nutrition(6).saturationModifier(.75f).build();

    public static final FoodProperties LAVA_FRUIT = new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build();
    public static final FoodProperties MAGMA_BERRY = new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build();

    public static final FoodProperties FUGU = new FoodProperties.Builder().nutrition(6).saturationModifier(1f).build();
    public static final FoodProperties MOUSSE = new FoodProperties.Builder().nutrition(6).saturationModifier(.75f).build();
}
