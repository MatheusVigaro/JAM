package tk.vigaro.justanothermod;

import java.text.DecimalFormat;
import java.util.Queue;

import org.apache.logging.log4j.Logger;

import tk.vigaro.justanothermod.util.ChunkPosition;

public class Reference {
	public static final String ID = "justanothermod";
	public static final String NAME = "Just Another Mod";
	public static final String VERSION = "0.0.1b";
	
	public static Queue<ChunkPosition> toGenerate;
	public static int startingSize;
	public static DecimalFormat decimalFormat;
	public static Logger logger;

}
