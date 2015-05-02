package tk.vigaro.justanothermod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import tk.vigaro.justanothermod.CreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLightSource extends Block{
	public BlockLightSource(float lightLevel){
		super(Material.air);
		this.setCreativeTab(CreativeTab.justAnotherMod);
		this.setLightLevel(lightLevel);
		this.setBlockBounds(0F, 0F, 0F, 0F, 0F, 0F);

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
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block){
		if (world.isAirBlock(x+1, y, z) && world.isAirBlock(x-1 , y, z) && world.isAirBlock(x, y+1, z) && world.isAirBlock(x, y-1, z) && world.isAirBlock(x, y, z+1) && world.isAirBlock(x, y, z-1)){
			world.setBlockToAir(x, y, z);
		}
	}
}
