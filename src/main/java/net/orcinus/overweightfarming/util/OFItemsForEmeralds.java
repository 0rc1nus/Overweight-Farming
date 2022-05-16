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

    public OFItemsForEmeralds(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
        this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
    }

    public OFItemsForEmeralds(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
        this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
    }

    public OFItemsForEmeralds(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
        this.itemStack = p_35758_;
        this.emeraldCost = p_35759_;
        this.numberOfItems = p_35760_;
        this.maxUses = p_35761_;
        this.villagerXp = p_35762_;
        this.priceMultiplier = p_35763_;
    }

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity p_35706_, Random p_35707_) {
        return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}
