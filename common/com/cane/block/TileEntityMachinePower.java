package com.cane.block;

import net.minecraft.nbt.NBTTagCompound;

import com.cane.PowerProviderCC;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;

public abstract class TileEntityMachinePower extends TileEntityMachine implements IPowerReceptor
{
	public PowerProviderCC provider;
	
	@Override
	public void doWork()
	{
		
	}

	@Override
	public IPowerProvider getPowerProvider()
	{
		return provider;
	}

	@Override
	public int powerRequest()
	{
		return provider.powerReqest();
	}

	@Override
	public void setPowerProvider(IPowerProvider provider)
	{
		this.provider = (PowerProviderCC) provider;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setFloat("energy", provider.getEnergyStored());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		provider.setEnergyStored(tag.getFloat("energy"));
	}
}
