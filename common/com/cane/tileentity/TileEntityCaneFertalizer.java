package com.cane.tileentity;

import com.cane.CaneCraft;

import net.minecraft.item.ItemStack;

public class TileEntityCaneFertalizer extends TileEntityFertalizer
{
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
		System.out.println();
	}

	@Override
	public String getDisplayName()
	{
		return "Cane Fertalizer";
	}

}
