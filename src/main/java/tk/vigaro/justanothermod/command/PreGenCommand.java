package tk.vigaro.justanothermod.command;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import tk.vigaro.justanothermod.ModInformation;
import tk.vigaro.justanothermod.util.ChunkPosition;

public class PreGenCommand implements ICommand{
	private List aliases;
	public PreGenCommand()
	{
		this.aliases = new ArrayList();
		this.aliases.add("pregen");
	}

	@Override
	public String getCommandName()
	{
		return "pregen";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/pregen <x> <y> <height> <width> [dimension]";
	}

	@Override
	public List getCommandAliases()
	{
		return this.aliases;
	}

	/**
	 * Return the required permission level for this command.
	 */
	public int getRequiredPermissionLevel()
	{
		return 4;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if(!icommandsender.canCommandSenderUseCommand(getRequiredPermissionLevel(), this.getCommandName()) && !MinecraftServer.getServer().isSinglePlayer()) {
			ChatComponentTranslation chatTranslation = new ChatComponentTranslation("commands.generic.permission", new Object[0]);
			MinecraftServer.getServer().addChatMessage(chatTranslation);
			icommandsender.addChatMessage(new ChatComponentText(chatTranslation.getUnformattedTextForChat()));
		} else {
			int playerX = 0;
			int playerY = 0;
			int playerZ = 0;
			if(!icommandsender.getCommandSenderName().equalsIgnoreCase("Rcon")) {
				EntityPlayer ep = MinecraftServer.getServer().worldServerForDimension(0).getPlayerEntityByName(icommandsender.getCommandSenderName());
				ChunkCoordinates cc = icommandsender.getPlayerCoordinates();
				playerX = cc.posX;
				playerY = cc.posY;
				playerZ = cc.posZ;
			}
			if(astring.length == 0 || astring[0].equalsIgnoreCase("help")) {
				ChatComponentTranslation chatTranslation = new ChatComponentTranslation(getCommandUsage(icommandsender), new Object[0]);
				MinecraftServer.getServer().addChatMessage(chatTranslation);
				icommandsender.addChatMessage(new ChatComponentText(chatTranslation.getUnformattedTextForChat()));
			}
			else if(astring[0].equalsIgnoreCase("stop")) {
				ModInformation.toGenerate.clear();
				ChatComponentTranslation chatTranslation = new ChatComponentTranslation("commands.stopped");
				MinecraftServer.getServer().addChatMessage(chatTranslation);
				icommandsender.addChatMessage(new ChatComponentText(chatTranslation.getUnformattedTextForChat()));
			} else {
				try {
					int x = 0;
					int z = 0;
					if(astring[0].equalsIgnoreCase("~")) {
						x = playerX/16;
					} else {
						x = Integer.parseInt(astring[0]);
					}
					if(astring[1].equalsIgnoreCase("~")) {
						z = playerZ/16;
					} else {
						z = Integer.parseInt(astring[1]);
					}
					int height = Integer.parseInt(astring[2]);
					int width = Integer.parseInt(astring[3]);
					int dimensionID = icommandsender.getEntityWorld().provider.dimensionId;
					if(astring.length == 5) {
						dimensionID = Integer.parseInt(astring[4]);
					}

					for(int i = (x - width/2); i < (x + width/2); i++) {
						for(int j = (z - height/2); j < (z + height/2); j++) {
							if(ModInformation.toGenerate == null) {
								ModInformation.toGenerate = new LinkedList<ChunkPosition>();
							}
							ModInformation.toGenerate.add(new ChunkPosition(i, j, dimensionID, icommandsender));
						}
					}
					ModInformation.startingSize = ModInformation.toGenerate.size();
				} catch (NumberFormatException e) {
					e.printStackTrace();
					ChatComponentTranslation chatTranslation = new ChatComponentTranslation("commands.numberFormatException");
					MinecraftServer.getServer().addChatMessage(chatTranslation);
					icommandsender.addChatMessage(new ChatComponentText(chatTranslation.getUnformattedTextForChat()));
				} catch (Exception e) {
					e.printStackTrace();
					ChatComponentTranslation chatTranslation = new ChatComponentTranslation("commands.failed");
					MinecraftServer.getServer().addChatMessage(chatTranslation);
					icommandsender.addChatMessage(new ChatComponentText(chatTranslation.getUnformattedTextForChat()));
				}
			}
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
	{	
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender,
			String[] astring)
	{
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i)
	{
		return false;
	}

	@Override
	public int compareTo(Object o)
	{
		return 0;
	}

}
