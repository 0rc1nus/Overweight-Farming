package net.orcinus.overweightfarming;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.IOException;
import java.util.List;
import java.util.Set;


public class OFMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if(mixinClassName.contains("WeedsBlockMixin")){
            return FabricLoader.getInstance().isModLoaded("immersive_weathering") && targetClassName.contains("WeedsBlock");
        }
        if(mixinClassName.contains("PomegranateLeavesBlockMixin") || mixinClassName.contains("FruitBearingYmpeLogBlockMixin")){
            return FabricLoader.getInstance().isModLoaded("aylyth") && (targetClassName.contains("PomegranateLeavesBlock") || targetClassName.contains("FruitBearingYmpeLogBlock"));
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
