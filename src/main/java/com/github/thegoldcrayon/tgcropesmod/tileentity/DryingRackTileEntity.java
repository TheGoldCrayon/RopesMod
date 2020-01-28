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
import java.util.Optional;

public class DryingRackTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int TIME_TO_DRY = 400;

    public static int dryingTimeLeft = -1;

    public final ItemStackHandler inventory = new ItemStackHandler(2)
    {
        @Override
        public boolean isItemValid(final int slot, @Nonnull final ItemStack stack)
        {
            switch(slot)
            {
                case INPUT_SLOT:
                {
                    return isInput(stack);
                }
                case OUTPUT_SLOT:
                    return isOutput(stack);
                default:
                {
                    return false;
                }
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
    }

    public DryingRackTileEntity(TileEntityType type)
    {
        super(type);
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
        if(world == null || world.isRemote)
            return;

        final ItemStack input = inventory.getStackInSlot(INPUT_SLOT);
        final ItemStack result = getResult(input);

        if(!result.isEmpty() && isInput(input))
        {
            final boolean canInsertResultIntoOutput = inventory.insertItem(OUTPUT_SLOT, result, true).isEmpty();
            if(canInsertResultIntoOutput)
            {
                if(dryingTimeLeft == -1)
                    dryingTimeLeft = TIME_TO_DRY;

                else if(dryingTimeLeft > 0)
                {
                    dryingTimeLeft--;
                    if(dryingTimeLeft == 0)
                    {
                        inventory.insertItem(OUTPUT_SLOT, result, false);
                        input.shrink(1);
                        dryingTimeLeft = -1;
                    }
                }
            }
        }

        this.markDirty();
    }

    private boolean isInput(ItemStack stack)
    {
        if(stack.getItem() == ModRegistry.FRESH_FLAX.get())
        {
            LOGGER.debug(stack + " is a valid input.");
            return true;
        }
        else
        {
            LOGGER.debug(stack + " is an invalid input.");
            return false;
        }
    }

    private boolean isOutput(ItemStack stack)
    {
        if(stack.getItem() == ModRegistry.DRY_FLAX.get())
            return true;
        else
            return false;
    }

    private ItemStack getResult(final ItemStack input)
    {
        if(input == new ItemStack(ModRegistry.FRESH_FLAX.get()))
            return new ItemStack(ModRegistry.DRY_FLAX.get());
        else
            return new ItemStack(null);
    }
}
