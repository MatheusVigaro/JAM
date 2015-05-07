package tk.vigaro.justanothermod.item;

import net.minecraft.item.Item;
import tk.vigaro.justanothermod.References;

public class ItemJustAnotherItem extends Item {
	public ItemJustAnotherItem(int maxStackSize, String unlocalizedName){
		super();
		this.setMaxStackSize(maxStackSize);
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName(References.ID + ":" + unlocalizedName);
		this.setCreativeTab(References.creativeTab);

	}
	
}
