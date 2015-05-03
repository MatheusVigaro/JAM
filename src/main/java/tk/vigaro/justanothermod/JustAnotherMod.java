package tk.vigaro.justanothermod;

import java.text.DecimalFormat;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import tk.vigaro.justanothermod.block.JAMBlocks;
import tk.vigaro.justanothermod.command.PreGenCommand;
import tk.vigaro.justanothermod.handler.ConfigHandler;
import tk.vigaro.justanothermod.handler.TickHandler;
import tk.vigaro.justanothermod.item.JAMItems;
import tk.vigaro.justanothermod.util.Utilities;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = ModInformation.ID, version = ModInformation.VERSION)
public class JustAnotherMod{
	@Instance(value = ModInformation.ID)
	public static JustAnotherMod instance;
	
	@NetworkCheckHandler
	public boolean networkCheckHandler(Map<String, String> map, Side side) { 
		return true;
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ModInformation.logger = LogManager.getLogger(ModInformation.ID);
		ModInformation.decimalFormat = new DecimalFormat();
		ModInformation.decimalFormat.setMaximumFractionDigits(2);
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigHandler());
		FMLCommonHandler.instance().bus().register(new TickHandler());
		JAMBlocks.init();
		JAMBlocks.registerBlocks();
		JAMItems.init();
		JAMItems.registerItems();
		
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event){
		JAMBlocks.registerRecipes();
		JAMBlocks.registerOreDict();
		JAMItems.registerRecipes();
		JAMItems.registerOreDict();
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
	}
	
	@EventHandler
	public void serverInit(FMLServerStartingEvent event){
		event.registerServerCommand(new PreGenCommand());
		if(ConfigInfo.x != null && ConfigInfo.z != null && ConfigInfo.height != null && ConfigInfo.width != null && ConfigInfo.height > 0 && ConfigInfo.width > 0) {
			Utilities.generateChunks(ConfigInfo.x, ConfigInfo.z, ConfigInfo.width, ConfigInfo.height, 0);
		}
	}
}