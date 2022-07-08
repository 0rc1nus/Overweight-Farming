package net.orcinus.overweightfarming.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.orcinus.overweightfarming.common.registry.OFObjects;
import net.orcinus.overweightfarming.common.registry.OFTags;
import net.orcinus.overweightfarming.common.registry.OFWorldGenerators;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        int height = generator.getLogPositions().size();
        if ((random.nextFloat() < this.smallTreeProbability) || ((random.nextFloat() < this.largeTreeProbability) && height > 6)) {
            List<BlockPos> list = generator.getLeavesPositions();
            if (!list.isEmpty()) {
                List<BlockPos> list3 = list.stream()
                        .filter((pos) -> generator.isAir(pos.down()) && generator.isAir(pos.down(2)) && generator.isAir(pos.down(3)) && generator.getWorld()
                                .testBlockState(pos, state -> state.isIn(OFTags.OVERWEIGHT_APPLE_LEAVES)))
                        .collect(Collectors.toList());
                if (!list3.isEmpty()) {
                    Collections.shuffle(list3);
                    Optional<BlockPos> optional = list3.stream().findFirst();
                    if (optional.isPresent()) {
                        generator.replace(optional.get().down(), OFObjects.OVERWEIGHT_APPLE_STEM.getDefaultState());
                        generator.replace(optional.get().down().down(), OFObjects.OVERWEIGHT_APPLE.getDefaultState());
                    }
                }
            }
        }
    }
}
