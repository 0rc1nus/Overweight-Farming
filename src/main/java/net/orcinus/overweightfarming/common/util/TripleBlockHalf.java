package net.orcinus.overweightfarming.common.util;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

public enum TripleBlockHalf implements StringIdentifiable {
    UPPER,
    MIDDLE,
    LOWER;

    public static final EnumProperty<TripleBlockHalf> TRIPLE_BLOCK_HALF = EnumProperty.of("half", TripleBlockHalf.class);

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this == UPPER ? "upper" : this == MIDDLE ? "middle" : "lower";
    }
}
