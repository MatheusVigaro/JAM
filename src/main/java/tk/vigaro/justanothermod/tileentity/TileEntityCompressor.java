package tk.vigaro.justanothermod.tileentity;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tk.vigaro.justanothermod.References;
import tk.vigaro.justanothermod.config.ConfigMachines;
import tk.vigaro.justanothermod.item.ItemCompressedBlock;
import tk.vigaro.justanothermod.item.JAMItems;
import cofh.api.energy.TileEnergyHandler;
import cofh.lib.util.helpers.InventoryHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;

public class TileEntityCompressor extends TileEnergyHandler {
	ForgeDirection orientation;
	int tickCounter = 0; 
	

	@Override
	public void updateEntity(){
		if (tickCounter >= 20){
			if (storage.getEnergyStored() >= ConfigMachines.compressorEnergyPerCycle && tryCompress()){
				tickCounter = 0;
				storage.extractEnergy(ConfigMachines.compressorEnergyPerCycle, false);
			}else{
				tickCounter = -80;
			}
		}else{
			tickCounter++;
		}
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		tagCompound.setInteger("orientation", orientation.ordinal());
		super.writeToNBT(tagCompound);
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		this.orientation = ForgeDirection.getOrientation(tagCompound.getInteger("orientation"));
		super.readFromNBT(tagCompound);
	}
	
	public void setOrientation(ForgeDirection orientation){
		this.orientation = orientation;
	}
	
	public boolean tryCompress(){
		if (orientation != null){
			int ix = this.xCoord;
			int iz = this.zCoord;
			int ox = this.xCoord;
			int oz = this.zCoord;
			
			switch(orientation){
			case SOUTH:
				ix--;
				ox++;
				break;
			case NORTH:
				ix++;
				ox--;
				break;
			case WEST:
				iz--;
				oz++;
				break;
			case EAST:
				iz++;
				oz--;
				break;
			default:
				return false;	
			}
			World world = getWorldObj();

			TileEntity tileInput = world.getTileEntity(ix, this.yCoord, iz); 
			TileEntity tileOutput = world.getTileEntity(ox, this.yCoord, oz);
			Map<String, SortedSet<Integer>> slotMap = new HashMap<String, SortedSet<Integer>>();
			
			if (tileInput instanceof IInventory && tileOutput instanceof IInventory){
				IInventory input = (IInventory)tileInput;
				IInventory output = (IInventory)tileOutput;
				
				for (int slot = 0; slot < input.getSizeInventory(); slot++){
					ItemStack stack = input.getStackInSlot(slot);
					boolean isCompressedBlock;
					if (stack != null){
						isCompressedBlock = (stack.getItem() instanceof ItemCompressedBlock);
					} else {
						isCompressedBlock = false;
					}

					if (!isCompressedBlock && ((input instanceof ISidedInventory && !((ISidedInventory)input).canExtractItem(slot, null, ForgeDirection.DOWN.ordinal())) || stack == null || !(stack.getItem() instanceof ItemBlock) || Block.getBlockFromItem(stack.getItem()).getRenderType() != 0 || ((!Block.getBlockFromItem(stack.getItem()).isNormalCube() || !Block.getBlockFromItem(stack.getItem()).isOpaqueCube()) && Block.getBlockFromItem(stack.getItem()).getMaterial() != Material.glass ))) {
						continue;
	                }

                    if (slotMap.containsKey(stack.getUnlocalizedName() + ":" + stack.getItemDamage())) {
                        slotMap.get(stack.getUnlocalizedName() + ":" + stack.getItemDamage()).add(slot);
                    } else {
                        SortedSet<Integer> slotList = new TreeSet<Integer>();
                        slotList.add(slot);
                        slotMap.put(stack.getUnlocalizedName() + ":" + stack.getItemDamage(), slotList);
                    }

                    if (stack.stackSize >= 9) {
                    	ItemStack result;
                        if (isCompressedBlock){
                        	if (stack.getItemDamage() > 5){
                        		continue;
                        	}
                        	result = stack.copy();
                        	result.stackSize = 1;
                        	result.setItemDamage(stack.getItemDamage()+1);
                        	
                        } else {
                            result = new ItemStack(JAMItems.compressedBlock);
                        	UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor(Block.getBlockFromItem(stack.getItem()));
                        	result.stackTagCompound = new NBTTagCompound();
                        	result.stackTagCompound.setString("contained", identifier.modId + ":" + identifier.name + ":" + stack.getItemDamage());
                        	
                        }
                        ItemStack testStack = InventoryHelper.simulateInsertItemStackIntoInventory(output, result, 1);
                        if (testStack == null) {
                           	input.decrStackSize(slot, 9);
                           	InventoryHelper.insertItemStackIntoInventory(output, result, 1);
                           	return true;

                        }
                    }

				}
				for (Map.Entry<String,SortedSet<Integer>> entry : slotMap.entrySet()) {
					if (entry.getValue().size() > 1) {
						SortedSet<Integer> slots = entry.getValue();
						while (slots.size() > 1) {
							if (input.getStackInSlot(slots.first()) == null || !(input.getStackInSlot(slots.first()).getUnlocalizedName() + ":" + input.getStackInSlot(slots.first()).getItemDamage()).equals(entry.getKey()) || input.getStackInSlot(slots.first()).stackSize >= input.getStackInSlot(slots.first()).getMaxStackSize()) {
                             slots.remove(slots.first());
                             	continue;
							}
							if (input.getStackInSlot(slots.last()) == null || !(input.getStackInSlot(slots.last()).isItemEqual(input.getStackInSlot(slots.first()))) || !ItemStack.areItemStackTagsEqual(input.getStackInSlot(slots.first()), input.getStackInSlot(slots.last()))) {
                             slots.remove(slots.last());
                             continue;
							}
							if (input.getStackInSlot(slots.first()).stackSize + input.getStackInSlot(slots.last()).stackSize <= input.getStackInSlot(slots.first()).getMaxStackSize()) {
								input.getStackInSlot(slots.first()).stackSize += input.getStackInSlot(slots.last()).stackSize;
								input.setInventorySlotContents(slots.last(), null);
							} else {
								int spaceRemain = input.getStackInSlot(slots.first()).getMaxStackSize() - input.getStackInSlot(slots.first()).stackSize;
								input.getStackInSlot(slots.first()).stackSize += spaceRemain;
								input.decrStackSize(slots.last(), spaceRemain);
							}
						}
					}
				}
			}
		}
		return false;	
	}
}
