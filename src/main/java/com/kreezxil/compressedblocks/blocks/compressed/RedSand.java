package com.kreezxil.compressedblocks.blocks.compressed;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.kreezxil.compressedblocks.CompressedBlocks;
import com.kreezxil.compressedblocks.ModBlocks;
import com.kreezxil.compressedblocks.blocks.compressed.Enums.EightTiers;
import com.kreezxil.compressedblocks.itemBlocks.IMetaBlockName;

public class RedSand extends Block implements IMetaBlockName {

	public static final String harvestTool = "shovel";
	public static final int MAXTIER = 8;
	public static final String UnlocalizedName = "CompressedRedSand";
	public static final float baseHardness = 4f;
	public static final float hardnessFactor = 1.07f;
	public static final int WOOD = 0;
	public static final int STONE = 1;
	public static final int IRON = 2;
	public static final int DIAMOND = 3;
	public static final int[] harvestLevel = { WOOD, WOOD, WOOD, WOOD, STONE,
			STONE, STONE, STONE };
	public static String[] tiers = { "", "Double", "Triple", "Quadruple",
			"Quintuple", "Sextuple", "Septuple", "Octuple" };

	public static final PropertyEnum TIER = PropertyEnum.create("tier",
			EightTiers.class);

	public RedSand() {
		super(Material.sand);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TIER,
				EightTiers.COMPRESSED));
		this.setUnlocalizedName(UnlocalizedName);
		this.setCreativeTab(CompressedBlocks.blocksTab);
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		EightTiers stateTier = (EightTiers) state.getValue(TIER);
		int tier = stateTier.getID();
		if (tier > 0 && tier < MAXTIER) {
			return baseHardness * tier * hardnessFactor * tier;
		}
		return baseHardness;
	}

	@Override
	public float getExplosionResistance(Entity exploder) {
		return this.blockHardness * 0.2F * 3 / 5.0F;
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return harvestTool;
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		EightTiers stateTier = (EightTiers) state.getValue(TIER);
		int tier = stateTier.getID();
		if (tier >= 0 && tier < MAXTIER) {
			return harvestLevel[tier];
		} else {
			return harvestLevel[0];
		}
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { TIER });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EightTiers tier;
		switch (meta) {
		case 1:
			tier = EightTiers.DOUBLE;
			break;
		case 2:
			tier = EightTiers.TRIPLE;
			break;
		case 3:
			tier = EightTiers.QUADRUPLE;
			break;
		case 4:
			tier = EightTiers.QUINTUPLE;
			break;
		case 5:
			tier = EightTiers.SEXTUPLE;
			break;
		case 6:
			tier = EightTiers.SEPTUPLE;
			break;
		case 7:
			tier = EightTiers.OCTUPLE;
			break;
		default: // 0 and undefined
			tier = EightTiers.COMPRESSED;
		}
		return getDefaultState().withProperty(TIER, tier);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EightTiers tier = (EightTiers) state.getValue(TIER);
		return tier.getID();
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 9;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random par2Random, int par3) {
		EightTiers stateTier = (EightTiers) state.getValue(TIER);
		int tier = stateTier.getID();
		if (tier > 0 && tier < MAXTIER) {
			// essentially it's a validation check
			return Item.getItemFromBlock(ModBlocks.CompressedRedSand);
		}
		return Item.getItemFromBlock(Blocks.sand);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for (int i = 0; i < MAXTIER; i++) {
			list.add(new ItemStack(itemIn, 1, i));
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		EightTiers stateTier = (EightTiers) state.getValue(TIER);
		int tier = stateTier.getID();
		if (tier != 0 && tier < MAXTIER)
			return tier - 1;
		else
			return 1; //this is for when the sand drops, and in this case I want red sand, so 1 is the meta for that
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world,
			BlockPos pos) {
		return new ItemStack(Item.getItemFromBlock(this), 1,
				this.getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return tiers[stack.getItemDamage()];
	}
}