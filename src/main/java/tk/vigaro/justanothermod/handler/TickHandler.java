package tk.vigaro.justanothermod.handler;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import tk.vigaro.justanothermod.ConfigInfo;
import tk.vigaro.justanothermod.Reference;
import tk.vigaro.justanothermod.util.ChunkPosition;
import tk.vigaro.justanothermod.util.Utilities;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class TickHandler {

	private int tickCounter = 0;
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if(Reference.toGenerate != null && !Reference.toGenerate.isEmpty()) {
			tickCounter++;
			for(int i = 0; i < ConfigInfo.chunksPerTick; i++) {
				ChunkPosition cp = Reference.toGenerate.poll();
				if(cp != null) {
					Utilities.generateChunk(null, cp.getX(), cp.getZ(), cp.getDimensionID());
					float completedPercentage = 1 - (float)Reference.toGenerate.size()/(float)Reference.startingSize;
					if(tickCounter == ConfigInfo.tickDelay) {
						Reference.logger.info("percentage: " + completedPercentage);
						tickCounter = 0;
						ChatComponentTranslation chatTranslation = new ChatComponentTranslation("");
						MinecraftServer.getServer().addChatMessage(chatTranslation);
						
						cp.getICommandSender().addChatMessage(new ChatComponentText("JAM: " + (int)(completedPercentage * 100) + "% completed"));
					}
					if(	Reference.toGenerate.peek() == null) {
						ChatComponentTranslation chatTranslation = new ChatComponentTranslation("commands.successful");
						MinecraftServer.getServer().addChatMessage(chatTranslation);
						cp.getICommandSender().addChatMessage(new ChatComponentText(chatTranslation.getUnformattedTextForChat()));
					}
				}
			}
		}
	}

}