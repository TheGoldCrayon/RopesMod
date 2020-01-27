package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import com.github.thegoldcrayon.tgcropesmod.tileentity.DryingRackTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TGCRopesMod.MODID)
public class ModTileEntityTypes
{
    @ObjectHolder("drying_rack")
    public static TileEntityType<DryingRackTileEntity> DRYING_RACK_TILE_ENTITY_TYPE;
}
