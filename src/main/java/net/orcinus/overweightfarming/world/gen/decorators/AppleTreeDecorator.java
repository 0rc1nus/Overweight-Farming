package net.orcinus.overweightfarming.world.gen.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.orcinus.overweightfarming.config.OFConfig;
import net.orcinus.overweightfarming.init.OFBlockTags;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFTreeDecoratorTypes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AppleTreeDecorator extends TreeDecorator {
    public static final Codec<AppleTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("smallTreeProbability").forGetter(decorator -> decorator.smallTreeProbability), Codec.floatRange(0.0F, 1.0F).fieldOf("largeTreeProbability").forGetter(decorator -> decorator.largeTreeProbability)).apply(instance, AppleTreeDecorator::new));
    private final float smallTreeProbability;
    private final float largeTreeProbability;

    public AppleTreeDecorator(float smallTreeProbability, float largeTreeProbability) {
        this.smallTreeProbability = smallTreeProbability;
        this.largeTreeProbability = largeTreeProbability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return OFTreeDecoratorTypes.APPLE.get();
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();
        int height = context.logs().size();
        float configPercent = (float)(OFConfig.OVERWEIGHT_APPLE_PERCENT.get() / 100);
        float smallTreeProbability = this.smallTreeProbability * configPercent;
        float largeTreeProbability = this.largeTreeProbability * configPercent;
        if ((random.nextFloat() < smallTreeProbability) || ((random.nextFloat() < largeTreeProbability) && height > 6)) {
            List<BlockPos> list = context.leaves();
            if (!list.isEmpty()) {
                List<BlockPos> list3 = list.stream().filter((pos) -> context.isAir(pos.below()) && context.isAir(pos.below(2)) && context.isAir(pos.below(3)) && context.level().isStateAtPosition(pos, state -> state.is(OFBlockTags.OVERWEIGHT_APPLE_LEAVES))).collect(Collectors.toList());
                if (!list3.isEmpty()) {
                    Collections.shuffle(list3);
                    Optional<BlockPos> optional = list3.stream().findFirst();
                    optional.ifPresent(blockPos -> context.setBlock(blockPos.below(), OFBlocks.OVERWEIGHT_APPLE.get().defaultBlockState()));
                }
            }
        }
    }
}
