package com.cane.item;

import com.cane.CaneCraft;

import net.minecraft.item.ItemBlock;

public class ItemBlockCC extends ItemBlock
{
	public ItemBlockCC(int id)
	{
		super(id);
		this.setHasSubtypes(true);
		this.setCreativeTab(CaneCraft.tab);
	}
	
	@Override
	public int getMetadata (int damageValue)
	{
		return damageValue;
	}
}
