package net.orcinus.overweightfarming.client.particles;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.overweightfarming.init.OFParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluids;

@OnlyIn(Dist.CLIENT)
public class MelonFallProvider implements ParticleProvider<SimpleParticleType> {
    protected final SpriteSet sprite;

    public MelonFallProvider(SpriteSet set) {
        this.sprite = set;
    }

    @Override
    public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        DripParticle dripparticle = new DripParticle.FallAndLandParticle(world, x, y, z, Fluids.EMPTY, OFParticleTypes.LANDING_MELON.get());
        dripparticle.gravity = 0.01F;
        dripparticle.setColor(0.67F, 0.04F, 0.05F);
        dripparticle.pickSprite(this.sprite);
        return dripparticle;
    }
}
