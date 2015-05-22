package tk.vigaro.justanothermod.handler;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import tk.vigaro.justanothermod.item.ItemCompressedBlock;

public class DecompressCraftingHandler implements IRecipe{

	@Override
	public boolean matches(InventoryCrafting inventory, World world) {
		int m = 0;
		for (int i = 0; i < inventory.getSizeInventory(); i++){
			m += (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).getItem() instanceof ItemCompressedBlock) ? 1 : 0;
		}	
		return m == 1 ? true : false;
		
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		ItemStack input = null;
		for	(int i = 0; i < inventory.getSizeInventory(); i++){
			if (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).getItem() instanceof ItemCompressedBlock){
				input = inventory.getStackInSlot(i);
				break;
				
			}
		}
		
		if (input != null && input.stackTagCompound != null){
			String contained[] = input.stackTagCompound.getString("contained").split(":");
			if (input.getItemDamage() == 0) {
				return new ItemStack(Block.getBlockFromName(contained[0] + ":" + contained[1]), 9, Integer.parseInt(contained[2]));
				
			} else {
				ItemStack output = input.copy();
				output.setItemDamage(input.getItemDamage()-1);
				output.stackSize = 9;
				return output;
			}
		}
		
		return null;
	}

	@Override
	public int getRecipeSize() {
		return 1;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

}
