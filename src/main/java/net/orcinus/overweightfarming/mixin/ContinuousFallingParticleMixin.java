package net.orcinus.overweightfarming.mixin;

import net.minecraft.client.particle.BlockLeakParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.orcinus.overweightfarming.common.util.IBlockLeakParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Sandwichable https://github.com/FoundationGames/Sandwichable/blob/1.18.2/src/main/java/io/github/foundationgames/sandwichable/mixin/ContinuousFallingParticleMixin.java
@Mixin(BlockLeakParticle.ContinuousFalling.class)
public abstract class ContinuousFallingParticleMixin extends Particle implements IBlockLeakParticle {
    @Unique
    private ParticleEffect OFNextParticle;

    protected ContinuousFallingParticleMixin(ClientWorld clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    @Inject(method = "updateVelocity()V", at = @At("HEAD"), cancellable = true)
    private void OF$spawnCustomNextParticle(CallbackInfo ci) {
        if (OFNextParticle != null && this.onGround) {
            this.markDead();
            this.world.addParticle(this.OFNextParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
            ci.cancel();
        }
    }

    @Override
    public void setNextParticle(ParticleEffect effect) {
        OFNextParticle = effect;
    }


}