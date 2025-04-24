package net.orcinus.overweightfarming.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class OFLootTableProvider extends LootTableProvider {

    public OFLootTableProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, Set.of(), ImmutableList.of(new SubProviderEntry(OFBlockLootTables::new, LootContextParamSets.BLOCK)), completableFuture);
    }

}
