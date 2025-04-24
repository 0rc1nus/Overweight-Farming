package net.orcinus.overweightfarming.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class OFConfig {

    public static final ModConfigSpec.Builder BUILDER;
    public static ModConfigSpec COMMON;
    public static ModConfigSpec.IntValue OVERWEIGHT_APPLE_PERCENT;

    static {
        BUILDER = new ModConfigSpec.Builder();

        OVERWEIGHT_APPLE_PERCENT = BUILDER.comment("Chance of overweight apple generating on trees").defineInRange("overweightApplePercent", 100, 0, Integer.MAX_VALUE);

        COMMON = BUILDER.build();
    }

}
