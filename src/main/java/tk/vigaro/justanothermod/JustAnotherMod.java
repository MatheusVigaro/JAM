package tk.vigaro.justanothermod;

import java.text.DecimalFormat;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import tk.vigaro.justanothermod.block.JAMBlocks;
import tk.vigaro.justanothermod.command.PreGenCommand;
import tk.vigaro.justanothermod.config.ConfigChunkPreGen;
import tk.vigaro.justanothermod.handler.ConfigHandler;
import tk.vigaro.justanothermod.handler.TickHandler;
import tk.vigaro.justanothermod.item.JAMItems;
import tk.vigaro.justanothermod.util.ChunkGenUtils;
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

@Mod(modid = References.ID, version = References.VERSION, dependencies = "required-after:CoFHCore")
public class JustAnotherMod{
	@Instance(value = References.ID)
	public static JustAnotherMod instance;
	
	@NetworkCheckHandler
	public boolean networkCheckHandler(Map<String, String> map, Side side) { 
		return true;
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		References.logger = LogManager.getLogger(References.ID);
		References.decimalFormat = new DecimalFormat();
		References.decimalFormat.setMaximumFractionDigits(2);
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
		JAMBlocks.registerTileEntities();
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
		if(ConfigChunkPreGen.x != null && ConfigChunkPreGen.z != null && ConfigChunkPreGen.height != null && ConfigChunkPreGen.width != null && ConfigChunkPreGen.height > 0 && ConfigChunkPreGen.width > 0) {
			ChunkGenUtils.generateChunks(ConfigChunkPreGen.x, ConfigChunkPreGen.z, ConfigChunkPreGen.width, ConfigChunkPreGen.height, 0);
		}
	}
}