package com.cane;

import com.cane.client.*;
import com.cane.inventory.ContainerCaneFertalizer;
import com.cane.inventory.ContainerExtractor;
import com.cane.inventory.ContainerInfuser;
import com.cane.inventory.ContainerRecycler;
import com.cane.tileentity.TileEntityCaneFertalizer;
import com.cane.tileentity.TileEntityExtractor;
import com.cane.tileentity.TileEntityFertalizer;
import com.cane.tileentity.TileEntityInfuser;
import com.cane.tileentity.TileEntityMachine;
import com.cane.tileentity.TileEntityRecycler;

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
		else if(tile instanceof TileEntityCaneFertalizer)
		{
			return new ContainerCaneFertalizer(player, (TileEntityCaneFertalizer)tile);
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
		else if(tile instanceof TileEntityFertalizer)
		{
			return new GuiFertalizer(player, (TileEntityFertalizer)tile);
		}
		
		return null;
	}
}
