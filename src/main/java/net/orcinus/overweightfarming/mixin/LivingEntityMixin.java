package net.orcinus.overweightfarming.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import net.orcinus.overweightfarming.common.registry.OFTags;
import net.orcinus.overweightfarming.common.util.OverweightGrowthManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void OF$tick(CallbackInfo ci) {
        LivingEntity livingEntity = ((LivingEntity) (Object) this);
        World world = livingEntity.getWorld();
        if (!world.isClient()) {
            if (livingEntity.getEquippedStack(EquipmentSlot.HEAD).isOf(OFObjects.STRAW_HAT) && world.getRandom().nextBoolean()) {
                int radius = 20 + world.getRandom().nextInt(20);
                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        for (int y = -1; y <= 1; y++) {
                            BlockPos entityPosition = livingEntity.getBlockPos();
                            BlockPos cropPos = new BlockPos(entityPosition.getX() + x, entityPosition.getY() + y, entityPosition.getZ() + z);
                            BlockState state = world.getBlockState(cropPos);
                            OverweightGrowthManager manager = new OverweightGrowthManager(world.getRandom());
                            Block kiwiVines = manager.getCompatBlock("hedgehog", "kiwi_vines");
                            boolean hedgehogFlag = FabricLoader.getInstance().isModLoaded("hedgehogs-fabric") && state.isOf(Objects.requireNonNull(kiwiVines));
                            if (state.isIn(BlockTags.CROPS) || state.isIn(OFTags.OVERWEIGHT_COMPAT) || state.getBlock() instanceof NetherWartBlock || hedgehogFlag) {
                                Block block = state.getBlock();
                                float v = world.getRandom().nextFloat();
                                boolean flag = v < 0.00015F && world.getRandom().nextBoolean();
                                boolean validForOverweight = false;
                                if (world instanceof ServerWorld serverLevel) {
                                    if (flag) {
                                        if (block == kiwiVines && state.get(Properties.BERRIES)) {
                                            validForOverweight = true;
                                        }
                                        if (state.contains(CropBlock.AGE)) {
                                            if (block instanceof CropBlock crop) {
                                                int age = state.get(CropBlock.AGE);
                                                if (age < crop.getMaxAge()) {
                                                    crop.applyGrowth(serverLevel, cropPos, state);
                                                }
                                                if (age == crop.getMaxAge() - 1) {
                                                    validForOverweight = true;
                                                }
                                            } else if (block instanceof StemBlock) {
                                                int age = state.get(StemBlock.AGE);
                                                if (age < StemBlock.MAX_AGE) {
                                                    serverLevel.setBlockState(cropPos, state.with(StemBlock.AGE, age + 1), 2);
                                                }
                                            }
                                        }
                                        if (state.contains(Properties.AGE_3)) {
                                            if (block instanceof BeetrootsBlock beetrootBlock) {
                                                int age = state.get(BeetrootsBlock.AGE);
                                                if (age < beetrootBlock.getMaxAge()) {
                                                    beetrootBlock.applyGrowth(serverLevel, cropPos, state);
                                                }
                                                if (age == beetrootBlock.getMaxAge() - 1) {
                                                    validForOverweight = true;
                                                }
                                            }
                                            if (block instanceof NetherWartBlock) {
                                                int age = state.get(NetherWartBlock.AGE);
                                                if (age < 3) {
                                                    world.setBlockState(cropPos, state.with(NetherWartBlock.AGE, state.get(NetherWartBlock.AGE) + 1), 2);
                                                }
                                                if (age == 2) {
                                                    validForOverweight = true;
                                                }
                                            }
                                        }
                                        if (validForOverweight) {
                                            for (Block overgrowth : manager.getOverweightMap().keySet()) {
                                                if (state.isOf(overgrowth)) {
                                                    if (livingEntity instanceof PlayerEntity) {
                                                        manager.growOverweightCrops(serverLevel, cropPos, state, serverLevel.getRandom());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
