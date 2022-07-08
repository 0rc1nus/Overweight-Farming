package net.orcinus.overweightfarming.mixin;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;


@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin {

    @Inject(method = "breed", at = @At("HEAD"))
    private void OF$breed(ServerWorld world, AnimalEntity other, CallbackInfo ci){
        AnimalEntity animalEntity = ((AnimalEntity) (Object) this);
        PassiveEntity passiveEntity = animalEntity.createChild(world, other);
        ServerPlayerEntity player = other.getLovingPlayer();
        if (player != null && player.getEquippedStack(EquipmentSlot.HEAD).getItem().equals(OFObjects.STRAW_HAT) && passiveEntity instanceof AnimalEntity) {
            Random random = world.getRandom();
            int tries = 0;
            if (random.nextInt(3) == 0) {
                tries++;
                if (random.nextInt(12) == 0) {
                    tries++;
                }
            }
            for (int i = 0; i < tries; i++) {
                PassiveEntity newChild = animalEntity.createChild(world, other);
                if (newChild != null) {
                    player.incrementStat(Stats.ANIMALS_BRED);
                    Criteria.BRED_ANIMALS.trigger(player, animalEntity, other, newChild);
                    newChild.setBaby(true);
                    newChild.refreshPositionAndAngles(animalEntity.getX(), animalEntity.getY(), animalEntity.getZ(), 0.0F, 0.0F);
                    world.spawnEntity(newChild);
                }
            }
        }
    }
}
