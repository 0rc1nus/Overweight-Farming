package com.binome.overweightfarming;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = OverweightFarming.MODID)
public class OFConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public Crops crops = new Crops();

    @ConfigEntry.Gui.CollapsibleObject
    public CompatCrops compatCrops = new CompatCrops();

    public static class Crops {
        public final boolean allowOverweightPotato = true;
        public final boolean allowOverweightCarrot = true;
        public final boolean allowOverweightCocoa = true;
        public final boolean allowOverweightBeetroot = true;
    }

    public static class CompatCrops {
        public final boolean allowOverweightCabbage = true;
        public final boolean allowOverweightOnion = true;
        public final boolean allowOverweightMandrake = true;
        public final boolean allowOverweightGarlic = true;
        public final boolean allowOverweightBloodroot = true;
    }
}
