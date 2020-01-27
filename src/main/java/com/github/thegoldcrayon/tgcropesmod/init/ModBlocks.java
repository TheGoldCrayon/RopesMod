package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.block.DryingRackBlock;
import com.github.thegoldcrayon.tgcropesmod.block.FlaxCropBlock;
import com.github.thegoldcrayon.tgcropesmod.block.RopeBlock;
import net.minecraftforge.registries.ObjectHolder;

//@ObjectHolder(TGCRopesMod.MODID)
public class ModBlocks
{

    @ObjectHolder("tgcropesmod:rope")
    public static RopeBlock ROPE;

    @ObjectHolder("tgcropesmod:flax_crop")
    public static FlaxCropBlock FLAX_CROP;

    @ObjectHolder("tgcropesmod:drying_rack")
    public static DryingRackBlock DRYING_RACK;
}
