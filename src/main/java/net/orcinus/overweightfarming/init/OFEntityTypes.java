package net.orcinus.overweightfarming.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.entities.OverweightAppleFallingBlockEntity;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OFEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, OverweightFarming.MODID);

    public static final RegistryObject<EntityType<OverweightAppleFallingBlockEntity>> OVERWEIGHT_APPLE_FALLING_BLOCK = ENTITY_TYPES.register("overweight_apple_falling_block", () -> EntityType.Builder.<OverweightAppleFallingBlockEntity>of(OverweightAppleFallingBlockEntity::new, MobCategory.MISC).sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(20).build("overweight_apple_falling_block"));

}
