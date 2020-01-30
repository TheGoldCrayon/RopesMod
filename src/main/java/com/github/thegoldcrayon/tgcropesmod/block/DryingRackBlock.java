package com.github.thegoldcrayon.tgcropesmod.block;

import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import com.github.thegoldcrayon.tgcropesmod.tileentity.DryingRackTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class DryingRackBlock extends HorizontalBlock
{
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final Logger LOGGER = LogManager.getLogger();
    private static final VoxelShape SHAPE_EAST_WEST = makeCuboidShape(6.0f, 0.0f, 0.0f, 10.0f, 16.0f, 16.0f);
    private static final VoxelShape SHAPE_NORTH_SOUTH = makeCuboidShape(0.0f, 0.0f, 6.0f, 16.0f, 16.0f, 10.0f);


    public DryingRackBlock()
    {
        super(Block.Properties
                .create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(5.0f)
                .notSolid()
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return state.getShape(worldIn, pos);
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return state.getShape(worldIn, pos);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        Direction direction = state.get(FACING);
        if(direction == Direction.NORTH||direction == Direction.SOUTH)
            return SHAPE_NORTH_SOUTH;
        else
            return SHAPE_EAST_WEST;
    }

    @Override
    public boolean hasTileEntity(final BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {
        return ModRegistry.DRYING_RACK_TILE_ENTITY_TYPE.get().create();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }

    @Override
    public ActionResultType onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit)
    {
        if(!worldIn.isRemote)
        {
            final TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof DryingRackTileEntity)
                NetworkHooks.openGui((ServerPlayerEntity) player, (DryingRackTileEntity) tileEntity, pos);
        }
        return ActionResultType.SUCCESS;
    }

    /*@Override
    public void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof DryingRackTileEntity) {
                final ItemStackHandler inventory = ((DryingRackTileEntity) tileEntity).inventory;
                for (int slot = 0; slot < inventory.getSlots(); ++slot)
                    InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
            }
        }
        super.onReplaced(oldState, worldIn, pos, newState, isMoving);
    }*/

    /*public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    public static boolean isOpaque(VoxelShape shape)
    {
        return true;
    }*/
}
