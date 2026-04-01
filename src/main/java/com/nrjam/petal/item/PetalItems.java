package com.nrjam.petal.item;

import com.nrjam.petal.Petal;
import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.item.component.PetalConsumableComponent;
import com.nrjam.petal.item.component.PetalFoodComponent;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class PetalItems {
    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(itemGroup -> {
            itemGroup.accept(TURNIP);
            itemGroup.accept(ROASTED_TURNIP);
            itemGroup.accept(GLAZED_TURNIP);
            itemGroup.accept(TURNIP_PIE);
            itemGroup.accept(LAVA_FRUIT);
            itemGroup.accept(BAKED_LAVA_FRUIT);
            itemGroup.accept(MAGMA_BERRY);
            itemGroup.accept(FUGU);
            itemGroup.accept(MOUSSE);
        });
    }

    public static final Item TURNIP = register("turnip", settings -> new BlockItem(PetalBlocks.TURNIPS, settings.food(PetalFoodComponent.TURNIP)));
    public static final Item ROASTED_TURNIP = register("roasted_turnip", settings -> new Item(settings.food(PetalFoodComponent.ROASTED_TURNIP)));
    public static final Item GLAZED_TURNIP = register("glazed_turnip", settings -> new Item(settings.food(PetalFoodComponent.GLAZED_TURNIP)));
    public static final Item TURNIP_PIE = register("turnip_pie", settings -> new Item(settings.food(PetalFoodComponent.TURNIP_PIE)));

    public static final Item LAVA_FRUIT = register("lava_fruit", settings -> new Item(settings.food(PetalFoodComponent.LAVA_FRUIT, PetalConsumableComponent.LAVA_FRUIT)));
    public static final Item BAKED_LAVA_FRUIT = register("baked_lava_fruit", settings -> new Item(settings.food(PetalFoodComponent.LAVA_FRUIT, PetalConsumableComponent.BAKED_LAVA_FRUIT)));
    public static final Item MAGMA_BERRY = register("magma_berry", settings -> new BlockItem(PetalBlocks.MAGMA_BERRIES, settings.food(PetalFoodComponent.MAGMA_BERRY, PetalConsumableComponent.MAGMA_BERRY)));

    public static final Item FUGU = register("fugu", settings -> new Item(settings.food(PetalFoodComponent.FUGU, PetalConsumableComponent.FUGU)));
    public static final Item MOUSSE = register("mousse", settings -> new Item(settings.food(PetalFoodComponent.MOUSSE)));

    public static Item register(String name, Function<Item.Properties, Item> itemFactory) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Petal.MOD_ID, name));
        Item item = itemFactory.apply(new Item.Properties().setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }
}
