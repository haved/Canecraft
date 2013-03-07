package com.cane.tileentity;

import com.cane.CaneCraft;

import net.minecraft.item.ItemStack;

public class TileEntityCaneFertalizer extends TileEntityFertalizer
{
	public static int CANE_FERT_AMAOUNT = 8000;
	
	public TileEntityCaneFertalizer()
	{
		items = new ItemStack[9];
		
		itemNeeded = new ItemStack(CaneCraft.Items.caneUtil, 1, 17);
		totalTime = 100;
	}
		
	@Override
	public String getInvName()
	{
		return "container.canefertalizer";
	}

	@Override
	public void fertalize()
	{
		for(int i = -2; i < 3; i++)
		{
			for(int j = -2; j < 3; j++)
			{
				fertalizeCane(xCoord + i, yCoord, zCoord + j);
			}
		}
	}
	
	private boolean isGrowableCane(int x, int y, int z)
	{
		return worldObj.getBlockTileEntity(x, y, z) instanceof TileEntityCane &&
				worldObj.getBlockMetadata(x, y, z) < 14;
	}
	
	private void fertalizeCane(int x, int y, int z)
	{
		while(true)
		{
			if(!isGrowableCane(x, y + 1, z))
			{
				growCane(x, y, z);
				break;
			}
			
			y++;
		}
	}
	
	private void growCane(int x, int y, int z)
	{
		isGrowableCane(x, y, z);
		((TileEntityCane) worldObj.getBlockTileEntity(x, y, z)).grow(CANE_FERT_AMAOUNT);
	}
	
	@Override
	public String getDisplayName()
	{
		return "Cane Fertalizer";
	}

	@Override
	public int getFaceTexture()
	{
		return 4;
	}
}
