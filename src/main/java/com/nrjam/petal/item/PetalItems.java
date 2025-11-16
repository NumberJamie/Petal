package com.nrjam.petal.item;

import com.nrjam.petal.Petal;
import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.item.component.PetalConsumableComponent;
import com.nrjam.petal.item.component.PetalFoodComponent;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class PetalItems {
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(itemGroup -> {
            itemGroup.add(TURNIP);
            itemGroup.add(ROASTED_TURNIP);
            itemGroup.add(GLAZED_TURNIP);
            itemGroup.add(TURNIP_PIE);
            itemGroup.add(LAVA_FRUIT);
            itemGroup.add(BAKED_LAVA_FRUIT);
            itemGroup.add(MAGMA_BERRY);
            itemGroup.add(FUGU);
            itemGroup.add(MOUSSE);
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

    public static Item register(String name, Function<Item.Settings, Item> itemFactory) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Petal.MOD_ID, name));
        Item item = itemFactory.apply(new Item.Settings().registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }
}
