package net.orcinus.overweightfarming.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MelonJuiceItem extends Item {
    public MelonJuiceItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        entity.heal(3);
        return super.finishUsingItem(stack, world, entity);
    }
}
