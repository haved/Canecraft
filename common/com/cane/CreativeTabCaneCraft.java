package com.cane;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabCaneCraft extends CreativeTabs
{
	public CreativeTabCaneCraft(int value, String name)
	{
		super(value, name);
	}
	
	@Override
	public int getTabIconItemIndex()
	{
		return Item.reed.itemID;
	}
	
	static{
		LanguageRegistry.instance().addStringLocalization("itemGroup.CaneTab", "en_US", "CaneCraft");
	}
}
