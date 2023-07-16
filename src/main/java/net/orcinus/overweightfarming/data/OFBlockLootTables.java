package net.orcinus.overweightfarming.data;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.overweightfarming.blocks.CropStemBlock;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFItemTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class OFBlockLootTables extends BlockLootSubProvider {

    public OFBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(OFBlocks.ALLIUM_BUSH.get(), (block) -> createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.dropSelf(OFBlocks.HALF_SEEDED_PEELED_MELON.get());
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_APPLE.get(), Items.APPLE);
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_BAKED_POTATO.get(), Items.BAKED_POTATO);
        this.simpleOverweightSeededCropDrop(OFBlocks.OVERWEIGHT_BEETROOT.get(), Items.BEETROOT, Items.BEETROOT_SEEDS);
        this.simpleOverweightSeededCropDrop(OFBlocks.OVERWEIGHT_CABBAGE.get(), Objects.requireNonNull(this.getCompatItem("farmersdelight", "cabbage")), Objects.requireNonNull(this.getCompatItem("farmersdelight", "cabbage_seeds")));
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_CARROT.get(), Items.CARROT);
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_COCOA.get(), Items.COCOA_BEANS);
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_GINGER.get(), this.getCompatItem("snowyspirit", "ginger"));
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get(), Items.GOLDEN_APPLE);
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_KIWI.get(), this.getCompatItem("hedgehog", "kiwi"));
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_NETHER_WART.get(), Items.NETHER_WART);
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_ONION.get(), this.getCompatItem("farmersdelight", "onion"));
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get(), Items.POISONOUS_POTATO);
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_POTATO.get(), Items.POTATO);
        this.simpleOverweightCropDrop(OFBlocks.OVERWEIGHT_SLICED_KIWI.get(), this.getCompatItem("hedgehog", "kiwi"));
        this.simpleOverweightSeededCropDrop(OFBlocks.PEELED_OVERWEIGHT_BEETROOT.get(), Items.BEETROOT, Items.BEETROOT_SEEDS);
        this.simpleOverweightCropDrop(OFBlocks.PEELED_OVERWEIGHT_CARROT.get(), Items.CARROT);
        this.simpleOverweightCropDrop(OFBlocks.PEELED_OVERWEIGHT_COCOA.get(), Items.COCOA_BEANS);
        this.simpleOverweightCropDrop(OFBlocks.PEELED_OVERWEIGHT_GINGER.get(), this.getCompatItem("snowyspirit", "ginger"));
        this.simpleOverweightCropDrop(OFBlocks.PEELED_OVERWEIGHT_KIWI.get(), this.getCompatItem("hedgehog", "kiwi"));
        this.simpleOverweightCropDrop(OFBlocks.PEELED_OVERWEIGHT_ONION.get(), this.getCompatItem("farmersdelight", "onion"));
        this.simpleOverweightCropDrop(OFBlocks.PEELED_OVERWEIGHT_POTATO.get(), Items.POTATO);
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_BEETROOT.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_CARROT.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_POTATO.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_COCOA.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_APPLE.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_GOLDEN_APPLE.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_ONION.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_CABBAGE.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_GINGER.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_KIWI.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_POISONOUS_POTATO.get());
        this.dropPottedContents(OFBlocks.POTTED_OVERWEIGHT_NETHER_WART.get());
        this.dropSelf(OFBlocks.SEEDED_PEELED_MELON.get());
        this.dropSelf(OFBlocks.SEEDLESS_PEELED_MELON.get());
        this.dropSelf(OFBlocks.VEGETABLE_COMPOST.get());
        this.dropSelf(OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get());
        this.dropSelf(OFBlocks.WAXED_SEEDED_PEELED_MELON.get());
        this.dropSelf(OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get());
    }

    @Nullable
    private Item getCompatItem(String farmersdelight, String onion) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(farmersdelight, onion));
    }

    private void simpleOverweightSeededCropDrop(@NotNull Block block, Item item, Item seed) {
        this.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(AlternativesEntry.alternatives(
                        AlternativesEntry.alternatives(
                                LootItem.lootTableItem(item)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(6.0F, 20.0F)))
                                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(OFItemTags.OVERWEIGHT_HARVESTABLES))),
                                LootItem.lootTableItem(block).apply(ApplyExplosionDecay.explosionDecay())
                        )))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(seed).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(OFItemTags.OVERWEIGHT_HARVESTABLES))))));
    }

    private void simpleOverweightCropDrop(@NotNull Block block, Item item) {
        this.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(AlternativesEntry.alternatives(
                        AlternativesEntry.alternatives(
                                LootItem.lootTableItem(item)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(6.0F, 20.0F)))
                                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(OFItemTags.OVERWEIGHT_HARVESTABLES))),
                                LootItem.lootTableItem(block).apply(ApplyExplosionDecay.explosionDecay())
                        )))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return OFBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).filter(block -> !(block instanceof CropStemBlock)).collect(Collectors.toList());
    }

}
