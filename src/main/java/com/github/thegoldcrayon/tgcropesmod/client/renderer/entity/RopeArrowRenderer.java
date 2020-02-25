package com.github.thegoldcrayon.tgcropesmod.client.renderer.entity;

import com.github.thegoldcrayon.tgcropesmod.entity.RopeArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class RopeArrowRenderer extends ArrowRenderer<RopeArrowEntity>
{
    public RopeArrowRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(RopeArrowEntity entity)
    {
        return new ResourceLocation("tgcropesmod:textures/entity/rope_arrow.png");
    }
}
