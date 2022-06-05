package com.binome.overweightfarming.registry;

import com.binome.overweightfarming.OverweightFarming;
import com.binome.overweightfarming.util.BlockLeakParticleDuck;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.BlockLeakParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.particle.WaterSplashParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class OFParticleTypes {
    public static final DefaultParticleType DRIPPING_MELON = Registry.register(Registry.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID, "dripping_melon"), FabricParticleTypes.simple());
    public static final DefaultParticleType FALLING_MELON = Registry.register(Registry.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID,"falling_melon"), FabricParticleTypes.simple());
    public static final DefaultParticleType LANDING_MELON = Registry.register(Registry.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID,"landing_melon"), FabricParticleTypes.simple());



    public static void init() {


        ParticleFactoryRegistry.getInstance().register(FALLING_MELON, s -> new BlockLeakParticle.DrippingObsidianTearFactory(s) {
            @Override
            public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
                BlockLeakParticle r = (BlockLeakParticle)super.createParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ);
                r.setColor(0.67F, 0.04F, 0.05F);
                ((BlockLeakParticleDuck)r).setNextParticle(LANDING_MELON);
                return r;
            }
        });
        ParticleFactoryRegistry.getInstance().register(DRIPPING_MELON, s -> new BlockLeakParticle.DrippingObsidianTearFactory(s) {
            @Override
            public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
                BlockLeakParticle r = (BlockLeakParticle)super.createParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ);
                r.setColor(1,1,1);
                ((BlockLeakParticleDuck)r).setNextParticle(FALLING_MELON);
                return r;
            }
        });
        ParticleFactoryRegistry.getInstance().register(LANDING_MELON, WaterSplashParticle.SplashFactory::new);
    }
}
