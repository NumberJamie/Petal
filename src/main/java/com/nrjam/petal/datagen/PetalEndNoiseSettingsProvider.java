package com.nrjam.petal.datagen;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.nrjam.petal.block.PetalBlocks;
import com.nrjam.petal.worldgen.PetalBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PetalEndNoiseSettingsProvider implements DataProvider {
    private final FabricPackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registriesFuture;

    public PetalEndNoiseSettingsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        this.output = output;
        this.registriesFuture = registriesFuture;
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput cachedOutput) {
        return registriesFuture.thenCompose(registries -> {
            HolderGetter<DensityFunction> densityFunctions = registries.lookupOrThrow(Registries.DENSITY_FUNCTION);

            SurfaceRules.RuleSource endFieldsRule = SurfaceRules.ifTrue(
                    SurfaceRules.isBiome(PetalBiomes.END_FIELDS),
                    SurfaceRules.ifTrue(
                            SurfaceRules.noiseCondition(Noises.SURFACE, -0.4, 0.4),
                            SurfaceRules.ifTrue(
                                    SurfaceRules.VERY_DEEP_UNDER_FLOOR,
                                    SurfaceRules.state(PetalBlocks.END_SOIL.defaultBlockState())
                            )
                    )
            );
            SurfaceRules.RuleSource vanillaEndRule = SurfaceRules.state(Blocks.END_STONE.defaultBlockState());
            SurfaceRules.RuleSource combinedRule = SurfaceRules.sequence(endFieldsRule, vanillaEndRule);

            NoiseGeneratorSettings settings = new NoiseGeneratorSettings(
                    NoiseSettings.END_NOISE_SETTINGS,
                    Blocks.END_STONE.defaultBlockState(),
                    Blocks.AIR.defaultBlockState(),
                    NoiseRouterData.end(densityFunctions),
                    combinedRule,
                    List.of(),
                    0,
                    true,
                    false,
                    false,
                    true
            );

            JsonElement json = NoiseGeneratorSettings.DIRECT_CODEC.encodeStart(
                    registries.createSerializationContext(JsonOps.INSTANCE),
                    settings
            ).getOrThrow();

            Path path = output.getOutputFolder()
                    .resolve("data/minecraft/worldgen/noise_settings/end.json");

            return DataProvider.saveStable(cachedOutput, json, path);
        });
    }

    @Override
    public @NonNull String getName() {
        return "Petal/End Noise Settings Override";
    }
}
