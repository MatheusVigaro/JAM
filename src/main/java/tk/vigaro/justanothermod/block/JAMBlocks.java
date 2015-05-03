package tk.vigaro.justanothermod.block;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class JAMBlocks {
	public static Block lightSource1;
	public static Block lightSource2;
	public static Block lightSource3;
	public static Block lightSource4;
	public static Block lightSource5;
	public static Block lightSource6;
	public static Map<String, Block> lightSource;
	
	public static void init(){
		lightSource1 = new BlockLightSource(0.2F);
		lightSource2 = new BlockLightSource(0.333F);
		lightSource3 = new BlockLightSource(0.5F);
		lightSource4 = new BlockLightSource(0.666F);
		lightSource5 = new BlockLightSource(0.833F);
		lightSource6 = new BlockLightSource(1F);
		lightSource = new HashMap();
		lightSource.put("1", lightSource1);
		lightSource.put("2", lightSource2);
		lightSource.put("3", lightSource3);
		lightSource.put("4", lightSource4);
		lightSource.put("5", lightSource5);
		lightSource.put("6", lightSource6);
	}
	
	public static void registerBlocks(){
		GameRegistry.registerBlock(lightSource1, BlockInformation.LIGHT_SOURCE_UNLOCALIZED_NAME + "1");
		GameRegistry.registerBlock(lightSource2, BlockInformation.LIGHT_SOURCE_UNLOCALIZED_NAME + "2");
		GameRegistry.registerBlock(lightSource3, BlockInformation.LIGHT_SOURCE_UNLOCALIZED_NAME + "3");
		GameRegistry.registerBlock(lightSource4, BlockInformation.LIGHT_SOURCE_UNLOCALIZED_NAME + "4");
		GameRegistry.registerBlock(lightSource5, BlockInformation.LIGHT_SOURCE_UNLOCALIZED_NAME + "5");
		GameRegistry.registerBlock(lightSource6, BlockInformation.LIGHT_SOURCE_UNLOCALIZED_NAME + "6");
		
	}

	public static void registerRecipes() {
		// TODO Auto-generated method stub
		
	}

	public static void registerOreDict() {
		// TODO Auto-generated method stub
		
	}

}
