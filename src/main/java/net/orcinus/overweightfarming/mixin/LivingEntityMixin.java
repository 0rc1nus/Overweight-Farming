package net.orcinus.overweightfarming.mixin;

import net.minecraft.state.property.Properties;
import net.orcinus.overweightfarming.registry.OFObjects;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orcinus.overweightfarming.registry.OFTags;
import net.orcinus.overweightfarming.util.OverweightGrowthManager;
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
        if (!world.isClient()) {
            if (livingEntity.getEquippedStack(EquipmentSlot.HEAD).isOf(OFObjects.STRAW_HAT)) {
                int radius = 40;
                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        for (int y = -1; y <= 1; y++) {
                            BlockPos entityPosition = livingEntity.getBlockPos();
                            BlockPos cropPos = new BlockPos(entityPosition.getX() + x, entityPosition.getY() + y, entityPosition.getZ() + z);
                            BlockState state = world.getBlockState(cropPos);
                            String hedgehogModid = "hedgehog";
                            Block kiwiVines = null;//TODO ForgeRegistries.BLOCKS.getValue(new ResourceLocation(hedgehogModid, "kiwi_vines"));
                            boolean hedgehogFlag = false;//TODO ModList.get().isLoaded(hedgehogModid) && state.is(Objects.requireNonNull(kiwiVines));
                            if (state.isIn(BlockTags.CROPS) || state.isIn(OFTags.OVERWEIGHT_COMPAT) || state.isOf(Blocks.NETHER_WART)) {
                                Block block = state.getBlock();
                                float v = world.getRandom().nextFloat();
                                boolean flag = v < 1.5E-4 && world.getRandom().nextBoolean();
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
                                                if (age == crop.getMaxAge()){
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
                                            if (block instanceof BeetrootsBlock beetrootBlock){
                                                int age = state.get(BeetrootsBlock.AGE);
                                                if (age < beetrootBlock.getMaxAge()) {
                                                    beetrootBlock.applyGrowth(serverLevel, cropPos, state);
                                                }
                                                if (age == beetrootBlock.getMaxAge())
                                                    validForOverweight = true;
                                            }
                                            if (block instanceof NetherWartBlock) {
                                                int age = state.get(BeetrootsBlock.AGE);
                                                if (age < 3) {
                                                    state = state.with(NetherWartBlock.AGE, age + 1);
                                                    world.setBlockState(cropPos, state, 2);
                                                }
                                            }
                                        }
                                        OverweightGrowthManager manager = new OverweightGrowthManager(world.getRandom());
                                        if (validForOverweight) {
                                            for (Block overgrowth : manager.getOverweightMap().keySet()) {
                                                if (state.isOf(overgrowth)) {
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
