package tk.vigaro.justanothermod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tk.vigaro.justanothermod.References;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCompressedBlock extends Item{
	
	public ItemCompressedBlock(){
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);

	}
	
	public IIcon[] icons = new IIcon[6];
	
	public ItemStack getContained(ItemStack itemstack){
		if (itemstack.stackTagCompound != null){
			String contained[] = itemstack.stackTagCompound.getString("contained").split(":");
			return new ItemStack(GameRegistry.findBlock(contained[0], contained[1]), 0, Integer.parseInt(contained[2]));
		}
		return null;
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister register) {
		for (int i = 0; i < 6; i++){		
			this.icons[i] = register.registerIcon(References.ID + ":" + ItemInfo.ITEM_COMPRESSED_UNLOCALIZED_NAME + "_" + i);
		}	
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack){
		return ItemInfo.ITEM_COMPRESSED_UNLOCALIZED_NAME + "x" + stack.getItemDamage();
		
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
	public String getItemStackDisplayName(ItemStack itemstack){
		if (itemstack.stackTagCompound != null){
			String contained[] = itemstack.stackTagCompound.getString("contained").split(":");
			return itemstack.getItemDamage() + 1 + "xCompressed " + (new ItemStack(GameRegistry.findBlock(contained[0], contained[1]), 0, Integer.parseInt(contained[2])).getDisplayName());
		}
		return itemstack.getItemDamage() + 1 + "xCompressed block";
	}

}
