package com.nrjam.petal.worldgen;

import com.nrjam.petal.Petal;
import com.nrjam.petal.block.PetalBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.List;

public class PetalConfiguredFeature {
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEAD_ROOTS_KEY = registerKey("dead_roots");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MAGMA_BLOOM_KEY = registerKey("magma_bloom");

    public static void initialize(Registerable<ConfiguredFeature<?, ?>> ctx) {
        register(ctx, DEAD_ROOTS_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(
                                new WeightedBlockStateProvider(Pool.<BlockState>builder()
                                        .add(PetalBlocks.DEAD_ROOTS.getDefaultState(), 75)
                                        .add(PetalBlocks.LAVA_ROOT.getDefaultState(), 25)
                                )
                        ),
                        List.of(Blocks.NETHERRACK)
                )
        );
        register(ctx, MAGMA_BLOOM_KEY, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(PetalBlocks.MAGMA_BLOOM)),
                List.of(Blocks.MAGMA_BLOCK)
        ));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Petal.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> ctx, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        ctx.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
