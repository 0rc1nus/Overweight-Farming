package com.binome.overweightfarming.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SeedProjectileEntity extends ThrowableItemProjectile {

    public SeedProjectileEntity(EntityType<? extends ThrowableItemProjectile> type, Level world) {
        super(type, world);
    }

    public SeedProjectileEntity(Level world, LivingEntity entity, ItemStack stack) {
        super(EntityType.SNOWBALL, entity, world);
    }

    public SeedProjectileEntity(Level world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

}
