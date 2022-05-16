package net.orcinus.overweightfarming.client.particles;

import net.orcinus.overweightfarming.init.OFParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluids;

public class MelonFallProvider implements ParticleProvider<SimpleParticleType> {
    protected final SpriteSet sprite;

    public MelonFallProvider(SpriteSet set) {
        this.sprite = set;
    }

    public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double p_106326_, double p_106327_, double p_106328_) {
        DripParticle dripparticle = new DripParticle.FallAndLandParticle(world, x, y, z, Fluids.EMPTY, OFParticleTypes.LANDING_MELON.get());
        dripparticle.gravity = 0.01F;
        dripparticle.setColor(0.67F, 0.04F, 0.05F);
        dripparticle.pickSprite(this.sprite);
        return dripparticle;
    }
}
