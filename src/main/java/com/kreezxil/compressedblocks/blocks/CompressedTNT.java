package com.kreezxil.compressedblocks.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.kreezxil.compressedblocks.CompressedBlocks;

public class CompressedTNT  extends Block {

	public CompressedTNT(String unlocalizedName, Material materialIn, float hardness, float resistance) {
		super(materialIn);
		// TODO Auto-generated constructor stub
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CompressedBlocks.blocksTab);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}
	
	public CompressedTNT(String name, float hardness, float resistance) {
		this(name, Material.rock, hardness, resistance);
	}
	
	public CompressedTNT(String name) {
		this(name, 2f, 10f);
	}

	public int quantityDropped(Random par1Random){
		return 9;
		}
	public Item getItemDropped(IBlockState state, Random par2Random, int par3){
		return Item.getItemFromBlock(Blocks.tnt);
	}


}
