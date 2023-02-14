package net.orcinus.overweightfarming.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.orcinus.overweightfarming.OverweightFarming;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID)
public class OFConfig {

    public static final ForgeConfigSpec.Builder BUILDER;
    public static ForgeConfigSpec COMMON;
    public static ForgeConfigSpec.IntValue OVERWEIGHT_APPLE_PERCENT;

    static {
        BUILDER = new ForgeConfigSpec.Builder();

        OVERWEIGHT_APPLE_PERCENT = BUILDER.comment("Chance of overweight apple generating on trees").defineInRange("overweightApplePercent", 100, 0, Integer.MAX_VALUE);

        COMMON = BUILDER.build();
    }

}
