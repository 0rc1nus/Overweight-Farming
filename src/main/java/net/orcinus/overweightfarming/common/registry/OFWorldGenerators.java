package net.orcinus.overweightfarming.common.registry;

import com.mojang.serialization.Codec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.common.worldgen.AppleTreeDecorator;

public class OFWorldGenerators {


    public static final TreeDecoratorType<AppleTreeDecorator> APPLE = register(
            new Identifier(OverweightFarming.MODID, "apple"),
            AppleTreeDecorator.CODEC
    );

    private static <P extends TreeDecorator> TreeDecoratorType<P> register(Identifier id, Codec<P> codec) {
        return Registry.register(Registries.TREE_DECORATOR_TYPE, id, new TreeDecoratorType<>(codec));
    }

    public static void init() {

    }

}
