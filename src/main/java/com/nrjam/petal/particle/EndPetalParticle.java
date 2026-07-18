package com.nrjam.petal.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;

public class EndPetalParticle extends SingleQuadParticle {
    private static final int FADE_OUT_TICKS = 20;

    private final float swayPhaseX;
    private final float swayPhaseZ;
    private final float swaySpeedX;
    private final float swaySpeedZ;
    private final float swayAmount;

    protected EndPetalParticle(ClientLevel world, double x, double y, double z, TextureAtlasSprite sprite) {
        super(world, x, y, z, sprite);
        this.lifetime = 120 + this.random.nextInt(60);
        this.gravity = -0.005F;
        this.quadSize = 0.03F + this.random.nextFloat() * 0.03F;
        this.yd = 0.005 + this.random.nextDouble() * 0.01;
        this.hasPhysics = false;
        this.swayPhaseX = this.random.nextFloat() * Mth.TWO_PI;
        this.swayPhaseZ = this.random.nextFloat() * Mth.TWO_PI;
        this.swaySpeedX = 0.03F + this.random.nextFloat() * 0.03F;
        this.swaySpeedZ = 0.03F + this.random.nextFloat() * 0.03F;
        this.swayAmount = 0.002F + this.random.nextFloat() * 0.003F;
    }

    @Override
    public void tick() {
        super.tick();
        this.xd = Mth.cos(this.age * this.swaySpeedX + this.swayPhaseX) * this.swayAmount;
        this.zd = Mth.sin(this.age * this.swaySpeedZ + this.swayPhaseZ) * this.swayAmount;
        int remaining = this.lifetime - this.age;
        if (remaining < FADE_OUT_TICKS) {
            setAlpha(remaining / (float) FADE_OUT_TICKS);
        }
    }

    @Override
    protected int getLightCoords(float partialTick) {
        float remaining = this.lifetime - this.age - partialTick;
        return (int) (255.0F * Mth.clamp(remaining / FADE_OUT_TICKS, 0.0F, 1.0F));
    }

    @Override
    protected @NonNull Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public @NonNull Particle createParticle(@NonNull SimpleParticleType type, @NonNull ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, @NonNull RandomSource random) {
            return new EndPetalParticle(world, x, y, z, this.sprites.get(random));
        }
    }
}
