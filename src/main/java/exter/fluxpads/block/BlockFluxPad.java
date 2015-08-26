package exter.fluxpads.block;

import exter.fluxpads.config.FluxPadsConfig;
import exter.fluxpads.creativetab.CreativeTabFluxPads;
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
  static public final int FLUXPAD_BASIC = 0;
  static public final int FLUXPAD_HARDENED = 1;
  static public final int FLUXPAD_REINFORCED = 2;
  static public final int FLUXPAD_RESONANT = 3;

  private IIcon[] top_icons;
  private IIcon[] bottom_icons;
  private IIcon[] sides_icons;
  
  static private final String[] TOP_ICON_PATHS =
  {
    "fluxpads:pad_basic_top",
    "fluxpads:pad_hardened_top",
    "fluxpads:pad_reinforced_top",
    "fluxpads:pad_resonant_top"
  };

  static private final String[] BOTTOM_ICON_PATHS =
  {
    "fluxpads:pad_basic_bottom",
    "fluxpads:pad_hardened_bottom",
    "fluxpads:pad_reinforced_bottom",
    "fluxpads:pad_resonant_bottom"
  };

  static private final String[] SIDES_ICON_PATHS =
  {
    "fluxpads:pad_basic_sides",
    "fluxpads:pad_hardened_sides",
    "fluxpads:pad_reinforced_sides",
    "fluxpads:pad_resonant_sides"
  };

  public BlockFluxPad()
  {
    super(Material.iron);
    setBlockName("fluxPad");
    setHardness(3.0F);
    setResistance(5.0F);
    setCreativeTab(CreativeTabFluxPads.tab);
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
      te.chargeInventory((EntityPlayer)player);
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
    switch(meta)
    {
      case FLUXPAD_BASIC:
        return new TileEntityFluxPad(FluxPadsConfig.basic_capacity,FluxPadsConfig.basic_rate);
      case FLUXPAD_HARDENED:
        return new TileEntityFluxPad(FluxPadsConfig.hardened_capacity,FluxPadsConfig.hardened_rate);
      case FLUXPAD_REINFORCED:
        return new TileEntityFluxPad(FluxPadsConfig.reinforced_capacity,FluxPadsConfig.reinforced_rate);
      case FLUXPAD_RESONANT:
        return new TileEntityFluxPad(FluxPadsConfig.resonant_capacity,FluxPadsConfig.resonant_rate);
    }
    return null;
  }

  @Override
  public void registerBlockIcons(IIconRegister register)
  {
    int i;
    top_icons = new IIcon[TOP_ICON_PATHS.length];
    for(i = 0; i < TOP_ICON_PATHS.length; i++)
    {
      top_icons[i] = register.registerIcon(TOP_ICON_PATHS[i]);
    }
    bottom_icons = new IIcon[BOTTOM_ICON_PATHS.length];
    for(i = 0; i < BOTTOM_ICON_PATHS.length; i++)
    {
      bottom_icons[i] = register.registerIcon(BOTTOM_ICON_PATHS[i]);
    }
    sides_icons = new IIcon[SIDES_ICON_PATHS.length];
    for(i = 0; i < SIDES_ICON_PATHS.length; i++)
    {
      sides_icons[i] = register.registerIcon(SIDES_ICON_PATHS[i]);
    }
  }

  @Override
  public IIcon getIcon(int side, int meta)
  {
    switch(side)
    {
      case 0:
        return bottom_icons[meta];
      case 1:
        return top_icons[meta];
      default:
        return sides_icons[meta];
    }
  }
}
