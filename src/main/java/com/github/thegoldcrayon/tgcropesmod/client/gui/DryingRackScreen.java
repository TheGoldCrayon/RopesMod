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

    /*
    230430 is render()
    230450 is drawGuiContainerBackgroundLayer()
    230446, 230651 is renderBackground()
    230459 is renderHoveredToolTip()
    */
    @Override
    public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.func_230446_a_(matrixStack);
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrixStack, mouseX, mouseY);

        int relMouseX = mouseX - this.guiLeft;
        int relMouseY = mouseY - this.guiTop;

        final DryingRackTileEntity tileEntity = this.container.tileEntity;
    }

    /* OLD
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

    @Override
    protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) //protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int startX = this.guiLeft;
        int startY = this.guiTop;
        int xBetweenSuns = 36;

        //blit is now func_238474_b_()

        this.func_238474_b_(matrixStack, startX, startY, 0, 0, this.xSize, this.ySize);

        final DryingRackTileEntity tileEntity = container.tileEntity;
        for(int slot = 0; slot <= DryingRackTileEntity.INPUT_SLOT_4; slot++)
        {
            if(tileEntity.dryingTimeLeft[slot] > 0)
            {
                int sunHeight = getDryingTimeScaled(slot);
                LOGGER.debug(sunHeight);
                this.func_238474_b_(matrixStack, startX + 25 + (xBetweenSuns * slot), startY + 53 - sunHeight, 176, 21 - sunHeight, 21, sunHeight);
            }
        }
    }


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
