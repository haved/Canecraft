package com.cane;

import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerProvider;

public class PowerProviderCC extends PowerProvider
{
	public PowerProviderCC(int maxEnergyRecieved, int maxStoredEnergy)
	{
		configure(maxEnergyRecieved, maxStoredEnergy);
	}
	
	public void configure(int maxEnergyReceived, int maxStoredEnergy)
	{
		super.configure(0, 0, maxEnergyReceived, 0, maxStoredEnergy);
	}

	public boolean update(IPowerReceptor receptor)
	{
		return false;
	}

	public void receiveEnergy(float quantity, ForgeDirection from)
	{
		addEnergy(quantity);
	}

	public void addEnergy(float quantity)
	{
		this.energyStored += quantity;

		if (this.energyStored > this.maxEnergyStored)
			this.energyStored = this.maxEnergyStored;
	}

	public void subtractEnergy(float quantity)
	{
		this.energyStored -= quantity;

		if (this.energyStored < 0.0F)
			this.energyStored = 0.0F;
	}

	public void setEnergyStored(float quantity)
	{
		this.energyStored = quantity;

		if (this.energyStored > this.maxEnergyStored)
			this.energyStored = this.maxEnergyStored;
		else if (this.energyStored < 0.0F)
			this.energyStored = 0.0F;
	}

	public void roundEnergyStored()
	{
		this.energyStored = Math.round(this.energyStored);
	}

	public int powerReqest()
	{
		int powerNeed = (int) (getMaxEnergyStored() - getEnergyStored());
		
		return Math.min(powerNeed, getMaxEnergyReceived());
	}
}
