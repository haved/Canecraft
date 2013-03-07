package com.cane;

import java.io.File;

import com.cane.item.ItemCane;
import com.cane.tileentity.TileEntityCane;
import com.cane.tileentity.TileEntityCaneFertalizer;
import com.cane.tileentity.TileEntityRecycler;
import com.cane.tileentity.TileEntitySoulFertalizer;

import net.minecraftforge.common.Configuration;

public class Reference
{
	public static final String MOD_ID = "CaneCraft";
	public static final String MOD_NAME = "Cane Craft";
	public static final String VERSION = "0.9.1";
	
	public static final String BLOCK_SPRITE = "/canecraft/texture/blocks.png";
	public static final String ITEM_SPRITE = "/canecraft/texture/items.png";
	
	public static final String GUI_LOCATION = "/canecraft/texture/gui/";
	
	public static final boolean debug = true; //sets it to debug mode
	
	public static class BlockId
	{
		public static final int DEFAULT_CANE = 4000;
		public static final int DEFAULT_MACHINE = 4001;
		
		public static int CANE = DEFAULT_CANE;
		public static int MACHINE = DEFAULT_MACHINE;
	}
	
	public static class ItemId
	{
		public static final int DEFAULT_CANE = 8000;
		public static final int DEFAULT_CANE_UTIL = 8001;
		
		public static int CANE = DEFAULT_CANE;
		public static int CANE_UTIL = DEFAULT_CANE_UTIL;
	}
	
	public static void loadConfig(File f)
	{
		Configuration cfg = new Configuration(f);
		cfg.load();
		
		BlockId.CANE = cfg.getBlock("cane", BlockId.DEFAULT_CANE).getInt();
		BlockId.MACHINE = cfg.getBlock("machine", BlockId.DEFAULT_MACHINE).getInt();
		
		ItemId.CANE = cfg.getItem("cane_item", ItemId.DEFAULT_CANE).getInt();
		ItemId.CANE_UTIL = cfg.getItem("cane_util", ItemId.DEFAULT_CANE_UTIL).getInt();
		
		for(int i = 0; i < TileEntityCane.growTime.length; i++)
		{
			TileEntityCane.growTime[i] =
				cfg.get(Configuration.CATEGORY_GENERAL,
				"GrowTime_" + ItemCane.itemNames[i].replaceAll(" ", "_"),
				TileEntityCane.growTime[i]).getInt();
		}
		
		TileEntityRecycler.chanceForScrap = cfg.get(Configuration.CATEGORY_GENERAL,
				"getSoulFertalizer1OutOf", TileEntityRecycler.chanceForScrap).getInt();
		
		TileEntitySoulFertalizer.SOUL_FERT_AMAOUNT = cfg.get(Configuration.CATEGORY_GENERAL,
				"SoulFertaizerBoostOnCane", TileEntitySoulFertalizer.SOUL_FERT_AMAOUNT).getInt();
		
		TileEntityCaneFertalizer.CANE_FERT_AMAOUNT = cfg.get(Configuration.CATEGORY_GENERAL,
				"CaneFertaizerBoostOnCane", TileEntityCaneFertalizer.CANE_FERT_AMAOUNT).getInt();
		
		cfg.save();
	}
}
