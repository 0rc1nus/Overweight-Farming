package com.binome.overweightfarming.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SeedProjectileEntity extends ThrownItemEntity {

    public SeedProjectileEntity(EntityType<? extends ThrownItemEntity> type, World world) {
        super(type, world);
    }

    public SeedProjectileEntity(World world, LivingEntity entity, ItemStack stack) {
        super(EntityType.SNOWBALL, entity, world);
    }

    public SeedProjectileEntity(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

}
