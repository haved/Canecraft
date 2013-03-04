package com.cane.block;

import com.cane.tileentity.TileEntityInfuser;
import com.cane.tileentity.TileEntityMachine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;


public class ContainerInfuser extends ContainerMachine
{
	public EntityPlayer player;
	public TileEntityInfuser tile;
	
	public ContainerInfuser(EntityPlayer player, TileEntityInfuser tile)
	{
		this.player = player;
		this.tile = tile;
		
		addPlayerSlots(player.inventory);
		addSlots();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack svar = null;
//        Slot slot = (Slot)this.inventorySlots.get(par2);
//        
//        if(slot != null && slot.getHasStack())
//        {
//        	if(slot.inventory == player.inventory)
//        	{
//        		if(tile.items == null || slot.getStack().isItemEqual(tile.items[0]))
//        		{
//        			moveTo(slot.getStack(), getSlotFromInventory(tile, 0));
//        		}
//        	}
//        	else if(slot.inventory == tile)
//        	{
//        		for(int i = 0; i < player.inventory.getSizeInventory(); i++)
//        		{
//        			ItemStack s = player.inventory.getStackInSlot(i);
//        			
//        			if(s != null && s.isItemEqual(slot.getStack()))
//        			{
//        				return moveIntoStack(slot.getStack(), s);
//        			}
//        		}
//        	}
//        }
        
        return svar;
    }
	
	private void addSlots()
	{
		this.addSlotToContainer(new Slot(tile, 0, 56, 35));
		this.addSlotToContainer(new SlotFurnace(player, tile, 1, 116, 35));
	}
	
    @Override
	public TileEntityMachine getTile()
	{
		return tile;
	}
}
