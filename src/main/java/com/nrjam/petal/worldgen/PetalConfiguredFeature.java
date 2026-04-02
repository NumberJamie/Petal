package com.nrjam.petal.worldgen;

import com.nrjam.petal.Petal;
import com.nrjam.petal.block.PetalBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class PetalConfiguredFeature {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_ROOTS_KEY = registerKey("dead_roots");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGMA_BLOOM_KEY = registerKey("magma_bloom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_LILY_PAD_KEY = registerKey("water_lily_pad");

    public static void initialize(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {
        register(ctx, DEAD_ROOTS_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(
                        new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(PetalBlocks.DEAD_ROOTS.defaultBlockState(), 75)
                                .add(PetalBlocks.LAVA_ROOT.defaultBlockState(), 25)
                                .build()
                        )
                )
        );
        register(ctx, MAGMA_BLOOM_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(PetalBlocks.MAGMA_BLOOM))
        );
        register(ctx, WATER_LILY_PAD_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(
                        new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(PetalBlocks.WATER_LILY_PAD.defaultBlockState(), 85)
                                .add(PetalBlocks.WATER_LILY.defaultBlockState(), 15)
                                .build()
                        )
                )
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Petal.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> ctx, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        ctx.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
