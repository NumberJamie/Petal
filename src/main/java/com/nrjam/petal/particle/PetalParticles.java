package com.nrjam.petal.particle;

import com.nrjam.petal.Petal;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class PetalParticles {
    public static final SimpleParticleType END_PETAL = Registry.register(
            BuiltInRegistries.PARTICLE_TYPE,
            Identifier.fromNamespaceAndPath(Petal.MOD_ID, "end_petal"),
            FabricParticleTypes.simple()
    );

    public static void initialize() {
    }
}
