package com.cane;

import com.cane.block.ContainerExtractor;
import com.cane.block.ContainerInfuser;
import com.cane.block.ContainerRecycler;
import com.cane.block.TileEntityExtractor;
import com.cane.block.TileEntityInfuser;
import com.cane.block.TileEntityMachine;
import com.cane.block.TileEntityRecycler;
import com.cane.client.*;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{
	public void registerRenderInformation()
	{
		
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
	int x, int y, int z)
	{
		if(ID == 0)
		{
			return getMachineContainer(player, (TileEntityMachine) world.getBlockTileEntity(x, y, z));
		}
		
		return null;
	}
	
	public Container getMachineContainer(EntityPlayer player, TileEntityMachine tile)
	{
		if(tile == null){return null;}
		
		if(tile instanceof TileEntityExtractor)
		{
			return new ContainerExtractor(player, (TileEntityExtractor)tile);
		}
		else if(tile instanceof TileEntityInfuser)
		{
			return new ContainerInfuser(player, (TileEntityInfuser)tile);
		}
		else if(tile instanceof TileEntityRecycler)
		{
			return new ContainerRecycler(player, (TileEntityRecycler)tile);
		}
		
		return null;
	}
	
	public GuiContainer getMachineGui(EntityPlayer player, TileEntityMachine tile)
	{
		if(tile == null){return null;}
		
		if(tile instanceof TileEntityExtractor)
		{
			return new GuiExtractor(player, (TileEntityExtractor)tile);
		}
		else if(tile instanceof TileEntityInfuser)
		{
			return new GuiInfuser(player, (TileEntityInfuser)tile);
		}
		else if(tile instanceof TileEntityRecycler)
		{
			return new GuiRecycler(player, (TileEntityRecycler)tile);
		}
		
		return null;
	}
}
