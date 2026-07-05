package com.nrjam.petal.block;

import com.nrjam.petal.Petal;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class PetalBlockIds {
    public static final ResourceKey<Block> MUDDY_FARMLAND = keyOf("muddy_farmland");
    public static final ResourceKey<Block> NETHER_FARMLAND = keyOf("nether_farmland");

    public static final ResourceKey<Block> DEAD_ROOTS = keyOf("dead_roots");
    public static final ResourceKey<Block> LAVA_ROOT = keyOf("lava_root");
    public static final ResourceKey<Block> MAGMA_BLOOM = keyOf("magma_bloom");

    public static final ResourceKey<Block> MAGMA_BERRIES = keyOf("magma_berries");

    public static final ResourceKey<Block> TURNIPS = keyOf("turnips");

    public static final ResourceKey<Block> HUGE_TURNIP = keyOf("huge_turnip");
    public static final ResourceKey<Block> TURNIP_GREENS = keyOf("turnip_greens");

    public static final ResourceKey<Block> WATER_LILY_PAD = keyOf("water_lily_pad");
    public static final ResourceKey<Block> WATER_LILY = keyOf("water_lily");

    public static final ResourceKey<Block> END_SOIL = keyOf("end_soil");

    public static final ResourceKey<Block> ENDER_ROOT = keyOf("ender_root");
    public static final ResourceKey<Block> BLOSSOMING_ROOT = keyOf("blossoming_root");

    private static ResourceKey<Block> keyOf(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Petal.MOD_ID, name));
    }
}
