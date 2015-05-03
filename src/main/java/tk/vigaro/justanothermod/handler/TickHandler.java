package tk.vigaro.justanothermod.handler;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import tk.vigaro.justanothermod.ConfigInfo;
import tk.vigaro.justanothermod.ModInformation;
import tk.vigaro.justanothermod.util.ChunkPosition;
import tk.vigaro.justanothermod.util.Utilities;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class TickHandler {

	private int tickCounter = 0;
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if(ModInformation.toGenerate != null && !ModInformation.toGenerate.isEmpty()) {
			tickCounter++;
			for(int i = 0; i < ConfigInfo.chunksPerTick; i++) {
				ChunkPosition cp = ModInformation.toGenerate.poll();
				if(cp != null) {
					Utilities.generateChunk(null, cp.getX(), cp.getZ(), cp.getDimensionID());
					float completedPercentage = 1 - (float)ModInformation.toGenerate.size()/(float)ModInformation.startingSize;
					if(tickCounter == ConfigInfo.tickDelay) {
						ModInformation.logger.info("percentage: " + completedPercentage);
						tickCounter = 0;
						ChatComponentTranslation chatTranslation = new ChatComponentTranslation("");
						MinecraftServer.getServer().addChatMessage(chatTranslation);
						
						cp.getICommandSender().addChatMessage(new ChatComponentText("JAM: " + (int)(completedPercentage * 100) + "% completed"));
					}
					if(	ModInformation.toGenerate.peek() == null) {
						ChatComponentTranslation chatTranslation = new ChatComponentTranslation("commands.successful");
						MinecraftServer.getServer().addChatMessage(chatTranslation);
						cp.getICommandSender().addChatMessage(new ChatComponentText(chatTranslation.getUnformattedTextForChat()));
					}
				}
			}
		}
	}

}