package tk.vigaro.justanothermod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tk.vigaro.justanothermod.CreativeTab;
import tk.vigaro.justanothermod.Reference;
import tk.vigaro.justanothermod.block.JAMBlocks;

public class ItemWandLight extends Item{
	public ItemWandLight(){
		setMaxStackSize(1);
		setCreativeTab(CreativeTab.justAnotherMod);
		setHasSubtypes(true);
		setUnlocalizedName(ItemInfo.WAND_LIGHT_UNLOCALIZED_NAME);

	}
	
	public IIcon[] icons = new IIcon[6];
	
	@Override
	public void registerIcons(IIconRegister reg){
		for (int i = 0; i < 6; i++){
			this.icons[i] = reg.registerIcon(Reference.ID + ":" + ItemInfo.WAND_LIGHT_UNLOCALIZED_NAME + "_" + i);
		}
	}
	
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
