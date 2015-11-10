package com.kreezxil.compressedblocks.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import com.kreezxil.compressedblocks.CompressedBlocks;
import com.kreezxil.compressedblocks.ModBlocks;

public class SeptupleCompressedGravel  extends Block {

	public SeptupleCompressedGravel(String unlocalizedName, Material materialIn, float hardness, float resistance) {
		super(materialIn);
		// TODO Auto-generated constructor stub
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CompressedBlocks.blocksTab);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel("shovel",3);
	}
	
	public SeptupleCompressedGravel(String name, float hardness, float resistance) {
		this(name, Material.rock, hardness, resistance);
	}
	
	public SeptupleCompressedGravel(String name) {
		this(name, 384f, 1920f);
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
			return Item.getItemFromBlock(ModBlocks.SextupleCompressedGravel);
		else if (flintChance<=1)
			return Item.getItemFromBlock(ModBlocks.SextupleCompressedFlint);
		else
			return Item.getItemFromBlock(ModBlocks.SextupleCompressedGravel);
	}

}
