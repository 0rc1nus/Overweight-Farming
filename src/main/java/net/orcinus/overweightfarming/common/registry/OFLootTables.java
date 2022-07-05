package net.orcinus.overweightfarming.common.registry;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;
import net.orcinus.overweightfarming.OverweightFarming;

import java.util.Set;

public class OFLootTables {
    public static void init() {
        Set<Identifier> villageHouseChestsId = Set.of(
                LootTables.VILLAGE_PLAINS_CHEST,
                LootTables.VILLAGE_SAVANNA_HOUSE_CHEST,
                LootTables.VILLAGE_SNOWY_HOUSE_CHEST,
                LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
                LootTables.VILLAGE_DESERT_HOUSE_CHEST);

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            Identifier injectId = new Identifier(OverweightFarming.MODID, "inject/" + id.getPath());
            if (villageHouseChestsId.contains(id)) {
                tableBuilder.pool(LootPool.builder().with(LootTableEntry.builder(injectId).weight(1).quality(0)).build());
            }

            if (LootTables.SHIPWRECK_SUPPLY_CHEST.equals(id)) {
                tableBuilder.pool(LootPool.builder().with(LootTableEntry.builder(injectId).weight(1).quality(0)).build());
            }
        });
    }
}
