package com.cane.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class ItemCaneUtil extends ItemCC
{
	public static String[] itemNames = new String[]{"Empty Canes", "Iron Cane Pack",
		"Copper Cane Pack", "Tin Cane Pack", "Silver Cane Pack", "Gold Cane Pack",
		"Diamond Cane Pack", "Restone Cane Pack", " Compressed Iron Cane Pack",
		"Compressed Copper Cane Pack", "Compressed Tin Cane Pack",
		"Compressed Silver Cane Pack", "Compressed Gold Cane Pack",
		"Compressed Diamond Cane Pack", "Compressed Redstone Cane Pack",
		"Soul Cane Pack", "Soul Fertalizer", "Cane Fertalizer", "Cane Fuel Cell",
		"Copmressed Cane Fuel Cell"};
	
	public ItemCaneUtil(int id)
	{
		super(id);
		this.iconIndex = 16;
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getIconFromDamage(int damage)
	{
		return iconIndex + damage;
	}
	
	@Override
	public String getItemNameIS(ItemStack itemStack)
	{
		return "caneUtil"+itemStack.getItemDamage();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int val, CreativeTabs tab, List list)
	{
		for(int i = 0; i < itemNames.length; i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}
}
