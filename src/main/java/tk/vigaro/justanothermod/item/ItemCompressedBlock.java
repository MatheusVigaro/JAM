package tk.vigaro.justanothermod.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tk.vigaro.justanothermod.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCompressedBlock extends Item{
	private final Block contained;
	
	public ItemCompressedBlock(Block block, String mod, String name){
		this.contained = block;
		this.setUnlocalizedName(ItemInfo.ITEM_COMPRESSED_UNLOCALIZED_NAME + "_" + mod + "_" + name);

	}
	
	public ItemStack getContained(){
		return new ItemStack(contained);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(References.ID + ":" + ItemInfo.ITEM_COMPRESSED_UNLOCALIZED_NAME);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack itemstack){
		return "Compressed " + contained.getLocalizedName();
	}

}
