package net.orcinus.overweightfarming.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class OFItemsForEmeralds implements VillagerTrades.ItemListing {
    private final ItemStack itemStack;
    private final int emeraldCost;
    private final int numberOfItems;
    private final int maxUses;
    private final int villagerXp;
    private final float priceMultiplier;

    public OFItemsForEmeralds(Item item, int emeraldCost, int numberOfItems, int villagerXp) {
        this(new ItemStack(item), emeraldCost, numberOfItems, 12, villagerXp);
    }

    public OFItemsForEmeralds(ItemStack stack, int emeraldCost, int numberOfItems, int maxUses, int villagerXp) {
        this(stack, emeraldCost, numberOfItems, maxUses, villagerXp, 0.05F);
    }

    public OFItemsForEmeralds(ItemStack stack, int emeraldCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier) {
        this.itemStack = stack;
        this.emeraldCost = emeraldCost;
        this.numberOfItems = numberOfItems;
        this.maxUses = maxUses;
        this.villagerXp = villagerXp;
        this.priceMultiplier = priceMultiplier;
    }

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity entity, Random random) {
        return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}
