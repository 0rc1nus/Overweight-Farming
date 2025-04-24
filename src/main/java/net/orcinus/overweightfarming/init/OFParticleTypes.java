package net.orcinus.overweightfarming.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.orcinus.overweightfarming.OverweightFarming;

public class OFParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, OverweightFarming.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_MELON = PARTICLE_TYPES.register("dripping_melon", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_MELON = PARTICLE_TYPES.register("falling_melon", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_MELON = PARTICLE_TYPES.register("landing_melon", () -> new SimpleParticleType(false));

}
