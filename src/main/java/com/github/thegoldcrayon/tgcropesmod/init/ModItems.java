package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TGCRopesMod.MODID)
public class ModItems
{
    @ObjectHolder("flax_seeds")
    public static Item FLAX_SEEDS;

    @ObjectHolder("fresh_flax")
    public static Item FRESH_FLAX;
}
