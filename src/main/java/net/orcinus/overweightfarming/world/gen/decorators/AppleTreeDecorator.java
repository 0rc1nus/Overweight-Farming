package net.orcinus.overweightfarming.world.gen.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.orcinus.overweightfarming.init.OFBlockTags;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFTreeDecoratorTypes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AppleTreeDecorator extends TreeDecorator {
    public static final MapCodec<AppleTreeDecorator> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.INT.fieldOf("minHeightRequirement").forGetter(decorator -> decorator.minHeightRequirement)
            ).apply(instance, AppleTreeDecorator::new));
    private final int minHeightRequirement;

    public AppleTreeDecorator() {
        this(0);
    }

    public AppleTreeDecorator(int minHeightRequirement) {
        this.minHeightRequirement = minHeightRequirement;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return OFTreeDecoratorTypes.APPLE.get();
    }

    @Override
    public void place(Context context) {
        int height = context.logs().size();

        if (this.minHeightRequirement > 0 && height < this.minHeightRequirement) return;

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
