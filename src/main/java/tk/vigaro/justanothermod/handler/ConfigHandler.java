package tk.vigaro.justanothermod.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import tk.vigaro.justanothermod.References;
import tk.vigaro.justanothermod.config.ConfigChunkPreGen;
import tk.vigaro.justanothermod.config.ConfigMachines;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {
	
	public static Configuration configuration;
		
	public static void init(File configFile) {
		if(configuration == null) { 
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(OnConfigChangedEvent event) {
		if(event.modID.equalsIgnoreCase(References.ID)) {
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration() {
		ConfigChunkPreGen.x = configuration.get("Chunk PreGen", "x", 0, "X starting value").getInt();
		ConfigChunkPreGen.z = configuration.get("Chunk PreGen", "z", 0, "Z starting value").getInt();
		ConfigChunkPreGen.height = configuration.get("Chunk PreGen", "height", 0, "Height starting value").getInt();
		ConfigChunkPreGen.width = configuration.get("Chunk PreGen", "width", 0, "Width starting value").getInt();
		ConfigChunkPreGen.chunksPerTick = configuration.get("Chunk PreGen", "numChunksPerTick", 1, "Number of chunks loaded per tick").getInt();
		ConfigChunkPreGen.tickDelay = configuration.get("Chunk PreGen", "tickDelay", 40, "Number of ticks inbetween percentage updates").getInt();
		
		ConfigMachines.compressorEnergyPerCycle = configuration.get("Machines" , "compressorEnergyPerCycle", 5000, "Amount of power consumed by the compressor per cycle").getInt();		
		
		if(configuration.hasChanged()) {
			configuration.save();
		}
	}
}