package com.github.thegoldcrayon.tgcropesmod.client.gui;

import com.github.thegoldcrayon.tgcropesmod.container.DryingRackContainer;
import com.github.thegoldcrayon.tgcropesmod.tileentity.DryingRackTileEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DryingRackScreen extends ContainerScreen<DryingRackContainer>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("tgcropesmod", "textures/gui/container/drying_rack.png");

    public DryingRackScreen(DryingRackContainer container, PlayerInventory inventory, ITextComponent title)
    {
        super(container, inventory, title);
    }

    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks)
    {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        int relMouseX = mouseX - this.guiLeft;
        int relMouseY = mouseY - this.guiTop;

        final DryingRackTileEntity tileEntity = this.container.tileEntity;
        /*boolean sun1Hovered = 25 <= relMouseX && relMouseX <= 42 && 33 <= relMouseY && relMouseY <= 52;
        boolean sun2Hovered = 61 <= relMouseX && relMouseX <= 78 && 33 <= relMouseY && relMouseY <= 52;
        boolean sun3Hovered = 97 <= relMouseX && relMouseX <= 114 && 33 <= relMouseY && relMouseY <= 52;
        boolean sun4Hovered = 133 <= relMouseX && relMouseX <=150 && 33 <= relMouseY && relMouseY <= 52;*/
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int startX = this.guiLeft;
        int startY = this.guiTop;
        int xBetweenSuns = 36;

        this.blit(startX, startY, 0, 0, this.xSize, this.ySize);

        final DryingRackTileEntity tileEntity = container.tileEntity;
        for(int slot = 0; slot <= DryingRackTileEntity.INPUT_SLOT_4; slot++)
        {
            if(tileEntity.dryingTimeLeft[slot] > 0)
            {
                int sunHeight = getDryingTimeScaled(slot);

                this.blit(startX + 25 + (xBetweenSuns * slot), startY + 53 - sunHeight, 176, 21 - sunHeight, 21, sunHeight);
            }
        }

    }

    private int getDryingTimeScaled(int slot)
    {
        final DryingRackTileEntity tileEntity = this.container.tileEntity;
        final short dryingTimeLeft = (short)tileEntity.dryingTimeLeft[slot];
        final short maxDryingTime = (short)tileEntity.TIME_TO_DRY;
        if (tileEntity.dryingTimeLeft[slot] <= 0)
            return 0;
        else
            return (maxDryingTime - dryingTimeLeft) * 21 / maxDryingTime; //sun height 21
    }
}
