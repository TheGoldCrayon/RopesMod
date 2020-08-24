package com.github.thegoldcrayon.tgcropesmod.item;

import com.github.thegoldcrayon.tgcropesmod.init.ModItemGroups;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RopeSwing extends Item
{

    World world;
    BlockPos blockPos, playerPos;
    PlayerEntity playerEntity;
    public RopeSwing()
    {
        super(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        world = context.getWorld();
        blockPos = context.getPos();
        playerEntity = context.getPlayer();
        //playerPos = playerEntity.getPosition();
        return ActionResultType.PASS;
    }
    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(isSelected)
        {
            //playerPos = entityIn.getPosition();
            playerPos = entityIn.func_233580_cy_(); //This might be the name of getPosition() ??
            
        }
    }
}
