package net.orcinus.overweightfarming.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.orcinus.overweightfarming.common.util.ITreeFeatureConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(TreeFeatureConfig.Builder.class)
public class TreeFeatureConfigMixin {
    @Shadow private List<TreeDecorator> decorators;

    @Inject(method = "build", at = @At("HEAD"))
    private void build(CallbackInfoReturnable<TreeFeatureConfig> cir) {
        decorators = new ArrayList<>(decorators);
    }
}