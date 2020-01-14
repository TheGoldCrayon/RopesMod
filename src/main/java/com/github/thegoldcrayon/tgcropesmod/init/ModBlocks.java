package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import com.github.thegoldcrayon.tgcropesmod.block.FlaxCropBlock;
import com.github.thegoldcrayon.tgcropesmod.block.RopeBlock;
import net.minecraft.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ObjectHolder;

//@ObjectHolder(TGCRopesMod.MODID)
public class ModBlocks
{

    @ObjectHolder("tgcropesmod:rope")
    public static RopeBlock ROPE;

    //public static Block FLAX_CROP = new FlaxCropBlock();
    //untextured full block
    //without annotation: everything works fine except renderlayer messed up, applies to ROPE too

    //public static final Block FLAX_CROP = null;
    // fully textured crop block, but seed is registered as crop block in creative tab, can still /give seed however
    //without annotation: crashed

    //public static final Block FLAX_CROP = new FlaxCropBlock();
    //same as first FLAX_CROP
    //without annotation: same as first FLAX_CROP

    //public static final FlaxCropBlock FLAX_CROP = null;
    // same as first FLAX_CROP
    //without annotation: crashed

    @ObjectHolder("tgcropesmod:flax_crop")
    public static FlaxCropBlock FLAX_CROP;
}
