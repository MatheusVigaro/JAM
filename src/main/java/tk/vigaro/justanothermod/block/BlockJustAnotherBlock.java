package tk.vigaro.justanothermod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tk.vigaro.justanothermod.References;

public class BlockJustAnotherBlock extends Block {

	protected BlockJustAnotherBlock(Material material, String unlocalizedName, String tool, int harvestLevel) {
		super(material);
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(References.ID + ":" + unlocalizedName);
		this.setCreativeTab(References.creativeTab);
		this.setHarvestLevel(tool, harvestLevel);
		
	}

}
