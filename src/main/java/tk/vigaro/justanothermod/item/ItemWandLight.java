package tk.vigaro.justanothermod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tk.vigaro.justanothermod.CreativeTab;
import tk.vigaro.justanothermod.ModInformation;
import tk.vigaro.justanothermod.block.JAMBlocks;

public class ItemWandLight extends Item{
	public ItemWandLight(int tier){
		setMaxStackSize(1);
		setUnlocalizedName(ItemInfo.WAND_LIGHT_UNLOCALIZED_NAME + tier);
		setTextureName(ModInformation.ID + ":" + ItemInfo.WAND_LIGHT_UNLOCALIZED_NAME + tier);
		setCreativeTab(CreativeTab.justAnotherMod);
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
				world.setBlock(x, y, z, JAMBlocks.lightSource.get(itemStack.getUnlocalizedName().substring(14)));
			}
			return true;
			
		}
		
		return false;
	
	}

}
