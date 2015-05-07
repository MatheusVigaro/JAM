package tk.vigaro.justanothermod;
import net.minecraft.item.ItemStack;
import tk.vigaro.justanothermod.block.JAMBlocks;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIJustAnotherModConfig implements IConfigureNEI{

	@Override
	public String getName() {
		return References.NAME;
	}

	@Override
	public String getVersion() {
		return References.VERSION;
	}

	@Override
	public void loadConfig() {
		for (int i = 0; i < 6; i++){
			API.hideItem(new ItemStack(JAMBlocks.lightSource.get(i)));
		}
	}

}
