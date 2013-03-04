package com.cane.client;

import org.lwjgl.opengl.GL11;

import com.cane.Reference;
import com.cane.block.ContainerInfuser;
import com.cane.block.TileEntityInfuser;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiInfuser extends GuiContainer
{
	private TileEntityInfuser tile;
	
	public GuiInfuser(EntityPlayer player, TileEntityInfuser tile)
	{
		super(new ContainerInfuser(player, tile));
		xSize = 176;
		ySize = 166;
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(tile.getDisplayName(), 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        drawCurrent();
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		int tex = mc.renderEngine.getTexture(Reference.GUI_LOCATION + "infuser.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(tex);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        int var7 = tile.getProgress();
        this.drawTexturedModalRect(x + 79, y + 34, 176, 0, var7 + 1, 16);
        
        var7 = tile.getPower();
        this.drawTexturedModalRect(x + 8, y + 8 + (60 - var7), 176, 17 + (60 - var7), 16, var7);
	}
	
	private void drawCurrent()
	{
		if(tile.getCurrent() != null && tile.getCurrent().stackSize > 0)
		{
			drawCurrent(tile.getCurrent(), 83, 18);
		}
	}
	
	private void drawCurrent(ItemStack is, int x, int y)
	{
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRenderer.zLevel = 200.0F;
        itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, is, x, y);
        itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, is, x, y);
        this.zLevel = 0.0F;
        itemRenderer.zLevel = 0.0F;
	}
}