package com.nrjam.petal.block;

import com.nrjam.petal.Petal;
import com.nrjam.petal.block.crop.*;
import com.nrjam.petal.block.custom.*;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;


public class PetalBlocks {
    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register((itemGroup) -> {
            itemGroup.accept(HUGE_TURNIP.asItem());
            itemGroup.accept(MUDDY_FARMLAND.asItem());
            itemGroup.accept(NETHER_FARMLAND.asItem());
            itemGroup.accept(DEAD_ROOTS.asItem());
            itemGroup.accept(LAVA_ROOT.asItem());
            itemGroup.accept(MAGMA_BLOOM.asItem());
        });

        TillableBlockRegistry.register(Blocks.MUD, ctx -> true, PetalBlocks.MUDDY_FARMLAND.defaultBlockState());
        TillableBlockRegistry.register(Blocks.SOUL_SOIL, ctx -> true, PetalBlocks.NETHER_FARMLAND.defaultBlockState());
    }

    public static final Block MUDDY_FARMLAND = register("muddy_farmland", MuddyFarmland::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FARMLAND), true);
    public static final Block NETHER_FARMLAND = register("nether_farmland", NetherFarmland::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FARMLAND), true);

    public static final Block DEAD_ROOTS = register("dead_roots", DeadRoots::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS), true);
    public static final Block LAVA_ROOT = register("lava_root", LavaRoot::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS), true);
    public static final Block MAGMA_BLOOM = register("magma_bloom", MagmaBloom::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).lightLevel(state -> 3), true);

    public static final Block MAGMA_BERRIES = register("magma_berries", MagmaBerriesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES), false);

    public static final Block TURNIPS = register("turnips", TurnipsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES), false);

    public static final Block HUGE_TURNIP = register("huge_turnip", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MELON), true);

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean registerItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));
        if (registerItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Petal.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Petal.MOD_ID, name));
    }
}
