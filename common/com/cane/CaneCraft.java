package com.cane;

import com.cane.Machine.TYPE;
import com.cane.block.*;
import com.cane.item.*;
import com.cane.tileentity.TileEntityCane;
import com.cane.tileentity.TileEntityExtractor;
import com.cane.tileentity.TileEntityInfuser;
import com.cane.tileentity.TileEntityRecycler;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = {Reference.MOD_ID}, serverSideRequired = false,
clientSideRequired = true, packetHandler = PacketHandler.class)
public class CaneCraft
{
	@Instance(Reference.MOD_ID)
    public static CaneCraft instance;
	@SidedProxy(clientSide = "com.cane.client.ClientProxy",
	serverSide = "com.cane.CommonProxy")
	public static CommonProxy proxy;
	public static CreativeTabCaneCraft tab;
	
	public static class Blocks
	{
		public static BlockCane cane;
		public static BlockMachine machine;
		
		public static void load()
		{
			cane = new BlockCane(Reference.BlockId.CANE);
			GameRegistry.registerBlock(cane, "BlockCane");
			GameRegistry.registerTileEntity(TileEntityCane.class, "canecraft.tile.cane");
			
			machine = new BlockMachine(Reference.BlockId.MACHINE);
			GameRegistry.registerBlock(machine, ItemBlockMachine.class, "BlockMachine");
			
			for(TYPE typ : Machine.TYPE.values())
			{
				LanguageRegistry.instance().addStringLocalization(typ.name() + ".name", "en_US", typ.name);
			}
			GameRegistry.registerTileEntity(TileEntityExtractor.class, "canecraft.tile.extractor");
			GameRegistry.registerTileEntity(TileEntityInfuser.class, "canecraft.tile.infuser");
			GameRegistry.registerTileEntity(TileEntityRecycler.class, "canecraft.tile.recycler");
		}
	}
	public static class Items
	{
		public static ItemCane cane;
		public static ItemCaneUtil caneUtil;
		
		public static void load()
		{
			cane = new ItemCane(Reference.ItemId.CANE);
			for(int i = 0; i < ItemCane.itemNames.length; i++)
			{
				LanguageRegistry.instance().addStringLocalization("cane" + i + ".name",
						"en_US", ItemCane.itemNames[i] + " Canes");
			}
			
			caneUtil = new ItemCaneUtil(Reference.ItemId.CANE_UTIL);
			for(int i = 0; i < ItemCaneUtil.itemNames.length; i++)
			{
				LanguageRegistry.instance().addStringLocalization("caneUtil" + i + ".name",
						"en_US", ItemCaneUtil.itemNames[i]);
			}
		}
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Reference.loadConfig(event.getSuggestedConfigurationFile());
	}
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		tab = new CreativeTabCaneCraft(CreativeTabs.getNextID(), "CaneTab");
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		proxy.registerRenderInformation();
		
		Blocks.load();
		Items.load();
		
		initCrafting();
	}
	
	private void initCrafting()
	{
		addNormalCaneCrafting();
		addMachineCrafting();
	}
	
	private void addPackCrafting(ItemStack output, ItemStack input)
	{
		GameRegistry.addRecipe(output, "III", "IEI", "III",
				Character.valueOf('I'), input, Character.valueOf('E'),
				new ItemStack(Items.caneUtil, 1, 0));
		
	}
	
	private void addPackOreCrafting(ItemStack output, String input)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(output, "III", "IEI", "III",
				Character.valueOf('I'), input, Character.valueOf('E'),
				new ItemStack(Items.caneUtil, 1, 0)));
		
	}
	
	private void addNormalCaneCrafting()
	{
		addPackCrafting(new ItemStack(Items.caneUtil, 1, 1), new ItemStack(Item.ingotIron));
		addPackOreCrafting(new ItemStack(Items.caneUtil, 1, 2), "ingotCopper");
		addPackOreCrafting(new ItemStack(Items.caneUtil, 1, 3), "ingotTin");
		addPackOreCrafting(new ItemStack(Items.caneUtil, 1, 4), "ingotSilver");
		addPackCrafting(new ItemStack(Items.caneUtil, 1, 5), new ItemStack(Item.ingotGold));
		addPackCrafting(new ItemStack(Items.caneUtil, 1, 6), new ItemStack(Item.diamond));
		
		addPackCrafting(new ItemStack(Items.caneUtil, 1, 8), new ItemStack(Block.blockSteel));
		addPackOreCrafting(new ItemStack(Items.caneUtil, 1, 9), "blockCopper");
		addPackOreCrafting(new ItemStack(Items.caneUtil, 1, 10), "blockTin");
		addPackOreCrafting(new ItemStack(Items.caneUtil, 1, 11), "blockSilver");
		addPackCrafting(new ItemStack(Items.caneUtil, 1, 12), new ItemStack(Block.blockGold));
		addPackCrafting(new ItemStack(Items.caneUtil, 1, 13), new ItemStack(Block.blockDiamond));
		
		GameRegistry.addRecipe(new ItemStack(Items.caneUtil, 1, 7), "RDR", "RER", "RDR",
				Character.valueOf('R'), Item.redstone,
				Character.valueOf('D'), Item.diamond,
				Character.valueOf('E'), new ItemStack(Items.caneUtil, 1, 15));
		GameRegistry.addRecipe(new ItemStack(Items.caneUtil, 1, 14), "RDR", "RER", "RRR",
				Character.valueOf('R'), Item.redstone,
				Character.valueOf('D'), Block.blockDiamond,
				Character.valueOf('E'), new ItemStack(Items.caneUtil, 1, 15));
		GameRegistry.addRecipe(new ItemStack(Items.caneUtil, 1, 15), "SDS", "DED", "SDS",
				Character.valueOf('S'), Block.slowSand,
				Character.valueOf('D'), Item.diamond,
				Character.valueOf('E'), new ItemStack(Items.caneUtil, 1, 15));
	}
	
	private void addMachineCrafting()
	{
		GameRegistry.addRecipe(new ItemStack(Blocks.machine, 8, 8), "GDG", "DWD", "SSS",
				'G', Block.glowStone,
				'D', Block.dirt,
				'W', Item.bucketWater,
				'S', Block.stone);
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		ItemCane.initOutputs();
	}
}
