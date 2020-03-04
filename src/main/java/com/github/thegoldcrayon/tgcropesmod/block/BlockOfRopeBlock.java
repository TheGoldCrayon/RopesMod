package com.github.thegoldcrayon.tgcropesmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockOfRopeBlock extends RotatedPillarBlock
{
    public BlockOfRopeBlock()
    {
        super(Block.Properties
                .create(Material.WOOL)
                .sound(SoundType.CLOTH)
                .hardnessAndResistance(5.0f)
        );
    }
}
