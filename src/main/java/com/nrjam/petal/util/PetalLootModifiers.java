package com.nrjam.petal.util;

import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class PetalLootModifiers {
    public static void initialize() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if (BuiltInLootTables.VILLAGE_TAIGA_HOUSE.equals(key)) {
                LootPool.Builder builder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.8f))
                        .add(LootItem.lootTableItem(PetalItems.TURNIP))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)).build());
                tableBuilder.pool(builder.build());
            }
        });
    }
}
