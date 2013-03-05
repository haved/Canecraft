package com.cane.item;

import java.util.List;

import com.cane.CaneCraft;
import com.cane.Machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemBlockMachine extends ItemBlockCC
{
	public ItemBlockMachine(int id)
	{
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
    public String getItemNameIS(ItemStack itemstack)
    {
		if(Machine.TYPE.values().length > itemstack.getItemDamage())
		{
			return Machine.TYPE.values()[itemstack.getItemDamage()].name();
		}
		else
		{
			return "cane.machine.null";
		}
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	public void getSubItems(int val, CreativeTabs tab, List list)
	{
		for(int i = 0; i < Machine.TYPE.values().length; i++)
		{
			list.add(new ItemStack(CaneCraft.Blocks.machine, 1, i));
		}
	}
}
