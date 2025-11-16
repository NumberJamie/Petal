package com.nrjam.petal.item.component;

import net.minecraft.component.type.FoodComponent;

public class PetalFoodComponent {
    public static final FoodComponent TURNIP = new FoodComponent.Builder().nutrition(1).saturationModifier(1f).build();
    public static final FoodComponent ROASTED_TURNIP = new FoodComponent.Builder().nutrition(4).saturationModifier(.5f).build();
    public static final FoodComponent GLAZED_TURNIP = new FoodComponent.Builder().nutrition(6).saturationModifier(.5f).build();
    public static final FoodComponent TURNIP_PIE = new FoodComponent.Builder().nutrition(6).saturationModifier(.75f).build();

    public static final FoodComponent LAVA_FRUIT = new FoodComponent.Builder().nutrition(2).saturationModifier(.5f).build();
    public static final FoodComponent MAGMA_BERRY = new FoodComponent.Builder().nutrition(2).saturationModifier(.5f).build();

    public static final FoodComponent FUGU = new FoodComponent.Builder().nutrition(6).saturationModifier(1f).build();
    public static final FoodComponent MOUSSE = new FoodComponent.Builder().nutrition(6).saturationModifier(.75f).build();
}
