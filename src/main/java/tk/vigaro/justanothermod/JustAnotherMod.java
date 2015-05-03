package tk.vigaro.justanothermod;

import tk.vigaro.justanothermod.block.JAMBlocks;
import tk.vigaro.justanothermod.item.JAMItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModInformation.ID, version = ModInformation.VERSION)
public class JustAnotherMod{
	@Instance(value = ModInformation.ID)
	public static JustAnotherMod instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		System.out.println("Starting PreInitialization");
		JAMBlocks.init();
		JAMBlocks.registerBlocks();
		JAMItems.init();
		JAMItems.registerItems();
		
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event){
		System.out.println("Starting Initialization");
		JAMBlocks.registerRecipes();
		JAMBlocks.registerOreDict();
		JAMItems.registerRecipes();
		JAMItems.registerOreDict();
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		System.out.println("Starting PostInitialization");
	}
}
