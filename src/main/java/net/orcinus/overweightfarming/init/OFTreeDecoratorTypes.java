package net.orcinus.overweightfarming.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.world.gen.decorators.AppleTreeDecorator;

public class OFTreeDecoratorTypes {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, OverweightFarming.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<AppleTreeDecorator>> APPLE = TREE_DECORATORS.register("apple", () -> new TreeDecoratorType<>(AppleTreeDecorator.CODEC));

}
