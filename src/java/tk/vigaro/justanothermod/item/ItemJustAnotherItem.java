package tk.vigaro.justanothermod.item;

import net.minecraft.item.Item;
import tk.vigaro.justanothermod.CreativeTab;
import tk.vigaro.justanothermod.ModInformation;

public class ItemJustAnotherItem extends Item {
	public ItemJustAnotherItem(int maxStackSize, String unlocalizedName){
		setMaxStackSize(maxStackSize);
		setUnlocalizedName(unlocalizedName);
		setTextureName(ModInformation.ID + ":" + unlocalizedName);
		setCreativeTab(CreativeTab.justAnotherMod);

	}
	
}
