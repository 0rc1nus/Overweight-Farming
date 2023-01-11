package net.orcinus.overweightfarming.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.common.blockentities.OverweightAppleBlockEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class OFBlockEntityTypes {
    private static final Map<BlockEntityType<?>, Identifier> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        BLOCK_ENTITY_TYPES.put(type, new Identifier(OverweightFarming.MODID, name));
        return type;
    }    public static final BlockEntityType<OverweightAppleBlockEntity> OVERWEIGHT_APPLE_BLOCK_ENTITY = register("overweight_apple", FabricBlockEntityTypeBuilder
            .create(OverweightAppleBlockEntity::new, OFObjects.OVERWEIGHT_APPLE, OFObjects.OVERWEIGHT_GOLDEN_APPLE, OFObjects.OVERWEIGHT_POMME, OFObjects.OVERWEIGHT_YMPE).build(null));

    public static void init() {
        BLOCK_ENTITY_TYPES.keySet().forEach(blockEntityType -> Registry.register(Registry.BLOCK_ENTITY_TYPE, BLOCK_ENTITY_TYPES.get(blockEntityType), blockEntityType));
    }



}