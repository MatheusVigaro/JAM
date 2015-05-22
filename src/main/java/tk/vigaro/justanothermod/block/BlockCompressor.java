package tk.vigaro.justanothermod.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tk.vigaro.justanothermod.References;
import tk.vigaro.justanothermod.tileentity.TileEntityCompressor;
import cofh.api.block.IDismantleable;
import cofh.lib.util.helpers.BlockHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressor extends BlockContainer implements IDismantleable{
	public IIcon[] icons = new IIcon[2];
	
	public BlockCompressor() {
		super(Material.iron);
		this.setCreativeTab(References.creativeTab);
		this.setBlockTextureName(References.ID + ":" + BlockInfo.COMPRESSOR_UNLOCALIZED_NAME);
		
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack){
		if (!world.isRemote){
			ForgeDirection dir;
			int facing = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			switch(facing){
			default:
			case 0:
				dir = ForgeDirection.SOUTH;
				break;
			case 1:
				dir = ForgeDirection.WEST;
				break;
			case 2:
				dir = ForgeDirection.NORTH;
				break;
			case 3:
				dir = ForgeDirection.EAST;
					
			}
			world.setBlockMetadataWithNotify(x, y, z, dir.ordinal(), 3);
			((TileEntityCompressor)world.getTileEntity(x, y, z)).setOrientation(dir);

		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg){
		this.icons[0] = reg.registerIcon(this.textureName + "_" + 0);
		this.icons[1] = reg.registerIcon(this.textureName + "_" + 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		if (meta == 0){
			return side == 3 ? this.icons[1] : this.icons[0];
		}
		return side == meta ? this.icons[1] : this.icons[0];
	}

	@Override
	public boolean canDismantle(EntityPlayer arg0, World arg1, int arg2, int arg3, int arg4) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> dismantleBlock(EntityPlayer arg0, World world, int x, int y, int z, boolean arg5) {
		ArrayList<ItemStack> dropped = new ArrayList();
		dropped.add(new ItemStack(JAMBlocks.compressor));
		world.setBlockToAir(x, y, z);

		float f = 0.3F;

		double dX = References.random.nextFloat() * f + (1.0F - f) * 0.5D; 
		double dY = References.random.nextFloat() * f + (1.0F - f) * 0.5D;
		double dZ = References.random.nextFloat() * f + (1.0F - f) * 0.5D;

		world.spawnEntityInWorld(new EntityItem(world, x + dX, y + dY, z + dZ, (ItemStack)dropped.get(0)));
		
		return dropped;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCompressor();
		
	}
	
	@Override
	public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis){
		int meta = BlockHelper.getLeftSide(world.getBlockMetadata(x, y, z));
		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
		((TileEntityCompressor)world.getTileEntity(x, y, z)).setOrientation(ForgeDirection.getOrientation(meta));
		return true;
	}
	
	@Override
	public boolean hasTileEntity(int meta){
		return true;
	}
}
