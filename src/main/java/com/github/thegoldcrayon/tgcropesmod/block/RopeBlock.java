package com.github.thegoldcrayon.tgcropesmod.block;

import com.github.thegoldcrayon.tgcropesmod.TGCRopesMod;
import com.github.thegoldcrayon.tgcropesmod.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RopeBlock extends LadderBlock
{
    protected static final VoxelShape ROPE = Block.makeCuboidShape(6.0d, 0.0d, 6.0d, 10.0d, 16.0d, 10.0d);

    private static final Logger LOGGER = LogManager.getLogger(TGCRopesMod.MODID + "Rope Block Testing");


    public RopeBlock()
    {
        super(Block.Properties
                .create(Material.WOOL)
                .sound(SoundType.CLOTH)
                .hardnessAndResistance(0.5f)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return ROPE;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        int xPos = pos.getX();
        int yPos = pos.getY() + 1;
        int zPos = pos.getZ();
        BlockPos blockAbove = new BlockPos(xPos, yPos, zPos);
        Block block = worldIn.getBlockState(blockAbove).getBlock();

        if(block == Blocks.AIR)
            return false;
        else
            return true;
    }
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (!stateIn.isValidPosition(worldIn, currentPos))
            return Blocks.AIR.getDefaultState();
        else
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState blockState, World worldIn, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult)
    {
        ItemStack heldItemStack = playerEntity.getHeldItem(hand);
        Item heldItem = heldItemStack.getItem();
        Item rope = ModBlocks.ROPE.asItem();

        //LOGGER.debug(heldItemStack);
        if(heldItem == rope)
        {
            Boolean isRope = true;
            Block currentBlock = this;
            BlockPos currentPos = blockPos;
            while(isRope)
            {
                currentPos = currentPos.down();
                currentBlock = worldIn.getBlockState(currentPos).getBlock();
                //LOGGER.debug(currentPos);
                //LOGGER.debug(currentBlock);
                if(currentBlock != this)
                    isRope = false;
            }
            if(currentBlock != Blocks.AIR)
                return ActionResultType.FAIL;
            else
            {
                //LOGGER.debug(true);
                int heldItemStackCount = heldItemStack.getCount() - 1;
                ItemStack newHeldItemStack = new ItemStack(heldItem, heldItemStackCount);
                worldIn.setBlockState(currentPos, this.getDefaultState());
                playerEntity.setHeldItem(hand, newHeldItemStack);
                return ActionResultType.CONSUME;
            }
        }
        else
            return ActionResultType.FAIL;
    }

    /*@Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        Vec3d playerVector = entityIn.getMotion();
        double playerX = playerVector.getX();
        double playerY = playerVector.getY();
        double playerZ = playerVector.getZ();

        entityIn.setMotion(playerX, playerY * 1.5, playerZ);

        LOGGER.debug("x: " + playerX + " y: " + playerY + " z: " + playerZ);
    }*/
}
