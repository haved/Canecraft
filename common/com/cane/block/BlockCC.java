package com.cane.block;

import com.cane.CaneCraft;
import com.cane.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCC extends Block
{
	public BlockCC(int id, Material material)
	{
		super(id, material);
		setCreativeTab(CaneCraft.tab);
	}
	
	@Override
	public String getTextureFile ()
	{
		return Reference.BLOCK_SPRITE;
	}
}
