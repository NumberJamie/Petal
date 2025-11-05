package com.nrjam.petal.util;

import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class PetalLootModifiers {
    public static void initialize() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if (LootTables.VILLAGE_TAIGA_HOUSE_CHEST.equals(key)) {
                LootPool.Builder builder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.8f))
                        .with(ItemEntry.builder(PetalItems.TURNIP))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)).build());
                tableBuilder.pool(builder.build());
            }
        });
    }
}
