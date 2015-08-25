package exter.fluxpads.block;

import exter.fluxpads.tileentity.TileEntityFluxPad;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import java.util.List;

public class BlockFluxPad extends BlockContainer
{

  public static final int FLUXPAD_LEADSTONE = 0;
  public static final int FLUXPAD_HARDENED = 1;
  public static final int FLUXPAD_REDSTONE = 2;
  public static final int FLUXPAD_RESONANT = 3;

  public BlockFluxPad()
  {
    super(Material.iron);
    setBlockName("fluxPad");
    setHardness(3.0F);
    setResistance(5.0F);
  }

  @Override
  public int damageDropped(int metadata)
  {
    return metadata;
  }

  @Override
  public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
  {
    setBlockBounds(0, 0, 0, 1, 0.1875f, 1);
    super.setBlockBoundsBasedOnState(world, x, y, z);
  }

  @Override
  public void setBlockBoundsForItemRender()
  {
    setBlockBounds(0, 0, 0, 1, 0.1875f, 1);
    super.setBlockBoundsForItemRender();
  }

  @Override
  public boolean isOpaqueCube()
  {
    return false;
  }

  @Override
  public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
  {
    return false;
  }

  @Override
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
  {
    if(entity instanceof EntityPlayer)
    {
      EntityPlayer player = (EntityPlayer) entity;
      TileEntityFluxPad te = (TileEntityFluxPad) world.getTileEntity(x, y, z);
      te.chargeItems(player.inventory);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void getSubBlocks(Item item, CreativeTabs creativeTabs, @SuppressWarnings("rawtypes") List list)
  {
    for(int i = 0; i < 4; i++)
    {
      list.add(new ItemStack(this, 1, i));
    }
  }

  @Override
  public TileEntity createNewTileEntity(World world, int meta)
  {
    // TODO
    return null;
  }

  @Override
  public void registerBlockIcons(IIconRegister register)
  {
    // TODO
  }

  @Override
  public IIcon getIcon(int side, int meta)
  {
    // TODO
    return super.getIcon(side, meta);
  }
}
