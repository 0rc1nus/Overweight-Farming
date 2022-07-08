package net.orcinus.overweightfarming.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import net.orcinus.overweightfarming.common.registry.OFTags;
import net.orcinus.overweightfarming.common.registry.OFWorldGenerators;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class AppleTreeDecorator extends TreeDecorator {
    public static final Codec<AppleTreeDecorator> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.floatRange(0.0F, 1.0F).fieldOf("smallTreeProbability").forGetter(decorator -> decorator.smallTreeProbability),
                            Codec.floatRange(0.0F, 1.0F).fieldOf("largeTreeProbability").forGetter(decorator -> decorator.largeTreeProbability))
                    .apply(instance, AppleTreeDecorator::new)
    );

    private final float smallTreeProbability;
    private final float largeTreeProbability;

    public AppleTreeDecorator(float smallTreeProbability, float largeTreeProbability) {
        this.smallTreeProbability = smallTreeProbability;
        this.largeTreeProbability = largeTreeProbability;
    }


    @Override
    protected TreeDecoratorType<?> getType() {
        return OFWorldGenerators.APPLE;
    }

    @Override
    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
        int height = logPositions.size();
        if ((random.nextFloat() < this.smallTreeProbability) || ((random.nextFloat() < this.largeTreeProbability) && height > 6)) {
            if (!leavesPositions.isEmpty()) {
                List<BlockPos> list3 = leavesPositions.stream()
                        .filter((pos) -> Feature.isAir(world, pos.down()) && Feature.isAir(world, pos.down(2)) && Feature.isAir(world, pos.down(3)) && world
                                .testBlockState(pos, state -> state.isIn(OFTags.OVERWEIGHT_APPLE_LEAVES)))
                        .collect(Collectors.toList());
                if (!list3.isEmpty()) {
                    Collections.shuffle(list3);
                    Optional<BlockPos> optional = list3.stream().findFirst();
                    if (optional.isPresent()) {
                        replacer.accept(optional.get().down(), OFObjects.OVERWEIGHT_APPLE_STEM.getDefaultState());
                        replacer.accept(optional.get().down().down(), OFObjects.OVERWEIGHT_APPLE.getDefaultState());
                    }
                }
            }
        }
    }
}
