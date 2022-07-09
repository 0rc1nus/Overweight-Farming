package net.orcinus.overweightfarming.common.registry;

import com.mojang.serialization.Codec;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.common.worldgen.AppleTreeDecorator;
import net.orcinus.overweightfarming.mixin.TreeDecoratorTypeInvoker;

public class OFWorldGenerators implements TreeDecoratorTypeInvoker{
    public static final TreeDecoratorType<AppleTreeDecorator> APPLE = register(
            new Identifier(OverweightFarming.MODID, "apple"),
            AppleTreeDecorator.CODEC
    );

    private static <P extends TreeDecorator> TreeDecoratorType<P> register(Identifier id, Codec<P> codec) {
        return TreeDecoratorTypeInvoker.callRegister(id.toString(), codec);
    }

    public static void init() {

    }

}
