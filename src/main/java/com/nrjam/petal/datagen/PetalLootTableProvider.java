package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.block.crop.TurnipsBlock;
import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarrotsBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class PetalLootTableProvider extends FabricBlockLootTableProvider {
    public PetalLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = registries.getOrThrow(RegistryKeys.ENCHANTMENT);

        LootCondition.Builder builder = BlockStatePropertyLootCondition.builder(PetalBlocks.TURNIPS)
                .properties(StatePredicate.Builder.create().exactMatch(TurnipsBlock.AGE, TurnipsBlock.MAX_AGE));

        addDrop(PetalBlocks.MUDDY_FARMLAND, Blocks.MUD);
        addDrop(PetalBlocks.TURNIPS, applyExplosionDecay(PetalBlocks.TURNIPS, LootTable.builder()
                                .pool(LootPool.builder().with(ItemEntry.builder(PetalItems.TURNIP)))
                                .pool(LootPool.builder().conditionally(builder).with(ItemEntry.builder(PetalItems.TURNIP).apply(ApplyBonusLootFunction.binomialWithBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 0.5714286f, 1))))));
    }
}
