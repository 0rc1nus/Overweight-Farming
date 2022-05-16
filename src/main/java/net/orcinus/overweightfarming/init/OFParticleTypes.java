package net.orcinus.overweightfarming.init;

import net.orcinus.overweightfarming.OverweightFarming;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OFParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, OverweightFarming.MODID);

    public static final RegistryObject<SimpleParticleType> DRIPPING_MELON = PARTICLE_TYPES.register("dripping_melon", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FALLING_MELON = PARTICLE_TYPES.register("falling_melon", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> LANDING_MELON = PARTICLE_TYPES.register("landing_melon", () -> new SimpleParticleType(false));

}
