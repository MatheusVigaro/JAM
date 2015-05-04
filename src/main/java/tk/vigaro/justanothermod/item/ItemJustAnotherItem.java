package tk.vigaro.justanothermod.item;

import net.minecraft.item.Item;
import tk.vigaro.justanothermod.CreativeTab;
import tk.vigaro.justanothermod.Reference;

public class ItemJustAnotherItem extends Item {
	public ItemJustAnotherItem(int maxStackSize, String unlocalizedName){
		super();
		this.setMaxStackSize(maxStackSize);
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName(Reference.ID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTab.justAnotherMod);

	}
	
}
