package com.cane.tileentity;

import com.cane.PowerProviderCC;

import net.minecraft.item.Item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class TileEntityHarvester extends TileEntityMachinePower
{
	int current;
	
	public TileEntityHarvester()
	{
		items = new ItemStack[1];
		provider = new PowerProviderCC(1, 20);
	}
	
	public void updateEntity()
	{
		if(provider.getEnergyStored() <= 0)return;
		
		current++;
		
		if(current >= 15*15)
		{
			current = 0;
		}
		else if(current == (15 * 15) / 2)
		{
			current ++;
		}
		
		tryHarvest(xCoord - 7 + (current / 15), yCoord, zCoord - 7 + (current % 15));
		
		provider.subtractEnergy(1);
	}
	
	public void tryHarvest(int x, int y, int z)
	{
		int yPos = y;
		
		while(true)
		{
			if(!canHarvest(x, yPos + 1, z))
			{
				break;
			}
			
			y++;
		}
		
		if(canHarvest(x, yPos, z))
		{
			items[0] = new ItemStack(Item.itemsList[
					Block.blocksList[worldObj.getBlockId(x, yPos, z)].
					idDropped(0, null, 0)]);
		}
	}
	
	public boolean canHarvest(int x, int y, int z)
	{
		return worldObj.getBlockTileEntity(x, y, z) instanceof TileEntityCane |
				worldObj.getBlockId(x, y, z) == Block.reed.blockID;
	}
	
	@Override
	public String getInvName()
	{
		return "container.caneharvester";
	}

	@Override
	public String getDisplayName() 
	{
		return null; //No gui
	}
}
