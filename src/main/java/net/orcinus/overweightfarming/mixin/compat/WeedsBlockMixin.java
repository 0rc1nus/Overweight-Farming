package net.orcinus.overweightfarming.mixin.compat;

import net.minecraft.block.*;

//TODO wait for IW to update, REMEMBER TO ADD THE MIXIN BACK IN THE MIXIN.JSON
//@Mixin(value = WeedsBlock.class, remap = false)
public class WeedsBlockMixin extends CropBlock {
    protected WeedsBlockMixin(Settings settings) {
        super(settings);
    /*
    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), cancellable = true)
    private void OF$randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci){
        if (OverweightFarming.config.compatCrops.allowOverweightWeeds && state.contains(CropBlock.AGE) && state.get(CropBlock.AGE) == 3) {
            if (random.nextFloat() < 0.005) {
                if(world.getBlockState(pos.up(3)).isAir() && world.getRandom().nextBoolean()){
                    OverweightWeedBlock.placeTallAt(world, OFObjects.OVERWEIGHT_WEED.getDefaultState(), pos, Block.NOTIFY_LISTENERS);
                }else if(world.getBlockState(pos.up(2)).isAir()){
                    OverweightWeedBlock.placeShortAt(world, OFObjects.OVERWEIGHT_WEED.getDefaultState(), pos, Block.NOTIFY_LISTENERS);
                }
                ci.cancel();
            }
        }

     */
    }
}
