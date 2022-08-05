package net.orcinus.overweightfarming.world.gen.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.orcinus.overweightfarming.init.OFBlockTags;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFTreeDecoratorTypes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiConsumer;
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
    public void place(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> consumer, Random random, List<BlockPos> logPos, List<BlockPos> leavePos) {
        int height = logPos.size();
        if ((random.nextFloat() < this.smallTreeProbability) || ((random.nextFloat() < this.largeTreeProbability) && height > 6)) {
            if (!leavePos.isEmpty()) {
                List<BlockPos> list3 = leavePos.stream().filter((pos) -> Feature.isAir(world, pos.below()) && Feature.isAir(world, pos.below(2)) && Feature.isAir(world, pos.below(3)) && world.isStateAtPosition(pos, state -> state.is(OFBlockTags.OVERWEIGHT_APPLE_LEAVES))).collect(Collectors.toList());
                if (!list3.isEmpty()) {
                    Collections.shuffle(list3);
                    Optional<BlockPos> optional = list3.stream().findFirst();
                    optional.ifPresent(blockPos -> consumer.accept(blockPos.below(), OFBlocks.OVERWEIGHT_APPLE.get().defaultBlockState()));
                }
            }
        }
    }

}