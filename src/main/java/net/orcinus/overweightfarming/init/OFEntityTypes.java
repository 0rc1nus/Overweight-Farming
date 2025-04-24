package net.orcinus.overweightfarming.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.entities.OverweightAppleFallingBlockEntity;

public class OFEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, OverweightFarming.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<OverweightAppleFallingBlockEntity>> OVERWEIGHT_APPLE_FALLING_BLOCK = ENTITY_TYPES.register("overweight_apple_falling_block", () -> EntityType.Builder.<OverweightAppleFallingBlockEntity>of(OverweightAppleFallingBlockEntity::new, MobCategory.MISC).sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(20).build("overweight_apple_falling_block"));

}
