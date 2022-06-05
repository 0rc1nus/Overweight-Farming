package net.orcinus.overweightfarming.util;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.orcinus.overweightfarming.registry.OFObjects;

public class OFUtils {
    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(OFObjects.SEEDED_PEELED_MELON, OFObjects.WAXED_SEEDED_PEELED_MELON)
            .put(OFObjects.HALF_SEEDED_PEELED_MELON, OFObjects.WAXED_HALF_SEEDED_PEELED_MELON)
            .put(OFObjects.SEEDLESS_PEELED_MELON, OFObjects.WAXED_SEEDLESS_PEELED_MELON)
            .build());


    public static final Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> WAXABLES.get().inverse());
    public static final Supplier<BiMap<Block, Block>> PEELABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(OFObjects.OVERWEIGHT_BEETROOT, OFObjects.PEELED_OVERWEIGHT_BEETROOT)
            .put(OFObjects.OVERWEIGHT_CARROT, OFObjects.PEELED_OVERWEIGHT_CARROT)
            .put(OFObjects.OVERWEIGHT_POTATO, OFObjects.PEELED_OVERWEIGHT_POTATO)
            .put(OFObjects.OVERWEIGHT_ONION, OFObjects.PEELED_OVERWEIGHT_ONION)
            .put(OFObjects.OVERWEIGHT_KIWI, OFObjects.OVERWEIGHT_SLICED_KIWI)
            .put(OFObjects.OVERWEIGHT_SLICED_KIWI, OFObjects.PEELED_OVERWEIGHT_KIWI)
            .put(OFObjects.OVERWEIGHT_GINGER, OFObjects.PEELED_OVERWEIGHT_GINGER)
            .put(OFObjects.OVERWEIGHT_COCOA, OFObjects.PEELED_OVERWEIGHT_COCOA)
            .put(Blocks.MELON, OFObjects.SEEDED_PEELED_MELON)
            .build());
    public static final Supplier<BiMap<Block, Block>> UNPEELABLES = Suppliers.memoize(() -> PEELABLES.get().inverse());


}

