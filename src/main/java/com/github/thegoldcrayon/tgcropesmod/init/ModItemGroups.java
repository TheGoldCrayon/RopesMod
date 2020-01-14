package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public final class ModItemGroups
{
    public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(TGCRopesMod.MODID, () -> new ItemStack(ModItems.FRESH_FLAX));

    public static final class ModItemGroup extends ItemGroup
    {
        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier)
        {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon()
        {
            return iconSupplier.get();
        }
    }
}
