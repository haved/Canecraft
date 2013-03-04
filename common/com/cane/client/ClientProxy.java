package com.cane.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import com.cane.CommonProxy;
import com.cane.Reference;
import com.cane.tileentity.TileEntityMachine;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderInformation()
	{
		MinecraftForgeClient.preloadTexture(Reference.BLOCK_SPRITE);
		MinecraftForgeClient.preloadTexture(Reference.ITEM_SPRITE);
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		if(ID == 0)
		{
			return getMachineGui(player, (TileEntityMachine) world.getBlockTileEntity(x, y, z));
		}
		
		return null;
	}
}
