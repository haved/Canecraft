package com.cane;

import com.cane.block.TileEntityExtractor;
import com.cane.block.TileEntityInfuser;
import com.cane.block.TileEntityMachine;
import com.cane.block.TileEntityRecycler;

public class Machine
{
	public static enum TYPE
	{
		EXTRACTOR_1("Tier 1 Extractor", TileEntityExtractor.class),
		EXTRACTOR_2("Tier 2 Extractor", TileEntityExtractor.class),
		INFUSER("Cane Infuser", TileEntityInfuser.class),
		RECYCLER("Recycler", TileEntityRecycler.class),
		FERTALIZER("Cane Fertalizer"),
		SOUL_GROWER("Soul Grower"),
		CANE_HARVESTER("Cane Harvester"),
		CANE_HOLDER("Cane Holder");
			
		public String name;
		public Class<? extends TileEntityMachine> clazz;
		
		TYPE(String name)
		{
			this.name = name;
		}
		
		TYPE(String name, Class<? extends TileEntityMachine> clazz)
		{
			this.name = name;
			this.clazz = clazz;
		}
	}
	
	public static TileEntityMachine getTileEntity(int i)
	{
		TileEntityMachine svar = null;
		
		try
		{
			svar = TYPE.values()[i].clazz.newInstance();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		if(i == TYPE.EXTRACTOR_2.ordinal())
		{
			((TileEntityExtractor) svar).setTier2(true);
		}
		
		return svar;
	}
	
	public static boolean hasClass(int i)
	{
		return TYPE.values()[i].clazz != null;
	}
}