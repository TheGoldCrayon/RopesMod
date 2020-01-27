package com.github.thegoldcrayon.tgcropesmod.tileentity;

import com.github.thegoldcrayon.tgcropesmod.container.DryingRackContainer;
import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import com.github.thegoldcrayon.tgcropesmod.init.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DryingRackTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int TIME_TO_DRY = 400;

    public static int drying_time_left = -1;

    public final ItemStackHandler inventory = new ItemStackHandler(2)
    {
        @Override
        public boolean isItemValid(final int slot, @Nonnull final ItemStack stack)
        {
            switch(slot)
            {
                case INPUT_SLOT:
                    return isInput(stack);
                case OUTPUT_SLOT:
                    return isOutput(stack);
                default:
                    return false;
            }
        }

        @Override
        protected void onContentsChanged(final int slot)
        {
            super.onContentsChanged(slot);
            DryingRackTileEntity.this.markDirty();
        }
    };

    public DryingRackTileEntity()
    {
        super(ModTileEntityTypes.DRYING_RACK_TILE_ENTITY_TYPE);
        LOGGER.debug("Test 3");
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent(ModRegistry.DRYING_RACK.get().getTranslationKey());
    }

    @Nullable
    @Override
    public Container createMenu(final int windowId, final PlayerInventory inventory, final PlayerEntity player)
    {
        return new DryingRackContainer(windowId, inventory, this);
    }

    @Override
    public void tick()
    {
        LOGGER.debug("Test 4");

        /*if(world == null || world.isRemote)
            return;

        final ItemStack input = new ItemStack(ModRegistry.FRESH_FLAX.get());
        final ItemStack output = new ItemStack(ModRegistry.DRY_FLAX.get());

        if(drying_time_left == -1)
            drying_time_left = TIME_TO_DRY;

        if(drying_time_left < 0)
            drying_time_left--;

        if(drying_time_left == 0)
        {
            inventory.insertItem(OUTPUT_SLOT, output, false);
            input.shrink(1);
            drying_time_left = -1;
        }

        this.markDirty();*/
    }

    private boolean isInput(ItemStack stack)
    {
        if(stack == new ItemStack(ModRegistry.FRESH_FLAX.get()))
            return true;
        else
            return false;
    }

    private boolean isOutput(ItemStack stack)
    {
        if(stack == new ItemStack(ModRegistry.DRY_FLAX.get()))
            return true;
        else
            return false;
    }
}
