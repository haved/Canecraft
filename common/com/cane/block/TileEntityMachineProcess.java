package com.cane.block;

import com.cane.Reference;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityMachineProcess extends TileEntityMachinePower
{	
	protected ItemStack current;
	protected ItemStack displayCurrent;
	protected int totalTime;
	protected int progress;
	protected int orange = 1;
	
	public void updateEntity()
	{
		super.updateEntity();
		
		if(Reference.debug)
		{
			provider.addEnergy(8);
		}
		
		if(current == null)
		{
			displayCurrent = null;
		}
		
		if(needNewCurrent())
		{
			current = items[0].copy();
			current.stackSize = 1;
			totalTime = getTime(current);
			
			items[0].stackSize--;
			if(items[0].stackSize <= 0)
			{
				items[0] = null;
			}
		}
		
		if(current != null)
		{
			displayCurrent = current.copy();
			
			if(provider.getEnergyStored() >= getPowerUsage())
			{
				provider.subtractEnergy(getPowerUsage());
				progress++;
				
				if(progress >= totalTime)
				{
					progress = 0;
					finish();
					eject();
				}
			}
		}
	}
	
	protected abstract boolean needNewCurrent();
	
	protected abstract void finish();
	
	protected abstract void eject();
	
	protected abstract ItemStack getOutput(ItemStack in);
	
	protected abstract int getTime(ItemStack in);
	
	protected abstract int getPowerUsage();
	
	@Override
	public int getTexture(int side)
	{
		int i = 0;
		
		if(side == 0)i = 16 * 3;
		else if(side == 1)i = 16 * 1;
		else if(side == face)i = (16 * 4) + getFaceTexture() + (current != null ? 16:0);
		else i = 16 * 2;
		
		if(side == orange & side != face) i = i + 1;
		
		return i;
	}
	
	protected abstract int getFaceTexture();

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		this.orange = tag.getShort("orange");
		this.progress = tag.getInteger("progress");
		
		if(tag.hasKey("currentItem"))
		{
			NBTTagCompound item = tag.getCompoundTag("currentItem");
			this.current = ItemStack.loadItemStackFromNBT(item);
		}
		
		this.totalTime = getTime(current);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setShort("orange", (short) orange);
		tag.setInteger("progress", progress);
		
		if(current != null)
		{
			NBTTagCompound item = new NBTTagCompound();
			current.writeToNBT(item);
			tag.setTag("currentItem", item);
		}
	}
	
	public int getProgress()
	{
		if(totalTime <= 0) return 0;
		return (progress * 24) / totalTime;
	}
	
	public int getPower()
	{
		if(provider.getEnergyStored() <= 0)return 0;
		
		return (int) ((provider.getEnergyStored() * 60) / provider.getMaxEnergyStored());
	}
	
	public ItemStack getCurrent()
	{
		return displayCurrent;
	}
}
