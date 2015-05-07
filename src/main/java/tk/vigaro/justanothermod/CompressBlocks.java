package tk.vigaro.justanothermod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import tk.vigaro.justanothermod.handler.CompressedRenderHandler;
import tk.vigaro.justanothermod.item.ItemCompressedBlock;
import tk.vigaro.justanothermod.item.ItemInfo;
import cpw.mods.fml.common.registry.GameRegistry;

public class CompressBlocks {

	static Object[] keys = Block.blockRegistry.getKeys().toArray();
	
	public static void init(){
		for (int i = 0; i < keys.length; i++){
			String[] s = keys[i].toString().split(":");
			References.logger.info("Found block valid block \"" + s[1] + "\" from mod \"" + s[0] + "\", COMPRESS DEM BLOCKS!");
			Block b = GameRegistry.findBlock(s[0], s[1]);
			
			if (b.getRenderType() == 0 && b.isOpaqueCube() && b.isNormalCube() && !s[1].contains("lit_redstone")){			
				Item c = new ItemCompressedBlock(b, s[0], s[1]);
				GameRegistry.registerItem(c, ItemInfo.ITEM_COMPRESSED_UNLOCALIZED_NAME + "_" + s[0] + "_" + s[1]);
				GameRegistry.addShapelessRecipe(new ItemStack(c), b, b, b, b, b, b, b, b ,b);
				GameRegistry.addShapelessRecipe(new ItemStack(b, 9), c);
				MinecraftForgeClient.registerItemRenderer(c, new CompressedRenderHandler());
			}
		}
		
	}
}
