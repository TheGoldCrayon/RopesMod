package com.github.thegoldcrayon.tgcropesmod.block;

import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class DryingRackBlock extends HorizontalBlock
{
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public DryingRackBlock()
    {
        super(Block.Properties
                .create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(5.0f)
                .func_226896_b_()
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context)
    {

        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());

    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {

        builder.add(FACING);

    }

    @Override
    public boolean hasTileEntity()
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {
        return ModRegistry.DRYING_RACK_TILE_ENTITY_TYPE.get().create();
    }

    /*public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    public static boolean isOpaque(VoxelShape shape)
    {
        return true;
    }*/
}
