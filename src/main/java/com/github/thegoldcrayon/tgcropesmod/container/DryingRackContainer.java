package com.github.thegoldcrayon.tgcropesmod.container;

import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import com.github.thegoldcrayon.tgcropesmod.tileentity.DryingRackTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.items.SlotItemHandler;
import org.omg.CORBA.ObjectHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class DryingRackContainer  extends Container
{
    public final DryingRackTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public DryingRackContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data)
    {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }
    public DryingRackContainer(final int windowId, final PlayerInventory playerInventory, final DryingRackTileEntity tileEntity)
    {
        super(ModRegistry.DRYING_RACK_CONTAINER_TYPE.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        //this.trackInt(new FunctionalIntReferenceHolder(() -> tileEntity.drying_time_left, v -> tileEntity.drying_time_left = (int) v));

        this.addSlot(new SlotItemHandler(tileEntity.inventory, DryingRackTileEntity.INPUT_SLOT, 56, 35));
        this.addSlot(new SlotItemHandler(tileEntity.inventory, DryingRackTileEntity.OUTPUT_SLOT, 116, 35));

        final int playerInventoryStartX = 8;
        final int playerInventoryStartY = 84;
        final int slotSizePlusBorder = 18;

        for(int row = 0; row < 3; row++)
        {
            for(int column = 0; column < 9; column++)
            {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, playerInventoryStartX + (column * slotSizePlusBorder), playerInventoryStartY + (row * slotSizePlusBorder)));
            }
        }

        final int playerHotbarY = playerInventoryStartY + slotSizePlusBorder * 3 + 4;

        for(int column = 0; column < 9; column++)
            this.addSlot(new Slot(playerInventory, column, playerInventoryStartX + (column * slotSizePlusBorder), playerHotbarY));
    }

    private static DryingRackTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data)
    {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null!");
        Objects.requireNonNull(data, "data cannot be null!");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof DryingRackTileEntity)
            return (DryingRackTileEntity) tileAtPos;
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack slotStack = slot.getStack();
            returnStack = slotStack.copy();

            final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!mergeItemStack(slotStack, containerSlots, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(slotStack, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (slotStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return returnStack;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, ModRegistry.DRYING_RACK.get());
    }
}
