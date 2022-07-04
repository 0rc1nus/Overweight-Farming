package net.orcinus.overweightfarming.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.common.entities.DandelionFluffEntity;

import java.util.LinkedHashMap;
import java.util.Map;

import net.orcinus.overweightfarming.common.entities.OverweightAppleFallingBlockEntity;
import net.minecraft.entity.EntityDimensions;
public class OFEntityTypes {
    private static final Map<EntityType<?>, Identifier> ENTITY_TYPES = new LinkedHashMap<>();

    public static final EntityType<DandelionFluffEntity> DANDELION_FLUFF_ENTITY = register("dandelion_fluff", FabricEntityTypeBuilder
            .<DandelionFluffEntity>create()
            .spawnGroup(SpawnGroup.MISC)
            .entityFactory(DandelionFluffEntity::new)
            .dimensions(EntityType.ARROW.getDimensions())
            .build());

    public static final EntityType<OverweightAppleFallingBlockEntity> OVERWEIGHT_APPLE_FALLING_BLOCK = register("overweight_apple_falling_block", FabricEntityTypeBuilder
            .<OverweightAppleFallingBlockEntity>create()
            .spawnGroup(SpawnGroup.MISC)
            .entityFactory(OverweightAppleFallingBlockEntity::new)
            .trackedUpdateRate(10)
            .dimensions(EntityDimensions.fixed(0.98F, 0.98F))
            .trackedUpdateRate(20)
            .build());
    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> type) {
        ENTITY_TYPES.put(type, new Identifier(OverweightFarming.MODID, name));
        return type;
    }

    public static void init() {
        ENTITY_TYPES.keySet().forEach(entityType -> Registry.register(Registry.ENTITY_TYPE, ENTITY_TYPES.get(entityType), entityType));
    }
}
