package net.orcinus.overweightfarming.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.orcinus.overweightfarming.registry.OFEntityTypes;

import java.util.function.Predicate;

public class OverweightAppleFallingBlockEntity extends FallingBlockEntity {
    private boolean isGolden;
    public OverweightAppleFallingBlockEntity(EntityType<? extends OverweightAppleFallingBlockEntity> entityType, World world) {
        super(entityType, world);
        this.fallHurtMax = 40;
    }

    public OverweightAppleFallingBlockEntity(boolean isGolden, World world, double x, double y, double z, BlockState blockState) {
        this(OFEntityTypes.OVERWEIGHT_APPLE_FALLING_BLOCK, world);
        this.isGolden = isGolden;
        this.block = blockState;
        this.intersectionChecked = true;
        this.setPosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.setFallingBlockPos(this.getBlockPos());
        this.destroyedOnLanding = false;
        this.dropItem = true;
    }


    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        int i = MathHelper.ceil(fallDistance - 1.0F);
        Predicate<Entity> predicate = EntityPredicates.EXCEPT_SPECTATOR;
        DamageSource damagesource = DamageSource.FALLING_BLOCK;
        int value = this.isGolden ? 2 : 1;
        float f = (float)Math.min(MathHelper.floor((float)i * 1 * value), 2 * value);
        this.world.getOtherEntities(this, this.getBoundingBox(), predicate).forEach((entity) -> {
            if (entity instanceof LivingEntity) {
                this.dropItem = false;
                entity.damage(damagesource, f);
            }
        });
        return false;
    }
}
