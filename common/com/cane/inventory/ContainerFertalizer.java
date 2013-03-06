package com.cane.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import com.cane.tileentity.TileEntityFertalizer;
import com.cane.tileentity.TileEntityMachine;

public class ContainerFertalizer extends ContainerMachine
{
	private TileEntityFertalizer tile;
	private EntityPlayer player;
	
	public ContainerFertalizer(EntityPlayer player, TileEntityFertalizer tile)
	{
		this.player = player;
		this.tile = tile;
		
		addPlayerSlots(this.player.inventory);
		addSlots();
	}
	
	public void addSlots()
	{
		for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                this.addSlotToContainer(
                new Slot(this.tile, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }
	}
	
	@Override
	public TileEntityMachine getTile()
	{
		return tile;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		return null;
	}
}
