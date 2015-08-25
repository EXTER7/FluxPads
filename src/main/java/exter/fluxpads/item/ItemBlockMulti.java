package exter.fluxpads.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMulti extends ItemBlock
{
  
  public ItemBlockMulti(Block block)
  {
    super(block);
    setHasSubtypes(true);
  }
  
  protected int GetSubIndex(ItemStack stack)
  {
    return stack.getItemDamage();
  }

  @Override
  public int getMetadata(int dmg)
  {
    return dmg;
  }
  
  @Override
  public final String getUnlocalizedName(ItemStack stack)
  {
    return super.getUnlocalizedName() + "." + stack.getItemDamage();
  }
}
