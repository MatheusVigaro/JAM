package tk.vigaro.justanothermod.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import tk.vigaro.justanothermod.ConfigInfo;
import tk.vigaro.justanothermod.References;
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
		ConfigInfo.x = configuration.get(Configuration.CATEGORY_GENERAL, "x", 0, "X starting value").getInt();
		ConfigInfo.z = configuration.get(Configuration.CATEGORY_GENERAL, "z", 0, "Z starting value").getInt();
		ConfigInfo.height = configuration.get(Configuration.CATEGORY_GENERAL, "height", 0, "Height starting value").getInt();
		ConfigInfo.width = configuration.get(Configuration.CATEGORY_GENERAL, "width", 0, "Width starting value").getInt();
		ConfigInfo.chunksPerTick = configuration.get(Configuration.CATEGORY_GENERAL, "numChunksPerTick", 1, "Number of chunks loaded per tick").getInt();
		ConfigInfo.tickDelay = configuration.get(Configuration.CATEGORY_GENERAL, "tickDelay", 40, "Number of ticks inbetween percentage updates").getInt();
		
		if(configuration.hasChanged()) {
			configuration.save();
		}
	}
}