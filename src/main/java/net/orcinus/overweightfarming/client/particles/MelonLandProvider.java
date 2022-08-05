package net.orcinus.overweightfarming.client.particles;

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
public class MelonLandProvider implements ParticleProvider<SimpleParticleType> {
    protected final SpriteSet sprite;

    public MelonLandProvider(SpriteSet set) {
        this.sprite = set;
    }

    @Override
    public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        DripParticle dripparticle = new DripParticle.DripLandParticle(world, x, y, z, Fluids.EMPTY);
        dripparticle.lifetime = (int)(128.0D / (Math.random() * 0.8D + 0.2D));
        dripparticle.setColor(0.62F, 0.0F, 0.1F);
        dripparticle.pickSprite(this.sprite);
        return dripparticle;
    }
}
