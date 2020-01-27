package com.github.thegoldcrayon.tgcropesmod.tileentity;

import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class DryingRackTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    public DryingRackTileEntity()
    {
        super(ModRegistry.DRYING_RACK_TILE_ENTITY_TYPE.get());
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return null;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_)
    {
        return null;
    }

    @Override
    public void tick()
    {

    }
}
