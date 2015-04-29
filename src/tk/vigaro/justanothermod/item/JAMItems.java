package tk.vigaro.justanothermod.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class JAMItems {
	public static Item nuggetCoal;
	public static Item nuggetCharcoal;
	
	public static void init(){
		nuggetCoal = new JustAnItem(64, "nuggetCoal");
		nuggetCharcoal = new JustAnItem(64, "nuggetCharcoal");
		
	}
	
	public static void registerItems(){
		GameRegistry.registerItem(nuggetCoal, "nuggetCoal");
		GameRegistry.registerItem(nuggetCharcoal, "nuggetCharcoal");
		
	}
	
	public static void registerRecipes(){
		ItemStack nuggetCoalStack = new ItemStack(nuggetCoal);
		ItemStack nuggetCharcoalStack = new ItemStack(nuggetCharcoal);
		
		GameRegistry.addShapelessRecipe(new ItemStack(nuggetCoal, 8), new ItemStack(Items.coal));
		GameRegistry.addShapelessRecipe(new ItemStack(nuggetCharcoal, 8), new ItemStack(Items.coal, 1, 1));
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.coal), nuggetCoalStack, nuggetCoalStack, nuggetCoalStack,
																   nuggetCoalStack, nuggetCoalStack, nuggetCoalStack,
																   nuggetCoalStack, nuggetCoalStack);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.coal, 1, 1), nuggetCharcoalStack, nuggetCharcoalStack, nuggetCharcoalStack,
																		 nuggetCharcoalStack, nuggetCharcoalStack, nuggetCharcoalStack,
																		 nuggetCharcoalStack, nuggetCharcoalStack);
	}
	
}