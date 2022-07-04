package net.orcinus.overweightfarming.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.orcinus.overweightfarming.common.util.ITreeFeatureConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(TreeFeatureConfig.class)
public class TreeFeatureConfigMixin implements ITreeFeatureConfig {

    @Shadow
    @Final
    @Mutable
    public List<TreeDecorator> decorators;

    @Override
    public void addDecorator(TreeDecorator decorator) {
        this.decorators = new ImmutableList.Builder<TreeDecorator>().addAll(this.decorators).add(decorator).build();
    }
}
