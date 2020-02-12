package com.github.thegoldcrayon.tgcropesmod.entity;

import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import com.github.thegoldcrayon.tgcropesmod.item.RopeArrow;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class RopeArrowEntity extends ArrowEntity
{
    public RopeArrowEntity(FMLPlayMessages.SpawnEntity spawnPacket, World world)
    {
        super(world, 0, 0, 0);
    }

    public RopeArrowEntity(EntityType<? extends ArrowEntity> arrow, World world)
    {
        super(arrow, world);
    }

    public RopeArrowEntity(World world, LivingEntity shooter)
    {
        super(world, shooter);
    }

    @Override
    protected ItemStack getArrowStack()
    {
        return new ItemStack(ModRegistry.ROPE_ARROW_ITEM.get());
    }

    @Override
    public void tick()
    {
        if(!world.isRemote)
        {

        }
    }

    @Override
    @Nonnull
    public EntityType<?> getType()
    {
        return ModRegistry.ROPE_ARROW_ENTITY_TYPE.get();
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
