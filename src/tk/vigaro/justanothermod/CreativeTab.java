package tk.vigaro.justanothermod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import tk.vigaro.justanothermod.item.JAMItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab {
	public static CreativeTabs justAnotherMod = new CreativeTabs("justAnotherMod"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return JAMItems.nuggetCoal;

		}
	};
}
	