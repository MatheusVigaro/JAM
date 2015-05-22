package tk.vigaro.justanothermod.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.OreDictionary;
import tk.vigaro.justanothermod.JAMMaterials;
import tk.vigaro.justanothermod.References;
import tk.vigaro.justanothermod.block.JAMBlocks;
import tk.vigaro.justanothermod.handler.CompressedRenderHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class JAMItems {
	public static Item nuggetCoal;
	public static Item nuggetCharcoal;
	public static Item enderArmorHelmet;
	public static Item enderArmorChestplate;
	public static Item enderArmorLeggings;
	public static Item enderArmorBoots;
	public static Item wandLight;
	public static Item compressedBlock;
	
	public static void init(){
		References.logger.info("Registering items");
		nuggetCoal = new ItemJustAnotherItem(64, "nuggetCoal");
		nuggetCharcoal = new ItemJustAnotherItem(64, "nuggetCharcoal");
		enderArmorHelmet = new ItemEnderArmor(ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Helmet", JAMMaterials.enderArmorMaterial, 0);
		enderArmorChestplate = new ItemEnderArmor(ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Chestplate", JAMMaterials.enderArmorMaterial, 1);
		enderArmorLeggings = new ItemEnderArmor(ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Leggings", JAMMaterials.enderArmorMaterial, 2);
		enderArmorBoots = new ItemEnderArmor(ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Boots", JAMMaterials.enderArmorMaterial, 3);
		wandLight = new ItemWandLight();
		compressedBlock = new ItemCompressedBlock();
		
	}
	
	public static void registerItems(){
		GameRegistry.registerItem(nuggetCoal, ItemInfo.COAL_NUGGET_UNLOCALIZED_NAME);
		GameRegistry.registerItem(nuggetCharcoal, ItemInfo.CHARCOAL_NUGGET_UNLOCALIZED_NAME);
		GameRegistry.registerItem(enderArmorHelmet, ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Helmet");
		GameRegistry.registerItem(enderArmorChestplate, ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Chestplate");
		GameRegistry.registerItem(enderArmorLeggings, ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Leggings");
		GameRegistry.registerItem(enderArmorBoots, ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + "Boots");
		GameRegistry.registerItem(wandLight, ItemInfo.WAND_LIGHT_UNLOCALIZED_NAME);
		GameRegistry.registerItem(compressedBlock, ItemInfo.ITEM_COMPRESSED_UNLOCALIZED_NAME);
		MinecraftForgeClient.registerItemRenderer(compressedBlock, new CompressedRenderHandler());
	}
	
	public static void registerRecipes(){
		ItemStack wandLight0Stack = new ItemStack(wandLight, 1, 0);
		ItemStack wandLight1Stack = new ItemStack(wandLight, 1, 1);
		ItemStack wandLight2Stack = new ItemStack(wandLight, 1, 2);
		ItemStack wandLight3Stack = new ItemStack(wandLight, 1, 3);
		ItemStack wandLight4Stack = new ItemStack(wandLight, 1, 4);
		ItemStack wandLight5Stack = new ItemStack(wandLight, 1, 5);
		ItemStack nuggetCoalStack = new ItemStack(nuggetCoal);
		ItemStack nuggetCharcoalStack = new ItemStack(nuggetCharcoal);
		ItemStack enderPearlStack = new ItemStack(Items.ender_pearl);
		ItemStack coalStack = new ItemStack(Items.coal);
		ItemStack torchStack = new ItemStack(Blocks.torch);
		ItemStack stickStack = new ItemStack(Items.stick);
		ItemStack ingotIronStack = new ItemStack(Items.iron_ingot);
		ItemStack ingotGoldStack = new ItemStack(Items.gold_ingot);
		ItemStack diamondStack = new ItemStack(Items.diamond);
		ItemStack glowstoneDustStack = new ItemStack(Items.glowstone_dust);
		ItemStack glowstoneStack = new ItemStack(Blocks.glowstone);
		ItemStack obsidianStack = new ItemStack(Blocks.obsidian);
		ItemStack condensedLightStack = new ItemStack(JAMBlocks.condensedLight);
		ItemStack clayStack = new ItemStack(Items.clay_ball);
		ItemStack redstoneStack = new ItemStack(Items.redstone);
		ItemStack blockIronStack = new ItemStack(Blocks.iron_block);
		ItemStack blockGoldStack = new ItemStack(Blocks.gold_block);
		ItemStack blockDiamondStack = new ItemStack(Blocks.diamond_block);
		ItemStack lapisLazuliStack = new ItemStack(Items.dye, 1, 4);
		
		GameRegistry.addShapelessRecipe(new ItemStack(nuggetCoal, 8), coalStack);
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
		
		GameRegistry.addRecipe(wandLight0Stack, " ct",
                                                " sc",
                                                "s  ", 'c', coalStack, 't', torchStack, 's', stickStack);
		GameRegistry.addRecipe(wandLight1Stack, " it",
                                                " wi",
                                                "c  ", 'i', ingotIronStack, 't', torchStack, 'w', wandLight0Stack, 'c', clayStack);
		GameRegistry.addRecipe(wandLight2Stack, " gr",
                                                " wg",
                                                "l  ", 'g', ingotGoldStack, 'r', redstoneStack, 'w', wandLight1Stack, 'l', lapisLazuliStack);
		GameRegistry.addRecipe(wandLight3Stack, " dg",
                                                " wd",
                                                "i  ", 'd', diamondStack, 'g', glowstoneStack, 'w', wandLight2Stack, 'i', blockIronStack);
		GameRegistry.addRecipe(wandLight4Stack, " og",
                                                " wo",
                                                "b  ", 'o', obsidianStack, 'g', glowstoneStack, 'w', wandLight3Stack, 'b', blockGoldStack);
		GameRegistry.addRecipe(wandLight5Stack, " gc",
                                                " wg",
                                                "d  ", 'g', blockGoldStack, 'c', condensedLightStack, 'w', wandLight4Stack, 'd', blockDiamondStack);

	}

	public static void registerOreDict() {
		OreDictionary.registerOre("nuggetCoal", new ItemStack(nuggetCoal));
		OreDictionary.registerOre("nuggetCharcoal", new ItemStack(nuggetCharcoal));
		
	}
	
}