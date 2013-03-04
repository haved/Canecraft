package com.cane.tileentity;

import com.cane.CaneCraft;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCane extends TileEntity
{
	public static final int[] growTime = new int[]{
		12000, 12000, 12000, 14000, 18000, 24000, 16000,
		48000, 48000, 48000, 56000, 72000, 96000, 64000,
		64000};
	
	public int type;//I,C,T,S,G,D,R,CI,CC,CT,CS,CG,CD,CR,S
	public int timeLeft;
	
	public TileEntityCane(int type)
	{
		this.type = type;
		timeLeft = getGrowTime(100);
	}
	
	public void updateEntity()
	{
		if(type < 14)
		{
			grow(1);
		}
	}
	
	public void grow(int val)
	{
		if(canGrow())
		{
			timeLeft -= val;
			
			if(timeLeft <= 0)
			{
				timeLeft = getGrowTime(1000);
				worldObj.setBlockAndMetadataWithNotify(xCoord, yCoord + 1, zCoord,
						CaneCraft.Blocks.cane.blockID, type);
			}
		}
	}
	
	public boolean canGrow()
	{
		if(!worldObj.isAirBlock(xCoord, yCoord+1, zCoord))
		{
			return false;
		}
		
		int val;
		
		for(val = 1; sameBlock(worldObj, xCoord, yCoord, zCoord, xCoord, yCoord-val, zCoord) & val <= 4;val++);
		
		if(val >= 3) return false;
		
		return true;
	}
	
	public static boolean sameBlock(World w, int x1, int y1, int z1,
			int x2, int y2, int z2)
	{
		return w.getBlockId(x1, y1, z1) == w.getBlockId(x2, y2, z2)
		&& w.getBlockMetadata(x1, y1, z1) == w.getBlockMetadata(x2, y2, z2);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		type = tag.getInteger("Type");
		timeLeft = tag.getInteger("TimeLeft");
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("Type", type);
		tag.setInteger("TimeLeft", timeLeft);
	}
	
	public int getGrowTime(int random)
	{
		return growTime[type] + ((int)Math.random() * random);
	}
}
