package net.orcinus.overweightfarming.mixin;

import net.minecraft.block.BeetrootsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orcinus.overweightfarming.init.OFObjects;
import net.orcinus.overweightfarming.util.OvergrowthHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void OF$tick(CallbackInfo ci){
        LivingEntity livingEntity = ((LivingEntity) (Object) this);
        World world = livingEntity.getWorld();
        if (!world.isClient) {
            if (livingEntity.getEquippedStack(EquipmentSlot.HEAD).getItem().equals(OFObjects.STRAW_HAT)) {
                int radius = 40;
                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        for (int y = -1; y <= 1; y++) {
                            BlockPos entityPosition = livingEntity.getBlockPos();
                            BlockPos cropPos = new BlockPos(entityPosition.getX() + x, entityPosition.getY() + y, entityPosition.getZ() + z);
                            BlockState state = world.getBlockState(cropPos);
                            if (state.isIn(BlockTags.CROPS)) {
                                Block block = state.getBlock();
                                float v = world.getRandom().nextFloat();
                                boolean flag = v < 1.6540289E-4 && world.getRandom().nextBoolean();
                                boolean validForOverweight = false;
                                if (world instanceof ServerWorld serverLevel) {
                                    if (flag) {
                                        if (state.contains(CropBlock.AGE)) {
                                            if (block instanceof CropBlock crop) {
                                                int age = state.get(CropBlock.AGE);
                                                if (age < crop.getMaxAge()) {
                                                    crop.applyGrowth(serverLevel, cropPos, state);
                                                }
                                                if (age == crop.getMaxAge())
                                                    validForOverweight = true;
                                            } else if (block instanceof StemBlock) {
                                                int age = state.get(StemBlock.AGE);
                                                if (age < StemBlock.MAX_AGE) {
                                                    serverLevel.setBlockState(cropPos, state.with(StemBlock.AGE, age + 1), 2);
                                                }
                                            }
                                        }
                                        if (state.contains(BeetrootsBlock.AGE) && block instanceof BeetrootsBlock beetrootBlock) {
                                            int age = state.get(BeetrootsBlock.AGE);
                                            if (age < beetrootBlock.getMaxAge()) {
                                                beetrootBlock.applyGrowth(serverLevel, cropPos, state);
                                            }
                                            if (age == beetrootBlock.getMaxAge())
                                                validForOverweight = true;
                                        }
                                        if (validForOverweight) {
                                            for (Block overgrowth : OvergrowthHandler.CROPS_TO_OVERGROWN.keySet()) {
                                                if (state.isOf(overgrowth)) {
                                                    OvergrowthHandler.growOverweightCrop(serverLevel.getRandom(), state, serverLevel, cropPos, overgrowth);
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
