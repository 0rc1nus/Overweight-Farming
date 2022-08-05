package net.orcinus.overweightfarming.entities;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.orcinus.overweightfarming.init.OFEntityTypes;

import java.util.function.Predicate;

public class OverweightAppleFallingBlockEntity extends FallingBlockEntity {
    private boolean isGolden;

    public OverweightAppleFallingBlockEntity(EntityType<? extends OverweightAppleFallingBlockEntity> type, Level world) {
        super(type, world);
    }

    public OverweightAppleFallingBlockEntity(boolean isGolden, Level world, double x, double y, double z, BlockState blockState) {
        this(OFEntityTypes.OVERWEIGHT_APPLE_FALLING_BLOCK.get(), world);
        this.isGolden = isGolden;
        this.blockState = blockState;
        this.blocksBuilding = true;
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.cancelDrop = false;
        this.dropItem = true;
        this.setStartPos(this.blockPosition());
    }

    @Override
    public boolean causeFallDamage(float p_149643_, float p_149644_, DamageSource p_149645_) {
        int i = Mth.ceil(p_149643_ - 1.0F);
        Predicate<Entity> predicate = EntitySelector.NO_SPECTATORS;
        DamageSource damagesource = DamageSource.FALLING_BLOCK;
        int value = this.isGolden ? 2 : 1;
        float f = (float)Math.min(Mth.floor((float)i * 1 * value), 2 * value);
        this.level.getEntities(this, this.getBoundingBox(), predicate).forEach((entity) -> {
            if (entity instanceof LivingEntity) {
                this.dropItem = false;
                entity.hurt(damagesource, f);
            }
        });
        return false;
    }

}