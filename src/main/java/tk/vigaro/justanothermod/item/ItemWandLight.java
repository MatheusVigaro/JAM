package tk.vigaro.justanothermod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tk.vigaro.justanothermod.References;
import tk.vigaro.justanothermod.block.JAMBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandLight extends Item{
	public ItemWandLight(){
		setMaxStackSize(1);
		setCreativeTab(References.creativeTab);
		setHasSubtypes(true);
		setUnlocalizedName(ItemInfo.WAND_LIGHT_UNLOCALIZED_NAME);

	}
	
	public IIcon[] icons = new IIcon[6];
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg){
		for (int i = 0; i < 6; i++){
			this.icons[i] = reg.registerIcon(References.ID + ":" + ItemInfo.WAND_LIGHT_UNLOCALIZED_NAME + "_" + i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int meta){
		return this.icons[meta];
		
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list){
		for (int i = 0; i < 6; i++){
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack){
		return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float sidex, float sidey, float sidez){
		if (!player.canPlayerEdit(x, y, z, side, itemStack)){
			return false;
		}
		
		if (world.getBlock(x, y, z) == Blocks.glowstone && itemStack.getItemDamage() == 4 && world.getBlock( x+1, y, z) == Blocks.lapis_block && world.getBlock(x-1, y, z) == Blocks.lapis_block && world.getBlock(x, y, z+1) == Blocks.lapis_block && world.getBlock(x, y, z-1) == Blocks.lapis_block && world.getBlock(x+1, y, z+1) == Blocks.gold_block && world.getBlock(x+1, y, z-1) == Blocks.gold_block && world.getBlock(x-1, y, z+1) == Blocks.gold_block && world.getBlock(x-1, y, z-1) == Blocks.gold_block){
			if (!world.isRemote){
				world.setBlockToAir(x+1, y, z);
				world.setBlockToAir(x-1, y, z);
				world.setBlockToAir(x, y, z+1);
				world.setBlockToAir(x, y, z-1);
				world.setBlockToAir(x+1, y, z+1);
				world.setBlockToAir(x+1, y, z-1);
				world.setBlockToAir(x-1, y, z+1);
				world.setBlockToAir(x-1, y, z-1);
				world.addWeatherEffect(new EntityLightningBolt(world, x, y, z));
				world.setBlock(x, y, z, JAMBlocks.condensedLight);

			}else{
				world.spawnParticle("hugeexplosion", x+1, y, z, 0, 0, 0);
				world.spawnParticle("hugeexplosion", x-1, y, z, 0, 0, 0);
				world.spawnParticle("hugeexplosion", x+1, y, z+1, 0, 0, 0);
				world.spawnParticle("hugeexplosion", x+1, y, z-1, 0, 0, 0);
				world.spawnParticle("hugeexplosion", x-1, y, z+1, 0, 0, 0);
				world.spawnParticle("hugeexplosion", x-1, y, z-1, 0, 0, 0);
			}
			return true;
		}
		
		if (!world.isAirBlock(x, y, z)){
			x += ForgeDirection.getOrientation(side).offsetX;
			y += ForgeDirection.getOrientation(side).offsetY;
			z += ForgeDirection.getOrientation(side).offsetZ;

			if (!world.isAirBlock(x, y, z)){
				return false;
			}
			if (!world.isRemote){
				world.setBlock(x, y, z, JAMBlocks.lightSource.get(itemStack.getItemDamage()));
			}
			return true;
			
		}
		
		return false;
	
	}

}
