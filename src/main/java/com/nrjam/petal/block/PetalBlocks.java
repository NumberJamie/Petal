package com.nrjam.petal.block;

import com.nrjam.petal.Petal;
import com.nrjam.petal.block.crop.*;
import com.nrjam.petal.block.custom.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;


public class PetalBlocks {
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register((itemGroup) -> {
            itemGroup.add(MUDDY_FARMLAND.asItem());
            itemGroup.add(NETHER_FARMLAND.asItem());
            itemGroup.add(DEAD_ROOTS.asItem());
            itemGroup.add(LAVA_ROOT.asItem());
            itemGroup.add(MAGMA_BLOOM.asItem());
        });

        TillableBlockRegistry.register(Blocks.MUD, ctx -> true, PetalBlocks.MUDDY_FARMLAND.getDefaultState());
        TillableBlockRegistry.register(Blocks.SOUL_SOIL, ctx -> true, PetalBlocks.NETHER_FARMLAND.getDefaultState());
    }

    public static final Block MUDDY_FARMLAND = register("muddy_farmland", MuddyFarmland::new, AbstractBlock.Settings.copy(Blocks.FARMLAND), true);
    public static final Block NETHER_FARMLAND = register("nether_farmland", NetherFarmland::new, AbstractBlock.Settings.copy(Blocks.FARMLAND), true);

    public static final Block DEAD_ROOTS = register("dead_roots", DeadRoots::new, AbstractBlock.Settings.copy(Blocks.NETHER_SPROUTS), true);
    public static final Block LAVA_ROOT = register("lava_root", LavaRoot::new, AbstractBlock.Settings.copy(Blocks.NETHER_SPROUTS), true);
    public static final Block MAGMA_BLOOM = register("magma_bloom", MagmaBloom::new, AbstractBlock.Settings.copy(Blocks.NETHER_SPROUTS).luminance(state -> 3), true);

    public static final Block TURNIPS = register("turnips", TurnipsBlock::new, AbstractBlock.Settings.copy(Blocks.POTATOES), false);

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean registerItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        if (registerItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }
        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Petal.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Petal.MOD_ID, name));
    }
}

