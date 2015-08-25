package exter.fluxpads.creativetab;

import exter.fluxpads.ModFluxPads;
import exter.fluxpads.block.BlockFluxPad;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabFluxPads extends CreativeTabs
{
  public static CreativeTabFluxPads tab = new CreativeTabFluxPads();

  private CreativeTabFluxPads()
  {
    super("fluxPads");
  }
  
  @Override
  public ItemStack getIconItemStack()
  {
    return new ItemStack(ModFluxPads.block,1,BlockFluxPad.FLUXPAD_BASIC);
  }

  @Override
  public Item getTabIconItem()
  {
    return null;
  }
}
