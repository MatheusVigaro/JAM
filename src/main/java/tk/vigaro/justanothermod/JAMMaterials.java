package tk.vigaro.justanothermod;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import tk.vigaro.justanothermod.item.ItemInfo;

public class JAMMaterials {
	public static final ArmorMaterial enderArmorMaterial = EnumHelper.addArmorMaterial(ItemInfo.ENDER_ARMOR_MATERIAL_NAME, ItemInfo.ENDER_ARMOR_DURABILITY, ItemInfo.ENDER_ARMOR_REDUCTION, ItemInfo.ENDER_ARMOR_ENCHANTABILITY);

	public static final Material lightSourceMaterial = new MaterialTransparent(MapColor.airColor){
		@Override
		public int getMaterialMobility(){
			return 1;
		}
		@Override
		public boolean blocksMovement(){
			return false;
		}
		@Override
		public boolean isOpaque(){
			return false;
		}
	}.setReplaceable();
	
}