package tk.vigaro.justanothermod;

import java.text.DecimalFormat;
import java.util.Queue;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import org.apache.logging.log4j.Logger;

import tk.vigaro.justanothermod.item.JAMItems;
import tk.vigaro.justanothermod.util.ChunkPosition;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class References {
	public static final String ID = "JustAnotherMod";
	public static final String NAME = "Just Another Mod";
	public static final String VERSION = "1.0.0";
	
	public static Queue<ChunkPosition> toGenerate;
	public static int startingSize;
	public static DecimalFormat decimalFormat;
	public static Logger logger;
	public static Random random = new Random();
	
	public static CreativeTabs creativeTab = new CreativeTabs("justAnotherMod"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return JAMItems.nuggetCoal;
		}
	};
}
