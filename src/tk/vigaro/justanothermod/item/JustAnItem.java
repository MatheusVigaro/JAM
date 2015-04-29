package tk.vigaro.justanothermod.item;

import net.minecraft.item.Item;
import tk.vigaro.justanothermod.CreativeTab;
import tk.vigaro.justanothermod.ModInformation;

public class JustAnItem extends Item {
	public JustAnItem(int maxStackSize, String unlocalizedName){
		setMaxStackSize(maxStackSize);
		setUnlocalizedName(unlocalizedName);
		setTextureName(ModInformation.ID + ":" + unlocalizedName);
		setCreativeTab(CreativeTab.justAnotherMod);

	}
	
}
