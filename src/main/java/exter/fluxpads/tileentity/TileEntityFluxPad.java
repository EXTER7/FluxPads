package exter.fluxpads.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.TileEnergyHandler;

public class TileEntityFluxPad extends TileEnergyHandler implements IEnergyHandler
{
  protected EnergyStorage energy;

  public TileEntityFluxPad()
  {
    energy = new EnergyStorage(1, 0);
  }
  
  public TileEntityFluxPad setStats(int capacity,int transfer)
  {
    energy.setCapacity(capacity);
    energy.setMaxTransfer(transfer);
    return this;
  }

  @Override
  public void readFromNBT(NBTTagCompound nbt)
  {
    super.readFromNBT(nbt);
    energy.readFromNBT(nbt);
  }

  @Override
  public void writeToNBT(NBTTagCompound nbt)
  {
    super.writeToNBT(nbt);
    energy.writeToNBT(nbt);
  }

  @Override
  public boolean canConnectEnergy(ForgeDirection from)
  {
    return from == ForgeDirection.DOWN;
  }

  @Override
  public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
  {
    if(from != ForgeDirection.DOWN)
    {
      return 0;
    }
    return energy.receiveEnergy(maxReceive, simulate);
  }

  @Override
  public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
  {
    return 0;
  }

  @Override
  public int getEnergyStored(ForgeDirection from)
  {
    return energy.getEnergyStored();
  }

  @Override
  public int getMaxEnergyStored(ForgeDirection from)
  {
    return energy.getMaxEnergyStored();
  }
  
  public void chargeItems(IInventory inventory)
  {
    List<ItemStack> rf_items = new ArrayList<ItemStack>();

    for(int i = 0; i < inventory.getSizeInventory(); i++)
    {
      ItemStack stack = inventory.getStackInSlot(i);
      if(stack != null && stack.getItem() instanceof IEnergyContainerItem)
      {
        rf_items.add(stack);
      }
    }
    
    
    if(rf_items.size() > 0)
    {
      int supply = energy.extractEnergy(energy.getMaxExtract(), true) / rf_items.size();
      int used = 0;
      for(ItemStack stack : rf_items)
      {
        IEnergyContainerItem energy_item = (IEnergyContainerItem) stack.getItem();
        used += energy_item.receiveEnergy(stack, supply, false);
      }
      energy.extractEnergy(used, true);
    }
  }
}
