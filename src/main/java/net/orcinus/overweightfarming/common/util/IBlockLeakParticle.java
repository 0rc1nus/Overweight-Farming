package net.orcinus.overweightfarming.common.util;


import net.minecraft.particle.ParticleEffect;

public interface IBlockLeakParticle {
    void setNextParticle(ParticleEffect effect);
}