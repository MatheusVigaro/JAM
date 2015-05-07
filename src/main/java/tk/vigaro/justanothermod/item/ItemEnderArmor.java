package tk.vigaro.justanothermod.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import tk.vigaro.justanothermod.References;

public class ItemEnderArmor extends ItemArmor {
	public ItemEnderArmor(String unlocalizedName, ArmorMaterial material, int type) {
		super(material, 0, type);
		this.setTextureName(References.ID + ":" + unlocalizedName);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(References.creativeTab);

	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return References.ID + ":textures/armor/" + ItemInfo.ENDER_ARMOR_UNLOCALIZED_NAME + (this.armorType == 2 ? "2" : "1") + ".png";
	}

}
