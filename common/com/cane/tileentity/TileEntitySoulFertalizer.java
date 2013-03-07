package com.cane.tileentity;

import net.minecraft.item.ItemStack;

import com.cane.CaneCraft;

public class TileEntitySoulFertalizer extends TileEntityFertalizer
{
	public static int SOUL_FERT_AMAOUNT = 4000;
	
	public TileEntitySoulFertalizer()
	{
		items = new ItemStack[9];
		
		itemNeeded = new ItemStack(CaneCraft.Items.caneUtil, 1, 16);
		totalTime = 200;
	}
		
	@Override
	public String getInvName()
	{
		return "container.soulfertalizer";
	}

	@Override
	public void fertalize()
	{
		int y = yCoord + 1;
		
		while(true)
		{
			if(!isSoulCane(xCoord, y + 1, zCoord))
			{
				break;
			}
			
			y++;
		}
		
		if(isSoulCane(xCoord, y, zCoord))
		{
			((TileEntityCane)worldObj.getBlockTileEntity(xCoord, y, zCoord))
			.grow(SOUL_FERT_AMAOUNT);
		}
	}
	
	public boolean isSoulCane(int x, int y, int z)
	{
		if(worldObj.getBlockTileEntity(x, y, z) instanceof TileEntityCane &&
				worldObj.getBlockMetadata(x, y, z) == 14)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public String getDisplayName()
	{
		return "Soul Fertalizer";
	}

	@Override
	public int getFaceTexture()
	{
		return 4;
	}
}
