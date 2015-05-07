package tk.vigaro.justanothermod.block;

import java.util.HashMap;
import java.util.Map;

import tk.vigaro.justanothermod.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class JAMBlocks {
	public static Block lightSource0;
	public static Block lightSource1;
	public static Block lightSource2;
	public static Block lightSource3;
	public static Block lightSource4;
	public static Block lightSource5;
	public static Map<Integer, Block> lightSource;

	public static Block condensedLight;
	
	public static void init(){
		References.logger.info("Registering blocks");
		lightSource0 = new BlockLightSource(0.2F);
		lightSource1 = new BlockLightSource(0.333F);
		lightSource2 = new BlockLightSource(0.5F);
		lightSource3 = new BlockLightSource(0.666F);
		lightSource4 = new BlockLightSource(0.833F);
		lightSource5 = new BlockLightSource(1F);
		lightSource = new HashMap();
		lightSource.put(0, lightSource0);
		lightSource.put(1, lightSource1);
		lightSource.put(2, lightSource2);
		lightSource.put(3, lightSource3);
		lightSource.put(4, lightSource4);
		lightSource.put(5, lightSource5);

		condensedLight = new BlockJustAnotherBlock(Material.iron, BlockInformation.CONDENSED_LIGHT_UNLOCALIZED_NAME, "pickaxe", 2).setLightLevel(1.0F).setHardness(20.0F);

	}
	
	public static void registerBlocks(){
		for (int i = 0; i < 6; i++){
			GameRegistry.registerBlock(lightSource.get(i), BlockInformation.LIGHT_SOURCE_UNLOCALIZED_NAME + "_" + i);
		}
		
		GameRegistry.registerBlock(condensedLight, BlockInformation.CONDENSED_LIGHT_UNLOCALIZED_NAME);

	}

	public static void registerRecipes() {
		// TODO Auto-generated method stub
		
	}

	public static void registerOreDict() {
		// TODO Auto-generated method stub
		
	}

}
