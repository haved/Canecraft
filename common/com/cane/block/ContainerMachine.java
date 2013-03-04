package com.cane.block;

import com.cane.tileentity.TileEntityMachine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerMachine extends Container
{
	@Override
	public boolean canInteractWith(EntityPlayer e)
	{
		return getTile().canInteractWith(e);
	}
	
	protected ItemStack moveTo(ItemStack is, Slot s)
	{
		ItemStack is2 = s.getStack();
		
		if(is == null)
		{
			is = is2.copy();
			is.stackSize = 0;
		}
		
		int q = is.stackSize + is2.stackSize;
		
		if(q > is.getMaxStackSize())
		{
			int qLeft = q - is.getMaxStackSize();
			
			is = is.copy();
			is.stackSize = qLeft;
			return is;
		}
		else
		{
			
			return null;
		}
	}
	
	protected ItemStack moveIntoStack(ItemStack is, ItemStack is2)
	{
		ItemStack svar;
		int q = is.stackSize + is2.stackSize;
		
		if(q > is.getMaxStackSize())
		{
			svar = is.copy();
			svar.stackSize = q - is.getMaxStackSize();
		}
		else
		{
			svar = null;
		}
		
		return svar;
	}
	
	protected void addPlayerSlots(InventoryPlayer inv)
	{
		for (int var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(inv, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (int var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(inv, var3, 8 + var3 * 18, 142));
        }
	}
	
	@Override
    public void onCraftGuiClosed(EntityPlayer entityplayer)
    {
        super.onCraftGuiClosed(entityplayer);
        getTile().closeChest();
    }
	
	public abstract TileEntityMachine getTile();
}
