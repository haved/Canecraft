package com.cane.inventory;

import com.cane.tileentity.TileEntityMachine;
import com.cane.tileentity.TileEntityRecycler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerRecycler extends ContainerMachine
{
	private TileEntityRecycler tile;
	private EntityPlayer player;
	
	public ContainerRecycler(EntityPlayer player, TileEntityRecycler tile)
	{
		this.tile = tile;
		this.player = player;
		
		addPlayerSlots(this.player.inventory);
		addSlots();
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		return null;
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
