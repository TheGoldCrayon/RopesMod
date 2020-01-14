package com.github.thegoldcrayon.tgcropesmod.init;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import com.github.thegoldcrayon.tgcropesmod.block.FlaxCropBlock;
import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TGCRopesMod.MODID)
public class ModBlocks
{
    public static final Block ROPE = null;
    public static Block FLAX_CROP = new FlaxCropBlock();
    //public static final Block FLAX_CROP = null;
    //public static final Block FLAX_CROP = new FlaxCropBlock();
    //public static final FlaxCropBlock FLAX_CROP = null;
}
