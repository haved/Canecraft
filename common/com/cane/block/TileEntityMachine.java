package com.cane.block;

import com.cane.Util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityMachine extends TileEntity implements IInventory
{
	public ItemStack[] items;
	public int face;
	
	public boolean ejectItem(int slot, int q, int side)
	{
		if(slot < 0 || slot >= getSizeInventory()) return false;
		
		ItemStack send = getStackInSlot(slot).copy();
		send.stackSize = Math.min(q, send.stackSize);
		
		if(Util.sendToPipe(this, side, send))
		{
			items[slot].stackSize =- send.stackSize;
			if(items[slot].stackSize <= 0)
			{
				items[slot] = null;
			}
			return true;
		}
		
		if (Util.isAdjacentInventory(this, side))
		{
		      this.items[slot].stackSize -= q - Util.addToAdjInventory(this, side, send);
		      if (this.items[slot].stackSize <= 0) {
		        this.items[slot] = null;
		      }
		      return true;
		    }
		
		
		
		return false;
	}
	
	public void setFace(int face)
	{
		this.face = face;
	}
	
	public int getFace()
	{
		return this.face;
	}
	
	public int getTexture(int side)
	{
		return 255;
	}
	
	public void closeChest()
	{
		
	}
	@Override
	public ItemStack decrStackSize(int slot, int q)
	{
		if (this.items[slot] != null)
        {
            ItemStack svar;

            if (this.items[slot].stackSize <= q)
            {
                svar = this.items[slot];
                this.items[slot] = null;
                return svar;
            }
            else
            {
                svar = this.items[slot].splitStack(q);

                if (this.items[slot].stackSize == 0)
                {
                    this.items[slot] = null;
                }

                return svar;
            }
        }
        else
        {
            return null;
        }
	}
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	@Override
	public int getSizeInventory()
	{
		return items.length;
	}
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return items[slot];
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.items[slot] != null)
        {
            ItemStack var2 = this.items[slot];
            this.items[slot] = null;
            return var2;
        }
        else
        {
            return null;
        }
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }
	@Override
	public void openChest()
	{
		
	}
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
    {
        this.items[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
           stack.stackSize = this.getInventoryStackLimit();
        }
    }

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
        NBTTagList list = tag.getTagList("Items");

        for (int var3 = 0; var3 < list.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)list.tagAt(var3);
            byte slot = var4.getByte("Slot");

            if (slot >= 0 && slot < this.items.length)
            {
                this.items[slot] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.face = tag.getShort("face");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList list = new NBTTagList();

		for (int item = 0; item < this.items.length; ++item)
        {
            if (this.items[item] != null)
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte)item);
                this.items[item].writeToNBT(itemTag);
                list.appendTag(itemTag);
            }
        }

        tag.setTag("Items", list);

        tag.setShort("face", (short) face);
	}
	
	public abstract String getDisplayName();
	
	public boolean canInteractWith(EntityPlayer e)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ?
		false : e.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	public static boolean itemExist(ItemStack is)
	{
		return is != null && is.stackSize > 0;
	}
	
	public static int overSpace(ItemStack is, int newItems)
	{
		if(is == null)
		{
			return -1;
		}
		int q = is.stackSize + newItems;
		return q - is.getMaxStackSize();
	}
}
