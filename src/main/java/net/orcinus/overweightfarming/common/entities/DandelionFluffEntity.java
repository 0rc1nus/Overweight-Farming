package net.orcinus.overweightfarming.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

//TODO all this
public class DandelionFluffEntity extends PersistentProjectileEntity {
    private int life = 0;

    public DandelionFluffEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);

    }

    protected DandelionFluffEntity(EntityType<? extends PersistentProjectileEntity> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    protected DandelionFluffEntity(EntityType<? extends PersistentProjectileEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

    protected void age() {
        ++this.life;
        if (this.life >= 10 * 20) {
            this.discard();
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putShort("life", (short)this.life);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.life = nbt.getShort("life");
    }

    @Override
    public void tick() {
        age();
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }
}
