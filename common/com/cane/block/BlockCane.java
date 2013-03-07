package com.cane.block;

import java.util.Random;

import com.cane.CaneCraft;
import com.cane.tileentity.TileEntityCane;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockCane extends BlockCC implements IPlantable
{
	public BlockCane(int id)
	{
		super(id, Material.plants);
		this.setBlockName("Cane_Block");
		float var3 = 0.375F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
		this.setCreativeTab(null);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.disableStats();
	}
	
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityCane(metadata);
	}
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public int getRenderType()
    {
        return 1;
    }
    @Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata)
    {
    	return metadata;
    }
      
    @Override
    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        world.markBlockForUpdate(i, j, k);
    }
    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return this.canPlaceBlockAt(par1World, par2, par3, par4);
    }
    @Override
    public boolean canPlaceBlockAt(World w, int x, int y, int z)
	{
		return canPlaceBlockAtMetadata(w, x, y, z, w.getBlockMetadata(x, y, z));
	}
    public boolean canPlaceBlockAtMetadata(World w, int x, int y, int z, int metadata)
	{	
		if(w.getBlockId(x, y-1, z) == blockID
				&& w.getBlockMetadata(x, y-1, z) == metadata)
		{
			return true;
		}
		
		int blockIdUnder = w.getBlockId(x, y-1, z);
		int blockDataUnder = w.getBlockMetadata(x, y-1, z);
		
		if(metadata < 14)
		{
			if(Block.blocksList[blockIdUnder] != null)
			{
				return Block.blocksList[blockIdUnder].canSustainPlant(w, x, y - 1, z, ForgeDirection.UP, this);
			}
			else
			{
				return false;
			}
		}
		
		return metadata == 14 &
		blockIdUnder == CaneCraft.Blocks.machine.blockID &
		blockDataUnder == 5;
	}
    protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
    }
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        this.checkBlockCoordValid(par1World, par2, par3, par4);
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
    	return CaneCraft.Items.cane.itemID;
    }
    @Override
	public int damageDropped (int metadata)
    {
		return metadata;
	}
    public int idPicked(World w, int par1, int par2, int par3)
    {
    	return CaneCraft.Items.cane.itemID;
    }
     
	@Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return EnumPlantType.Beach;
    }
    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockID;
    }
    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
}
