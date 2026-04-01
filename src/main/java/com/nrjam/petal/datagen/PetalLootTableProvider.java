package com.nrjam.petal.datagen;

import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.block.crop.MagmaBerriesBlock;
import com.nrjam.petal.block.crop.TurnipsBlock;
import com.nrjam.petal.item.PetalItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class PetalLootTableProvider extends FabricBlockLootSubProvider {
    public PetalLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> impl = registries.lookupOrThrow(Registries.ENCHANTMENT);

        LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(PetalBlocks.TURNIPS)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TurnipsBlock.AGE, TurnipsBlock.MAX_AGE));
        LootItemCondition.Builder builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(PetalBlocks.MAGMA_BERRIES)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MagmaBerriesBlock.AGE, MagmaBerriesBlock.MAX_AGE));

        add(PetalBlocks.MAGMA_BLOOM, createSilkTouchOrShearsDispatchTable(PetalBlocks.MAGMA_BLOOM, applyExplosionDecay(
                PetalBlocks.MAGMA_BLOOM, LootItem.lootTableItem(PetalItems.MAGMA_BERRY)
                        .when(LootItemRandomChanceCondition.randomChance(0.125f))
                        .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 1))
        )));
        add(PetalBlocks.DEAD_ROOTS, createShearsOrSilkTouchOnlyDrop(PetalBlocks.DEAD_ROOTS));
        add(PetalBlocks.TURNIP_GREENS, createShearsOrSilkTouchOnlyDrop(PetalBlocks.TURNIP_GREENS));
        add(PetalBlocks.LAVA_ROOT, createSilkTouchOrShearsDispatchTable(PetalBlocks.LAVA_ROOT, applyExplosionDecay(
                PetalBlocks.LAVA_ROOT, LootItem.lootTableItem(PetalItems.LAVA_FRUIT)
                        .when(LootItemRandomChanceCondition.randomChance(0.125f))
                        .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 1))
        )));
        add(PetalBlocks.HUGE_TURNIP, createSilkTouchDispatchTable(PetalBlocks.HUGE_TURNIP, applyExplosionDecay(
                PetalBlocks.HUGE_TURNIP, LootItem.lootTableItem(PetalItems.TURNIP)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(9)))
        )));

        dropOther(PetalBlocks.MUDDY_FARMLAND, Blocks.MUD);
        dropOther(PetalBlocks.NETHER_FARMLAND, Blocks.SOUL_SOIL);
        add(PetalBlocks.TURNIPS, applyExplosionDecay(PetalBlocks.TURNIPS, LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(PetalItems.TURNIP)))
                                .withPool(LootPool.lootPool().when(builder).add(LootItem.lootTableItem(PetalItems.TURNIP).apply(ApplyBonusCount.addBonusBinomialDistributionCount(impl.getOrThrow(Enchantments.FORTUNE), 0.3f, 1))))));
        add(PetalBlocks.MAGMA_BERRIES, applyExplosionDecay(PetalBlocks.MAGMA_BERRIES, LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(PetalItems.MAGMA_BERRY)))
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(PetalBlocks.MAGMA_BLOOM)).when(LootItemRandomChanceCondition.randomChance(0.2F)))
                .withPool(LootPool.lootPool().when(builder2).add(LootItem.lootTableItem(PetalItems.MAGMA_BERRY).apply(ApplyBonusCount.addBonusBinomialDistributionCount(impl.getOrThrow(Enchantments.FORTUNE), 0.3f, 1))))));
    }
}
