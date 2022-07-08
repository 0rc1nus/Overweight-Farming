package net.orcinus.overweightfarming.common.registry;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.BlockLeakParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.client.particle.FluffParticle;
import net.orcinus.overweightfarming.common.util.IBlockLeakParticle;

public class OFParticleTypes {
    public static final DefaultParticleType DRIPPING_MELON = Registry.register(Registry.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID, "dripping_melon"), FabricParticleTypes.simple());
    public static final DefaultParticleType FALLING_MELON = Registry.register(Registry.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID, "falling_melon"), FabricParticleTypes.simple());
    public static final DefaultParticleType LANDING_MELON = Registry.register(Registry.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID, "landing_melon"), FabricParticleTypes.simple());
    public static final DefaultParticleType FLUFF = Registry.register(Registry.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID, "fluff"), FabricParticleTypes.simple());


    public static void init() {
        ParticleFactoryRegistry.getInstance().register(OFParticleTypes.FLUFF, FluffParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(LANDING_MELON, s -> new BlockLeakParticle.LandingHoneyFactory(s) {
            @Override
            public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
                BlockLeakParticle r = (BlockLeakParticle) super.createParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ);
                if (r != null) {
                    r.setColor(0.67F, 0.04F, 0.05F);
                }
                return r;
            }
        });
        ParticleFactoryRegistry.getInstance().register(FALLING_MELON, s -> new BlockLeakParticle.FallingWaterFactory(s) {
            @Override
            public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
                BlockLeakParticle r = (BlockLeakParticle) super.createParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ);
                if (r != null) {
                    r.setColor(0.67F, 0.04F, 0.05F);
                    ((IBlockLeakParticle) r).setNextParticle(LANDING_MELON);
                }

                return r;
            }
        });
        ParticleFactoryRegistry.getInstance().register(DRIPPING_MELON, s -> new BlockLeakParticle.DrippingWaterFactory(s) {
            @Override
            public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
                BlockLeakParticle r = (BlockLeakParticle) super.createParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ);
                if (r != null) {
                    r.setColor(0.62F, 0.0F, 0.1F);
                    ((IBlockLeakParticle) r).setNextParticle(FALLING_MELON);
                }

                return r;
            }
        });
    }
}
