package com.github.thegoldcrayon.tgcropesmod.client.renderer;

import com.github.thegoldcrayon.tgcropesmod.block.DryingRackBlock;
import com.github.thegoldcrayon.tgcropesmod.init.ModRegistry;
import com.github.thegoldcrayon.tgcropesmod.tileentity.DryingRackTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import javafx.geometry.Pos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
//import net.minecraft.client.renderer.Quaternion;
import net.minecraft.util.math.vector.Quaternion;
//import net.minecraft.client.renderer.Vector3f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class DryingRackRenderer extends TileEntityRenderer<DryingRackTileEntity>
{
    public DryingRackRenderer(TileEntityRendererDispatcher rendererDispatcher)
    {
        super(rendererDispatcher);
    }

    @Override
    public void render(DryingRackTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay)
    {
        for(int slot = DryingRackTileEntity.OUTPUT_SLOT_1; slot <= DryingRackTileEntity.OUTPUT_SLOT_4; slot++)
        {
            ItemStack stack = tileEntity.inventory.getStackInSlot(slot);
            ItemStack stack2 = tileEntity.inventory.getStackInSlot(slot - 4);

            if (!stack.isEmpty())
            {
                BlockState state = tileEntity.getBlockState();
                Direction facing = state.get(DryingRackBlock.FACING);
                float yRotation = getRotation(facing);
                Quaternion rotation = Vector3f.YP.rotationDegrees(yRotation);
                double startX = 0.42;
                double yCoord = 0.63;
                double startZ = 0.215;
                double spacing = 0.19;
                double relStartX, relStartZ, xCoord, zCoord;

                matrixStack.push();

                if(facing == Direction.NORTH)
                {
                    relStartX = 1;
                    relStartZ = 0;

                    xCoord = relStartX - (startZ + ((slot - 4) * spacing));
                    zCoord = relStartZ + startX;
                }
                else if(facing == Direction.SOUTH)
                {
                    relStartX = 0;
                    relStartZ = 1;

                    xCoord = relStartX + (startZ + ((slot - 4) * spacing));
                    zCoord = relStartZ - startX;
                }
                else if(facing == Direction.EAST)
                {
                    relStartX = 1;
                    relStartZ = 1;

                    xCoord = relStartX - startX;
                    zCoord = relStartZ - (startZ + ((slot - 4) * spacing));
                }
                else
                {
                    relStartX = 0;
                    relStartZ = 0;

                    xCoord = relStartX + startX;
                    zCoord = relStartZ + (startZ + ((slot - 4) * spacing));
                }

                matrixStack.translate(xCoord, yCoord, zCoord);
                matrixStack.scale(0.5f, 0.5f, 0.5f);
                matrixStack.rotate(rotation);
                ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                IBakedModel model = itemRenderer.getItemModelWithOverrides(stack, tileEntity.getWorld(), null);
                itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, model);

                matrixStack.pop();
            }
            else
            {
                if (!stack2.isEmpty())
                {
                    BlockState state = tileEntity.getBlockState();
                    Direction facing = state.get(DryingRackBlock.FACING);
                    float yRotation = getRotation(facing);
                    Quaternion rotation = Vector3f.YP.rotationDegrees(yRotation);
                    double startX = 0.42;
                    double yCoord = 0.63;
                    double startZ = 0.215;
                    double spacing = 0.19;
                    double relStartX, relStartZ, xCoord, zCoord;

                    matrixStack.push();

                    if (facing == Direction.NORTH)
                    {
                        relStartX = 1;
                        relStartZ = 0;

                        xCoord = relStartX - (startZ + ((slot - 4) * spacing));
                        zCoord = relStartZ + startX;
                    }
                    else if (facing == Direction.SOUTH)
                    {
                        relStartX = 0;
                        relStartZ = 1;

                        xCoord = relStartX + (startZ + ((slot - 4) * spacing));
                        zCoord = relStartZ - startX;
                    }
                    else if (facing == Direction.EAST)
                    {
                        relStartX = 1;
                        relStartZ = 1;

                        xCoord = relStartX - startX;
                        zCoord = relStartZ - (startZ + ((slot - 4) * spacing));
                    }
                    else
                    {
                        relStartX = 0;
                        relStartZ = 0;

                        xCoord = relStartX + startX;
                        zCoord = relStartZ + (startZ + ((slot - 4) * spacing));
                    }

                    matrixStack.translate(xCoord, yCoord, zCoord);
                    matrixStack.scale(0.5f, 0.5f, 0.5f);
                    matrixStack.rotate(rotation);
                    ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                    IBakedModel model = itemRenderer.getItemModelWithOverrides(stack2, tileEntity.getWorld(), null);
                    itemRenderer.renderItem(stack2, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, model);

                    matrixStack.pop();
                }
            }
        }
    }
    private float getRotation(Direction facing)
    {
        if(facing == Direction.SOUTH)
            return 270.0f;
        else if(facing == Direction.EAST)
            return 0.0f;
        else if(facing == Direction.WEST)
            return 180.0f;
        else
            return 90.0f;
    }
}
