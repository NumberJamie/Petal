package com.nrjam.petal.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class PetalWorldGeneration {
    public static void initialize() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.NETHER_WASTES), GenerationStep.Decoration.VEGETAL_DECORATION, PetalPlacedFeature.DEAD_ROOTS_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, PetalPlacedFeature.MAGMA_BLOOM_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP, Biomes.MANGROVE_SWAMP), GenerationStep.Decoration.VEGETAL_DECORATION, PetalPlacedFeature.WATER_LILY_PAD_PLACED);

        TheEndBiomes.addHighlandsBiome(PetalBiomes.END_FIELDS, 5.0);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(PetalBiomes.END_FIELDS), GenerationStep.Decoration.VEGETAL_DECORATION, PetalPlacedFeature.ENDER_ROOT_PLACED);
    }
}
