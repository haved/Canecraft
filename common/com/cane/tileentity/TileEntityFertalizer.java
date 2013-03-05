package com.cane.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public abstract class TileEntityFertalizer extends TileEntityMachine
{
	public int work;
	public int totalTime;
	
	public ItemStack itemNeeded;
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		this.work = tag.getInteger("work");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setInteger("work", this.work);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if(work > 0)
		{
			work--;
			
			if(work <= 0)
			{
				fertalize();
				getNewItems();
			}
		}
		else
		{
			getNewItems();
		}
	}
	
	public void getNewItems()
	{
		for(int i = 0; i < items.length; i++)
		{
			if(itemExist(items[i]) && items[i].isItemEqual(itemNeeded))
			{
				decrStackSize(i, 1);
				work = totalTime;
			}
		}
	}
	
	public int getProgress()
	{
		return (work * 24 / totalTime);
	}
	
	public abstract void fertalize();
}
