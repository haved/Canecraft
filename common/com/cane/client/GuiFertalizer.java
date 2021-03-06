package com.cane.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

import com.cane.Reference;
import com.cane.inventory.ContainerFertalizer;
import com.cane.tileentity.TileEntityFertalizer;

public class GuiFertalizer extends GuiContainer
{
	private TileEntityFertalizer tile;
	
	public GuiFertalizer(EntityPlayer player, TileEntityFertalizer tile)
	{
		super(new ContainerFertalizer(player, tile));
		this.xSize = 176;
		this.ySize = 166;
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(tile.getDisplayName(), 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		int tex = mc.renderEngine.getTexture(Reference.GUI_LOCATION + "fertalizer.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(tex);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        int val = tile.getProgress();
        this.drawTexturedModalRect(x + 61, y + 74, 0, 166, val + 1, 3);
	}
}
