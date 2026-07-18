package com.nrjam.petal;

import com.nrjam.petal.particle.EndPetalParticle;
import com.nrjam.petal.particle.PetalParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;

public class PetalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(PetalParticles.END_PETAL, EndPetalParticle.Provider::new);
    }
}
