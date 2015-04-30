package tk.vigaro.justanothermod.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tk.vigaro.justanothermod.Materials;
import cpw.mods.fml.common.registry.GameRegistry;

public class JAMItems {
	public static Item nuggetCoal;
	public static Item nuggetCharcoal;
	public static Item enderArmorHelmet;
	public static Item enderArmorChestplate;
	public static Item enderArmorLeggings;
	public static Item enderArmorBoots;
	
	public static void init(){
		nuggetCoal = new JustAnItem(64, "nuggetCoal");
		nuggetCharcoal = new JustAnItem(64, "nuggetCharcoal");
		enderArmorHelmet = new EnderArmor(ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Helmet", Materials.enderArmorMaterial, 0);
		enderArmorChestplate = new EnderArmor(ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Chestplate", Materials.enderArmorMaterial, 1);
		enderArmorLeggings = new EnderArmor(ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Leggings", Materials.enderArmorMaterial, 2);
		enderArmorBoots = new EnderArmor(ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Boots", Materials.enderArmorMaterial, 3);
		
	}
	
	public static void registerItems(){
		GameRegistry.registerItem(nuggetCoal, ItemInfo.COAL_NUGGET_UNLOCALIZED_NAME);
		GameRegistry.registerItem(nuggetCharcoal, ItemInfo.CHARCOAL_NUGGET_UNLOCALIZED_NAME);
		GameRegistry.registerItem(enderArmorHelmet, ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Helmet");
		GameRegistry.registerItem(enderArmorChestplate, ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Chestplate");
		GameRegistry.registerItem(enderArmorLeggings, ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Leggings");
		GameRegistry.registerItem(enderArmorBoots, ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Boots");
		
	}
	
	public static void registerRecipes(){
		ItemStack nuggetCoalStack = new ItemStack(nuggetCoal);
		ItemStack nuggetCharcoalStack = new ItemStack(nuggetCharcoal);
		ItemStack enderPearlStack = new ItemStack(Items.ender_pearl);
		
		GameRegistry.addShapelessRecipe(new ItemStack(nuggetCoal, 8), new ItemStack(Items.coal));
		GameRegistry.addShapelessRecipe(new ItemStack(nuggetCharcoal, 8), new ItemStack(Items.coal, 1, 1));
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.coal), nuggetCoalStack, nuggetCoalStack, nuggetCoalStack,
																   nuggetCoalStack, nuggetCoalStack, nuggetCoalStack,
																   nuggetCoalStack, nuggetCoalStack);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.coal, 1, 1), nuggetCharcoalStack, nuggetCharcoalStack, nuggetCharcoalStack,
																		 nuggetCharcoalStack, nuggetCharcoalStack, nuggetCharcoalStack,
																		 nuggetCharcoalStack, nuggetCharcoalStack);

		GameRegistry.addRecipe(new ItemStack(enderArmorHelmet), "eee",
															    "e e",
																"   ", 'e', enderPearlStack);
		GameRegistry.addRecipe(new ItemStack(enderArmorChestplate), "e e",
																	"eee",
																	"eee", 'e', enderPearlStack);
		GameRegistry.addRecipe(new ItemStack(enderArmorLeggings), "eee",
																  "e e",
																  "e e", 'e', enderPearlStack);
		GameRegistry.addRecipe(new ItemStack(enderArmorBoots), "e e",
															   "e e", 'e', enderPearlStack);
		
	}

	public static void registerOreDict() {
		OreDictionary.registerOre("nuggetCoal", new ItemStack(nuggetCoal));
		OreDictionary.registerOre("nuggetCharcoal", new ItemStack(nuggetCharcoal));
		
	}
	
}