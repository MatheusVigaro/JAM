package tk.vigaro.justanothermod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import tk.vigaro.justanothermod.JAMMaterials;
import tk.vigaro.justanothermod.References;
import tk.vigaro.justanothermod.item.JAMItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLightSource extends Block{
	public BlockLightSource(float lightLevel){
		super(JAMMaterials.lightSourceMaterial);
		this.setBlockName(BlockInfo.LIGHT_SOURCE_UNLOCALIZED_NAME);
		this.setLightLevel(lightLevel);
		this.setBlockBounds(0F, 0F, 0F, 0F, 0F, 0F);
		this.setBlockTextureName(References.ID + ":noTexture");

	}

	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public int quantityDropped(Random random){
		return 0;
	}
	
	@Override
	public int getRenderType(){
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
		return null;
	}
	
	@Override
	public boolean canCollideCheck(int i, boolean b){
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block){
		if (world.isAirBlock(x+1, y, z) | world.getBlock(x, y, z) == this && world.isAirBlock(x-1 , y, z) | world.getBlock(x-1, y, z) == this && world.isAirBlock(x, y+1, z) | world.getBlock(x, y+1, z) == this && world.isAirBlock(x, y-1, z) | world.getBlock(x, y-1, z) == this && world.isAirBlock(x, y, z+1) | world.getBlock(x, y, z+1) == this && world.isAirBlock(x, y, z-1) | world.getBlock(x, y, z-1) == this){
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand){
		if (Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem() != null && Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem().getItem() == JAMItems.wandLight ){
			double motion = rand.nextGaussian()*0.02D;
			world.spawnParticle("happyVillager", x+0.5D, y+0.5D, z+0.5D, motion, motion, motion);
		}
	}
}
