package com.nrjam.petal.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class PetalWorldGeneration {
    public static void initialize() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.NETHER_WASTES), GenerationStep.Feature.VEGETAL_DECORATION, PetalPlacedFeature.DEAD_ROOTS_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.VEGETAL_DECORATION, PetalPlacedFeature.MAGMA_BLOOM_PLACED);
    }
}
