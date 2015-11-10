package com.kreezxil.compressedblocks.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import com.kreezxil.compressedblocks.CompressedBlocks;
import com.kreezxil.compressedblocks.ModBlocks;

public class SextupleCompressedGravel  extends Block {

	public SextupleCompressedGravel(String unlocalizedName, Material materialIn, float hardness, float resistance) {
		super(materialIn);
		// TODO Auto-generated constructor stub
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CompressedBlocks.blocksTab);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel("shovel",3);
	}
	
	public SextupleCompressedGravel(String name, float hardness, float resistance) {
		this(name, Material.rock, hardness, resistance);
	}
	
	public SextupleCompressedGravel(String name) {
		this(name, 192f, 960f);
	}

	@Override
	public int quantityDropped(Random par1Random){
		return 9;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random par2Random, int fortune){
		Random rn = new Random();
		int flintChance = rn.nextInt(10);
		
		if(fortune > 0)
			return Item.getItemFromBlock(ModBlocks.QuintupleCompressedGravel);
		else if (flintChance<=1)
			return Item.getItemFromBlock(ModBlocks.QuintupleCompressedFlint);
		else
			return Item.getItemFromBlock(ModBlocks.QuintupleCompressedGravel);
	}


}
