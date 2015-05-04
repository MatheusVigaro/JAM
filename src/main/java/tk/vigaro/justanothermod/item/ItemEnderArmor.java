package tk.vigaro.justanothermod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import tk.vigaro.justanothermod.CreativeTab;
import tk.vigaro.justanothermod.Reference;

public class ItemEnderArmor extends ItemArmor {
	public ItemEnderArmor(String unlocalizedName, ArmorMaterial material, int type) {
		super(material, 0, type);
		setTextureName(Reference.ID + ":" + unlocalizedName);
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(CreativeTab.justAnotherMod);

	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return Reference.ID + ":textures/armor/" + ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + (this.armorType == 2 ? "2" : "1") + ".png";
	}

}
