package net.orcinus.overweightfarming.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.entities.DandelionFluffEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class OFEntityTypes {
    private static final Map<EntityType<?>, Identifier> ENTITY_TYPES = new LinkedHashMap<>();

    public static final EntityType<DandelionFluffEntity> DANDELION_FLUFF_ENTITY = register("dandelion_fluff", FabricEntityTypeBuilder
            .<DandelionFluffEntity>create()
            .spawnGroup(SpawnGroup.MISC)
            .entityFactory(DandelionFluffEntity::new)
            .dimensions(EntityType.ARROW.getDimensions())
            .build());

    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> type) {
        ENTITY_TYPES.put(type, new Identifier(OverweightFarming.MODID, name));
        return type;
    }

    public static void init() {
        ENTITY_TYPES.keySet().forEach(entityType -> Registry.register(Registry.ENTITY_TYPE, ENTITY_TYPES.get(entityType), entityType));
    }
}
