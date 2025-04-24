package net.orcinus.overweightfarming.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.blocks.blockentities.OverweightAppleBlockEntity;

public class OFBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, OverweightFarming.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OverweightAppleBlockEntity>> OVERWEIGHT_APPLE = BLOCK_ENTITY_TYPES.register("overweight_apple", () -> BlockEntityType.Builder.of(OverweightAppleBlockEntity::new, OFBlocks.OVERWEIGHT_APPLE.get(), OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get()).build(null));

}
