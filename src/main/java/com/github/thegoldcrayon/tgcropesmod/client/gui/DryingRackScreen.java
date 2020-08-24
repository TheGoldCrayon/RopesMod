package com.github.thegoldcrayon.tgcropesmod.client.gui;

import com.github.thegoldcrayon.tgcropesmod.container.DryingRackContainer;
import com.github.thegoldcrayon.tgcropesmod.tileentity.DryingRackTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DryingRackScreen extends ContainerScreen<DryingRackContainer>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("tgcropesmod", "textures/gui/container/drying_rack.png");
    public static final Logger LOGGER = LogManager.getLogger();

    public DryingRackScreen(DryingRackContainer container, PlayerInventory inventory, ITextComponent title)
    {
        super(container, inventory, title);
    }

    //I think this is the unmapped name for render(), now with a matrixstack
    // 1 is matrixStack, 2 is partialTicks, 3 is mouseX, 4 is mouseY
    //230430 is render, 230450 is renderBackground
    @Override
    protected void func_230450_a_(final MatrixStack matrixStack, final float partialTicks, final int mouseX, final int mouseY)
    {
        this.func_230450_a_(matrixStack, partialTicks, mouseX, mouseY);
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrixStack, mouseX, mouseY); //renderHoveredToolTip

        int relMouseX = mouseX - this.guiLeft;
        int relMouseY = mouseY - this.guiTop;

        final DryingRackTileEntity tileEntity = this.container.tileEntity;
    }

    /* OLD OLD
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks)
    {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        int relMouseX = mouseX - this.guiLeft;
        int relMouseY = mouseY - this.guiTop;

        final DryingRackTileEntity tileEntity = this.container.tileEntity;
        //boolean sun1Hovered = 25 <= relMouseX && relMouseX <= 42 && 33 <= relMouseY && relMouseY <= 52;
        //boolean sun2Hovered = 61 <= relMouseX && relMouseX <= 78 && 33 <= relMouseY && relMouseY <= 52;
        //boolean sun3Hovered = 97 <= relMouseX && relMouseX <= 114 && 33 <= relMouseY && relMouseY <= 52;
        //boolean sun4Hovered = 133 <= relMouseX && relMouseX <=150 && 33 <= relMouseY && relMouseY <= 52;
    }
    */

    /* Have to figure out the unmapped func for below
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int startX = this.guiLeft;
        int startY = this.guiTop;
        int xBetweenSuns = 36;

        //blit is now 238466_a_, with args (mat, int, int, int, int, f, f, int, int, int, int)
        //this.func_238466_a_(matrixStack, startX, startY, 0, 0, this.xSize, this.ySize);
        this.func_238466_a_(matrixStack, startX, startY, 0, 0, 0.0f, 0.0f, this.xSize, this.ySize, 120, 120);

        final DryingRackTileEntity tileEntity = container.tileEntity;
        for(int slot = 0; slot <= DryingRackTileEntity.INPUT_SLOT_4; slot++)
        {
            if(tileEntity.dryingTimeLeft[slot] > 0)
            {
                int sunHeight = getDryingTimeScaled(slot);

                //this.func_238466_a_(matrixStack, startX + 25 + (xBetweenSuns * slot), startY + 53 - sunHeight, 176, 21 - sunHeight, 21, sunHeight);
                this.func_238466_a_(matrixStack, startX + 25 + (xBetweenSuns * slot), startY + 53 - sunHeight, 176, 21 - sunHeight, 0.0f, 0.0f, 21, sunHeight, 120, 120);
            }
        }
    }
    */

    private int getDryingTimeScaled(int slot)
    {
        final DryingRackTileEntity tileEntity = this.container.tileEntity;
        final int dryingTimeLeft = tileEntity.dryingTimeLeft[slot];
        final int maxDryingTime = tileEntity.TIME_TO_DRY;
        if (tileEntity.dryingTimeLeft[slot] <= 0)
            return 0;
        else
            return (maxDryingTime - dryingTimeLeft) * 21 / maxDryingTime; //sun height 21
    }
}
