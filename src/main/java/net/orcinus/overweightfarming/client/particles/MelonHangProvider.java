package net.orcinus.overweightfarming.client.particles;

import net.orcinus.overweightfarming.init.OFParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MelonHangProvider implements ParticleProvider<SimpleParticleType> {
    protected final SpriteSet sprite;

    public MelonHangProvider(SpriteSet set) {
        this.sprite = set;
    }

    public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        DripParticle.DripHangParticle dripparticle$driphangparticle = new DripParticle.DripHangParticle(world, x, y, z, Fluids.EMPTY, OFParticleTypes.FALLING_MELON.get());
        dripparticle$driphangparticle.gravity *= 0.01F;
        dripparticle$driphangparticle.lifetime = 100;
        dripparticle$driphangparticle.setColor(0.82F, 0.05F, 0.06F);
        dripparticle$driphangparticle.pickSprite(this.sprite);
        return dripparticle$driphangparticle;
    }
}
