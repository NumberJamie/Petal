package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.block.crop.MagmaBerriesBlock;
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
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
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
        LootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(PetalBlocks.MAGMA_BERRIES)
                .properties(StatePredicate.Builder.create().exactMatch(MagmaBerriesBlock.AGE, MagmaBerriesBlock.MAX_AGE));

        addDrop(PetalBlocks.MAGMA_BLOOM, dropsWithSilkTouchOrShears(PetalBlocks.MAGMA_BLOOM, applyExplosionDecay(
                PetalBlocks.MAGMA_BLOOM, ItemEntry.builder(PetalItems.MAGMA_BERRY)
                        .conditionally(RandomChanceLootCondition.builder(0.125f))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 1))
        )));
        addDrop(PetalBlocks.DEAD_ROOTS, dropsWithSilkTouchOrShears(PetalBlocks.DEAD_ROOTS));
        addDrop(PetalBlocks.LAVA_ROOT, dropsWithSilkTouchOrShears(PetalBlocks.LAVA_ROOT, applyExplosionDecay(
                PetalBlocks.LAVA_ROOT, ItemEntry.builder(PetalItems.LAVA_FRUIT)
                        .conditionally(RandomChanceLootCondition.builder(0.125f))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 1))
        )));
        addDrop(PetalBlocks.HUGE_TURNIP, dropsWithSilkTouch(PetalBlocks.HUGE_TURNIP, applyExplosionDecay(
                PetalBlocks.HUGE_TURNIP, ItemEntry.builder(PetalItems.TURNIP)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F)))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMax(9)))
        )));

        addDrop(PetalBlocks.MUDDY_FARMLAND, Blocks.MUD);
        addDrop(PetalBlocks.NETHER_FARMLAND, Blocks.SOUL_SOIL);
        addDrop(PetalBlocks.TURNIPS, applyExplosionDecay(PetalBlocks.TURNIPS, LootTable.builder()
                                .pool(LootPool.builder().with(ItemEntry.builder(PetalItems.TURNIP)))
                                .pool(LootPool.builder().conditionally(builder).with(ItemEntry.builder(PetalItems.TURNIP).apply(ApplyBonusLootFunction.binomialWithBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 0.3f, 1))))));
        addDrop(PetalBlocks.MAGMA_BERRIES, applyExplosionDecay(PetalBlocks.MAGMA_BERRIES, LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(PetalItems.MAGMA_BERRY)))
                .pool(LootPool.builder().conditionally(builder2).with(ItemEntry.builder(PetalItems.MAGMA_BERRY).apply(ApplyBonusLootFunction.binomialWithBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 0.3f, 1))))));
    }
}
