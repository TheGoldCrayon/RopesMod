package com.github.thegoldcrayon.tgcropesmod.block;

import com.github.thegoldcrayon.tgcropesmod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class FlaxCropBlock extends CropsBlock
{
    public static final IntegerProperty FLAX_AGE = BlockStateProperties.AGE_0_3;
    private static final VoxelShape[] SHAPE = new VoxelShape[]{
            Block.makeCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 4.0d, 16.0d),
            Block.makeCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 8.0d, 16.0d),
            Block.makeCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 12.0d, 16.0d),
            Block.makeCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 16.0d, 16.0d)
    };

    public FlaxCropBlock()
    {
        super(Properties
                .create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .sound(SoundType.CROP)
                .hardnessAndResistance(0.0f)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE[state.get(this.getAgeProperty())];
    }

    @Override
    protected IItemProvider getSeedsItem()
    {
        return ModItems.FLAX_SEEDS;
    }

    @Override
    public int getMaxAge()
    {
        return 3;
    }

    @Override
    public IntegerProperty getAgeProperty()
    {
        return FLAX_AGE;
    }

    @Override
    public void func_225534_a_(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_)
    {
        if (p_225534_4_.nextInt(3) != 0)
        {
            super.func_225534_a_(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
        }
    }

    @Override
    protected int getBonemealAgeIncrease(World worldIn)
    {
        return super.getBonemealAgeIncrease(worldIn) / 3;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FLAX_AGE);
    }
}