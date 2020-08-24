package com.github.thegoldcrayon.tgcropesmod.entity;

import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import com.github.thegoldcrayon.tgcropesmod.item.RopeArrow;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public class RopeArrowEntity extends ArrowEntity
{
    public static final Logger LOGGER = LogManager.getLogger();

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
        /*if(!world.isRemote)
        {
            //once this no longer crashes, set up rope so that it checks the face of the block it lands on.
            // if valid, place rope all the way down until can't place more, or player runs out of rope in inventory.
            // must use rope from player's inv.
        }*/
        if(this.inGround)
        {
            //BlockPos position = this.getPosition();
            BlockPos position = this.func_233580_cy_();
            Block checkBlock = world.getBlockState(position.up()).getBlock();
            int maxRopeUsable = 27;
            if(!(checkBlock == Blocks.AIR) && world.getBlockState(position).getBlock() == Blocks.AIR)
            {
                int ropeUsed = 0;
                Boolean isAir = true;
                Block startingBlock = world.getBlockState(position).getBlock();
                Block currentBlock = startingBlock;
                BlockPos startingPos = position;
                BlockPos currentPos = startingPos;

                while(isAir && ropeUsed < maxRopeUsable)
                {
                    world.setBlockState(currentPos, ModRegistry.ROPE.get().getDefaultState());
                    currentPos = currentPos.down();
                    currentBlock = world.getBlockState(currentPos).getBlock();
                    if(currentBlock != Blocks.AIR)
                        isAir = false;
                    ropeUsed++;
                }
                this.remove();
            }
        }
        super.tick();
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
