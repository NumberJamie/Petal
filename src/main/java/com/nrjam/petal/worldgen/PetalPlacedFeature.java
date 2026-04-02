package com.nrjam.petal.worldgen;

import com.nrjam.petal.Petal;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.TrapezoidInt;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PetalPlacedFeature {
    public static final ResourceKey<PlacedFeature> DEAD_ROOTS_PLACED = registerKey("dead_roots_placed");
    public static final ResourceKey<PlacedFeature> MAGMA_BLOOM_PLACED = registerKey("magma_bloom_placed");
    public static final ResourceKey<PlacedFeature> WATER_LILY_PAD_PLACED = registerKey("water_lily_pad_placed");

    public static void initialize(BootstrapContext<PlacedFeature> ctx) {
        var configuredFeature = ctx.lookup(Registries.CONFIGURED_FEATURE);

        register(ctx, DEAD_ROOTS_PLACED, configuredFeature.getOrThrow(PetalConfiguredFeature.DEAD_ROOTS_KEY), CountPlacement.of(6), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome(), CountPlacement.of(96), RandomOffsetPlacement.of(TrapezoidInt.of(-7, 7, 0), TrapezoidInt.of(-3, 3, 0)), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE));
        register(ctx, MAGMA_BLOOM_PLACED, configuredFeature.getOrThrow(PetalConfiguredFeature.MAGMA_BLOOM_KEY), CountPlacement.of(32), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE));
        register(ctx, WATER_LILY_PAD_PLACED, configuredFeature.getOrThrow(PetalConfiguredFeature.WATER_LILY_PAD_KEY), CountPlacement.of(2), InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome(), CountPlacement.of(5), RandomOffsetPlacement.of(TrapezoidInt.of(-7, 7, 0), TrapezoidInt.of(-3, 3, 0)), BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockTags.AIR)));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Petal.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> ctx, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifier) {
        ctx.register(key, new PlacedFeature(config, List.copyOf(modifier)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<PlacedFeature> ctx, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifier) {
        register(ctx, key, configuration, List.of(modifier));
    }
}
