package tk.vigaro.justanothermod;

import java.util.Arrays;

import net.minecraft.item.ItemStack;
import tk.vigaro.justanothermod.item.JAMItems;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler{
	
	@Override
	public int getBurnTime(ItemStack fuel){
		if (Arrays.asList(JAMItems.nuggetCoal, JAMItems.nuggetCharcoal).contains(fuel)){
			return 200;
		}else{
			return 0;
		}
	}

}
