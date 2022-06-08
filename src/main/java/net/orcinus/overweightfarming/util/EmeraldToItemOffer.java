package net.orcinus.overweightfarming.util;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.Random;


public class EmeraldToItemOffer implements TradeOffers.Factory {

    private final ItemStack sell;
    private final int price;
    private final int maxUses;
    private final int experience;
    private final float multiplier;

    public EmeraldToItemOffer(ItemStack stack, int price, int maxUses, int experience, float multiplier) {
        this.sell = stack;
        this.price = price;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = multiplier;
    }

    public TradeOffer create(Entity entity, Random random) {
        return new TradeOffer(new ItemStack(Items.EMERALD, this.price + random.nextInt(3)), sell, this.maxUses, this.experience,
        this.multiplier);
    }
}
