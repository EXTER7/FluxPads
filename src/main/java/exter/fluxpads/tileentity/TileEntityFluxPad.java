package exter.fluxpads.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

import baubles.api.BaublesApi;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.TileEnergyHandler;

public class TileEntityFluxPad extends TileEnergyHandler implements IEnergyHandler
{
  private EnergyStorage energy;

  public TileEntityFluxPad()
  {
    energy = new EnergyStorage(1, 0);
  }

  public TileEntityFluxPad(int capacity,int transfer)
  {
    energy = new EnergyStorage(capacity, transfer);
  }

  @Override
  public void readFromNBT(NBTTagCompound nbt)
  {
    super.readFromNBT(nbt);
    energy.setCapacity(nbt.getInteger("Capacity"));
    energy.setMaxTransfer(nbt.getInteger("Transfer"));
    energy.readFromNBT(nbt);
  }

  @Override
  public void writeToNBT(NBTTagCompound nbt)
  {
    super.writeToNBT(nbt);
    nbt.setInteger("Capacity",energy.getMaxEnergyStored());
    nbt.setInteger("Transfer",energy.getMaxExtract());
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
  
  private void findRFItems(IInventory inventory,List<ItemStack> rf_items)
  {
    if(inventory == null)
    {
      return;
    }
    for(int i = 0; i < inventory.getSizeInventory(); i++)
    {
      ItemStack stack = inventory.getStackInSlot(i);
      if(stack != null && stack.getItem() instanceof IEnergyContainerItem)
      {
        IEnergyContainerItem rf_item = (IEnergyContainerItem) stack.getItem();
        if(rf_item.getEnergyStored(stack) < rf_item.getMaxEnergyStored(stack))
        {
          rf_items.add(stack);
        }
      }
    }
  }

  public void chargeInventory(EntityPlayer player)
  {
    List<ItemStack> rf_items = new ArrayList<ItemStack>();

    findRFItems(player.inventory,rf_items);
    findRFItems(BaublesApi.getBaubles(player),rf_items);
    
    if(rf_items.size() > 0)
    {
      int supply = energy.extractEnergy(energy.getMaxExtract(), true) / rf_items.size();
      int used = 0;
      for(ItemStack stack : rf_items)
      {
        IEnergyContainerItem rf_item = (IEnergyContainerItem) stack.getItem();
        used += rf_item.receiveEnergy(stack, supply, false);
      }
      energy.extractEnergy(used, false);
    }
  }
}
