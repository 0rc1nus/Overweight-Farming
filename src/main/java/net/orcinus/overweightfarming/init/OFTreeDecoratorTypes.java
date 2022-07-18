package net.orcinus.overweightfarming.init;

import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.world.gen.decorators.AppleTreeDecorator;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OFTreeDecoratorTypes {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, OverweightFarming.MODID);

    public static final RegistryObject<TreeDecoratorType<AppleTreeDecorator>> APPLE = TREE_DECORATORS.register("apple", () -> new TreeDecoratorType<>(AppleTreeDecorator.CODEC));

}
