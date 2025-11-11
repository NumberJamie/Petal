package com.nrjam.petal.worldgen;

import com.nrjam.petal.Petal;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import java.util.List;

public class PetalPlacedFeature {
    public static final RegistryKey<PlacedFeature> DEAD_ROOTS_PLACED = registerKey("dead_roots_placed");
    public static final RegistryKey<PlacedFeature> MAGMA_BLOOM_PLACED = registerKey("magma_bloom_placed");

    public static void initialize(Registerable<PlacedFeature> ctx) {
        var configuredFeature = ctx.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(ctx, DEAD_ROOTS_PLACED, configuredFeature.getOrThrow(PetalConfiguredFeature.DEAD_ROOTS_KEY), CountPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());
        register(ctx, MAGMA_BLOOM_PLACED, configuredFeature.getOrThrow(PetalConfiguredFeature.MAGMA_BLOOM_KEY), CountPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Petal.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> ctx, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifier) {
        ctx.register(key, new PlacedFeature(config, List.copyOf(modifier)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> ctx, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifier) {
        register(ctx, key, configuration, List.of(modifier));
    }
}
