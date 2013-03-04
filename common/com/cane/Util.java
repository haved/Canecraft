package com.cane;

import buildcraft.api.transport.IPipeEntry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class Util
{
	public static final int[][] SIDE_COORD_MOD = { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };
	
	public static int[] getAdjacentCoordinatesForSide(int x, int y, int z, int side)
	{
	    return new int[] { x + SIDE_COORD_MOD[side][0], y + SIDE_COORD_MOD[side][1], z + SIDE_COORD_MOD[side][2] };
	}

	public static boolean sendToPipe(TileEntity tile, int from, ItemStack stack)
	{
		int c[] = getAdjacentCoordinatesForSide(tile.xCoord, tile.yCoord, tile.zCoord, from);
		
		TileEntity tilePipe = tile.worldObj.getBlockTileEntity(c[0], c[1], c[2]);
		if(tilePipe instanceof IPipeEntry && ((IPipeEntry) tilePipe).acceptItems())
		{
			((IPipeEntry)tilePipe).entityEntering(stack, ForgeDirection.VALID_DIRECTIONS[from]);
			return true;
		}
		
		return false;
	}
	
	public static boolean isAdjacentInventory(TileEntity tile, int from)
	{
	    int[] coords = getAdjacentCoordinatesForSide(tile.xCoord, tile.yCoord, tile.zCoord, from);
	    TileEntity tileInventory = tile.worldObj.getBlockTileEntity(coords[0], coords[1], coords[2]);

	    if (((tileInventory instanceof ISidedInventory)) && (((ISidedInventory)tileInventory).getSizeInventorySide(ForgeDirection.getOrientation(from).getOpposite()) <= 0)) {
	    	return false;
	    }
	    if (((tileInventory instanceof IInventory)) && (((IInventory)tileInventory).getSizeInventory() > 0)) {
	    	return true;
	    }
	    return false;
	}

	public static int addToAdjInventory(TileEntity tile, int from, ItemStack theStack)
	{
		int[] c = getAdjacentCoordinatesForSide(tile.xCoord, tile.yCoord, tile.zCoord, from);
		TileEntity tileInv = tile.worldObj.getBlockTileEntity(c[0], c[1], c[2]);
		
		if ((tileInv instanceof ISidedInventory))
		{
	      ISidedInventory sidedInv = (ISidedInventory)tileInv;

	      int first = sidedInv.getStartInventorySide(ForgeDirection.getOrientation(from).getOpposite());
	      int last = first + sidedInv.getSizeInventorySide(ForgeDirection.getOrientation(from).getOpposite());

	      if (last - first < 128) {
	        for (int i = first; i < last; i++) {
	          if ((sidedInv.getStackInSlot(i) != null) && (sidedInv.getStackInSlot(i).isItemEqual(theStack)))
	          {
	            theStack = addToInventorySlot(sidedInv, i, theStack);
	          }
	          if (theStack == null) {
	            return 0;
	          }
	        }
	      }
	      for (int i = first; i < last; i++) {
	        theStack = addToInventorySlot(sidedInv, i, theStack);
	        if (theStack == null)
	          return 0;
	      }
	    }
	    else if ((tileInv instanceof IInventory)) {
	      IInventory inv = (IInventory)tileInv;

	      if (inv.getSizeInventory() < 128) {
	        for (int i = 0; i < inv.getSizeInventory(); i++) {
	          if ((inv.getStackInSlot(i) != null) && (inv.getStackInSlot(i).isItemEqual(theStack)))
	          {
	            theStack = addToInventorySlot(inv, i, theStack);
	          }
	          if (theStack == null) {
	            return 0;
	          }
	        }
	      }
	      for (int i = 0; i < inv.getSizeInventory(); i++)
	      {
	        theStack = addToInventorySlot(inv, i, theStack);
	        if (theStack == null) {
	          return 0;
	        }
	      }
	    }
		return theStack.stackSize;
	}
	
	public static ItemStack addToInventorySlot(IInventory theInventory, int theSlot, ItemStack theStack)
	{
		ItemStack aStack = theInventory.getStackInSlot(theSlot);

		if (aStack == null)
		{
			aStack = theStack.copy();
			theInventory.setInventorySlotContents(theSlot, aStack);
			return null;
		}
		if (aStack.isItemEqual(theStack))
		{
			if (theStack.stackSize + aStack.stackSize > aStack.getMaxStackSize())
			{
				int stackDiff = aStack.getMaxStackSize() - aStack.stackSize;
				aStack.stackSize = aStack.getMaxStackSize();
				theStack.stackSize -= stackDiff;
				theInventory.setInventorySlotContents(theSlot, aStack);
				return theStack;
			}
			aStack.stackSize += theStack.stackSize;
			theInventory.setInventorySlotContents(theSlot, aStack);
			return null;
		}
		return theStack;
	}
}
