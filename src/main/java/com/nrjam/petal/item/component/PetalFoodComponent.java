package com.nrjam.petal.item.component;

import net.minecraft.component.type.FoodComponent;

public class PetalFoodComponent {
    public static final FoodComponent TURNIP = new FoodComponent.Builder().nutrition(2).saturationModifier(1).build();
    public static final FoodComponent ROASTED_TURNIP = new FoodComponent.Builder().nutrition(3).saturationModifier(1).build();
    public static final FoodComponent GLAZED_TURNIP = new FoodComponent.Builder().nutrition(8).saturationModifier(.5f).build();
    public static final FoodComponent TURNIP_PIE = new FoodComponent.Builder().nutrition(5).saturationModifier(1).build();

    public static final FoodComponent FUGU = new FoodComponent.Builder().nutrition(6).saturationModifier(1).build();
    public static final FoodComponent MOUSSE = new FoodComponent.Builder().nutrition(4).saturationModifier(1.5f).build();
}
