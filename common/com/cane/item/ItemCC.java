package com.cane.item;

import com.cane.CaneCraft;
import com.cane.Reference;

import net.minecraft.item.Item;

public class ItemCC extends Item
{
	public ItemCC(int id)
	{
		super(id);
		this.setCreativeTab(CaneCraft.tab);
	}
	
	@Override
	public String getTextureFile()
	{
		return Reference.ITEM_SPRITE;
	}
}
