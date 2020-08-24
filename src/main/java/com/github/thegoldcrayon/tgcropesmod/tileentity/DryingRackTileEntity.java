package com.github.thegoldcrayon.tgcropesmod.tileentity;

import com.github.thegoldcrayon.tgcropesmod.container.DryingRackContainer;
import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import com.github.thegoldcrayon.tgcropesmod.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class DryingRackTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static final int INPUT_SLOT_1 = 0;
    public static final int INPUT_SLOT_2 = 1;
    public static final int INPUT_SLOT_3 = 2;
    public static final int INPUT_SLOT_4 = 3;
    public static final int OUTPUT_SLOT_1 = 4;
    public static final int OUTPUT_SLOT_2 = 5;
    public static final int OUTPUT_SLOT_3 = 6;
    public static final int OUTPUT_SLOT_4 = 7;

    public static final int TIME_TO_DRY = 100;

    private static final String INVENTORY_TAG = "inventory";

    public int[] dryingTimeLeft = new int[]{-1, -1, -1, -1};

    public final ItemStackHandler inventory = new ItemStackHandler(8)
    {
        @Override
        public boolean isItemValid(final int slot, @Nonnull final ItemStack stack)
        {
            switch(slot)
            {
                case INPUT_SLOT_1:
                case INPUT_SLOT_2:
                case INPUT_SLOT_3:
                case INPUT_SLOT_4:
                    return isInput(stack);
                case OUTPUT_SLOT_1:
                case OUTPUT_SLOT_2:
                case OUTPUT_SLOT_3:
                case OUTPUT_SLOT_4:
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

    private final LazyOptional<ItemStackHandler> inventoryCapabilityExternal = LazyOptional.of(() -> this.inventory);
    private final LazyOptional<IItemHandlerModifiable> inventoryCapabilityExternalUp = LazyOptional.of(() -> new RangedWrapper(this.inventory, INPUT_SLOT_1, INPUT_SLOT_4 + 1));
    private final LazyOptional<IItemHandlerModifiable> inventoryCapabilityExternalDown = LazyOptional.of(() -> new RangedWrapper(this.inventory, OUTPUT_SLOT_1, OUTPUT_SLOT_4 + 1));
    private final LazyOptional<IItemHandlerModifiable> inventoryCapabilityExternalSides = LazyOptional.of(() -> new RangedWrapper(this.inventory, INPUT_SLOT_1, INPUT_SLOT_4 + 1));


    public DryingRackTileEntity()
    {
        super(ModTileEntityTypes.DRYING_RACK_TILE_ENTITY_TYPE);
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

        for(int slot = 0; slot <= INPUT_SLOT_4; slot++)
        {
            final ItemStack input = inventory.getStackInSlot(slot);
            final ItemStack result = getResult(input);

            if(inventory.getStackInSlot(slot).isEmpty())
                dryingTimeLeft[slot] = -1;
            else
                {
                if (isInput(input))
                {
                    if (dryingTimeLeft[slot] == -1)
                        dryingTimeLeft[slot] = TIME_TO_DRY;
                    else if (dryingTimeLeft[slot] > 0)
                    {
                        dryingTimeLeft[slot]--;
                        if (dryingTimeLeft[slot] == 0)
                        {
                            input.shrink(1);
                            inventory.insertItem(slot + 4, result, false);
                            dryingTimeLeft[slot] = -1;
                        }
                    }
                }
            }
        }
        this.markDirty();
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side)
    {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            if(side == null)
                return inventoryCapabilityExternal.cast();
            switch(side)
            {
                case DOWN:
                    return inventoryCapabilityExternalDown.cast();
                case UP:
                    return inventoryCapabilityExternalUp.cast();
                case NORTH:
                case SOUTH:
                case EAST:
                case WEST:
                    return inventoryCapabilityExternalSides.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    // func_230337_a_ is read
    @Override
    public void func_230337_a_(final BlockState blockState, final CompoundNBT compound)
    {
        super.func_230337_a_(blockState, compound);
        this.inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
    }


    @Override
    public CompoundNBT write(final CompoundNBT compound)
    {
        super.write(compound);
        compound.put(INVENTORY_TAG, this.inventory.serializeNBT());
        return compound;
    }

    @Nonnull
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }

    @Override
    public void remove()
    {
        super.remove();
        inventoryCapabilityExternal.invalidate();
    }

    private boolean isInput(ItemStack stack)
    {
        if(stack.getItem() == ModRegistry.FRESH_FLAX.get())
            return true;
        else
            return false;
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
        if(input.getItem() == ModRegistry.FRESH_FLAX.get())
            return new ItemStack(ModRegistry.DRY_FLAX.get());
        else
            return new ItemStack(null);
    }
}
