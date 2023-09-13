package net.orcinus.overweightfarming.common.registry;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.client.particle.FluffParticle;
import net.orcinus.overweightfarming.common.util.IBlockLeakParticle;

public class OFParticleTypes {
    public static final DefaultParticleType DRIPPING_MELON = Registry.register(Registries.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID, "dripping_melon"), FabricParticleTypes.simple());
    public static final DefaultParticleType FALLING_MELON = Registry.register(Registries.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID, "falling_melon"), FabricParticleTypes.simple());
    public static final DefaultParticleType LANDING_MELON = Registry.register(Registries.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID, "landing_melon"), FabricParticleTypes.simple());
    public static final DefaultParticleType FLUFF = Registry.register(Registries.PARTICLE_TYPE, new Identifier(OverweightFarming.MODID, "fluff"), FabricParticleTypes.simple());


    public static void init() {
        ParticleFactoryRegistry.getInstance().register(OFParticleTypes.FLUFF, FluffParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(FALLING_MELON, withSprite(OFParticleTypes::createFalling));
        ParticleFactoryRegistry.getInstance().register(DRIPPING_MELON, withSprite(OFParticleTypes::createDripping));
        ParticleFactoryRegistry.getInstance().register(LANDING_MELON, withSprite(OFParticleTypes::createLanding));
    }

    public static SpriteBillboardParticle createLanding(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        var particle = BlockLeakParticle.createFallingWater(type, world, x, y, z, velocityX, velocityY, velocityZ);
        if (particle instanceof BlockLeakParticle leak) {
            leak.setColor(0.67F, 0.04F, 0.05F);
        }
        return particle;
    }

    public static SpriteBillboardParticle createFalling(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        var particle = BlockLeakParticle.createFallingWater(type, world, x, y, z, velocityX, velocityY, velocityZ);
        if (particle instanceof BlockLeakParticle leak) {
            leak.setColor(0.67F, 0.04F, 0.05F);
            ((IBlockLeakParticle)leak).setNextParticle(LANDING_MELON);
        }
        return particle;
    }

    public static SpriteBillboardParticle createDripping(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        var particle = BlockLeakParticle.createDrippingWater(type, world, x, y, z, velocityX, velocityY, velocityZ);
        if (particle instanceof BlockLeakParticle leak) {
            leak.setColor(0.62F, 0.0F, 0.1F);
            ((IBlockLeakParticle)leak).setNextParticle(FALLING_MELON);
        }
        return particle;
    }

    public static <E extends ParticleEffect> ParticleFactoryRegistry.PendingParticleFactory<E> withSprite(ParticleFactory.BlockLeakParticleFactory<E> factory) {
        return sprites -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> {
            var particle = factory.createParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ);
            if (particle != null) {
                particle.setSprite(sprites);
            }
            return particle;
        };
    }
}
