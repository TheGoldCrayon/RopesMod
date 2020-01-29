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
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
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
        for(int slot = 0; slot <= DryingRackTileEntity.INPUT_SLOT_4; slot++) {
            ItemStack stack = tileEntity.inventory.getStackInSlot(slot);

            if (!stack.isEmpty())
            {
                BlockState state = tileEntity.getBlockState();
                Direction facing = state.get(DryingRackBlock.FACING);
                float yRotation = getRotation(facing);
                Quaternion rot2 = Vector3f.YP.rotationDegrees(yRotation);

                    if(slot == 0)
                    {
                        matrixStack.push();

                        matrixStack.translate(0.42, 0.63, 0.215);
                        matrixStack.scale(0.5f, 0.5f, 0.5f);
                        matrixStack.rotate(rot2);
                        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                        IBakedModel model = itemRenderer.getItemModelWithOverrides(stack, tileEntity.getWorld(), null);
                        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, model);

                        matrixStack.pop();
                    }

                    if(slot == 1)
                    {
                        matrixStack.push();

                        matrixStack.translate(0.42, 0.63, 0.41);
                        matrixStack.scale(0.5f, 0.5f, 0.5f);
                        matrixStack.rotate(rot2);
                        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                        IBakedModel model = itemRenderer.getItemModelWithOverrides(stack, tileEntity.getWorld(), null);
                        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, model);

                        matrixStack.pop();
                    }

                    if(slot == 2)
                    {
                        matrixStack.push();

                        matrixStack.translate(0.42, 0.63, 0.595);
                        matrixStack.scale(0.5f, 0.5f, 0.5f);
                        matrixStack.rotate(rot2);
                        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                        IBakedModel model = itemRenderer.getItemModelWithOverrides(stack, tileEntity.getWorld(), null);
                        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, model);

                        matrixStack.pop();
                    }

                    if(slot == 3)
                    {
                        matrixStack.push();

                        matrixStack.translate(0.42, 0.63, 0.78);
                        matrixStack.scale(0.5f, 0.5f, 0.5f);
                        matrixStack.rotate(rot2);
                        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                        IBakedModel model = itemRenderer.getItemModelWithOverrides(stack, tileEntity.getWorld(), null);
                        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, model);

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
