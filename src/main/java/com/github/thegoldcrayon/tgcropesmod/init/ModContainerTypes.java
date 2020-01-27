package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import com.github.thegoldcrayon.tgcropesmod.container.DryingRackContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TGCRopesMod.MODID)
public class ModContainerTypes
{
    @ObjectHolder("drying_rack")
    public static ContainerType<DryingRackContainer> DRYING_RACK_CONTAINER_TYPE;
}
