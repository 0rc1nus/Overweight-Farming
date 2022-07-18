package net.orcinus.overweightfarming.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.blocks.blockentities.OverweightAppleBlockEntity;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OFBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OverweightFarming.MODID);

    public static final RegistryObject<BlockEntityType<OverweightAppleBlockEntity>> OVERWEIGHT_APPLE = BLOCK_ENTITY_TYPES.register("overweight_apple", () -> BlockEntityType.Builder.of(OverweightAppleBlockEntity::new, OFBlocks.OVERWEIGHT_APPLE.get(), OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get()).build(null));

}
