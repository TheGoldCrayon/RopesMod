package com.github.thegoldcrayon.tgcropesmod.item;

import com.github.thegoldcrayon.tgcropesmod.entity.RopeArrowEntity;
import com.github.thegoldcrayon.tgcropesmod.init.ModItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RopeArrow extends ArrowItem
{
    public RopeArrow()
    {
        super(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
    {
        RopeArrowEntity arrow = new RopeArrowEntity(worldIn, shooter);
        arrow.setPotionEffect(stack);
        arrow.setDamage(0.0d);
        arrow.setKnockbackStrength(0);
        return arrow;
    }
}
