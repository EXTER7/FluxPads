package exter.fluxpads;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import exter.fluxpads.block.BlockFluxPad;
import exter.fluxpads.item.ItemBlockMulti;
import exter.fluxpads.proxy.CommonFluxPadsProxy;
import exter.fluxpads.tileentity.TileEntityFluxPad;

@Mod(
    modid = "fluxpads",
    name = "FluxPads",
    version = "1.0.0.0",
    dependencies =
      "required-after:ThermalFoundation;" +
      "after:ThermalExpansion;")
public class ModFluxPads
{
  @Mod.Instance
  static public ModFluxPads instance;

  static public BlockFluxPad block;
  
  @SidedProxy(
      clientSide = "exter.fluxpads.proxy.ClientFluxPadsProxy",
      serverSide = "exter.fluxpads.proxy.CommonFluxPadsProxy")
  static public CommonFluxPadsProxy proxy;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    proxy.preInit();
    block = new BlockFluxPad();
    GameRegistry.registerBlock(block, ItemBlockMulti.class, "fluxPad");
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event)
  {
    proxy.init();
    GameRegistry.registerTileEntity(TileEntityFluxPad.class, "Flux_Pad");
  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
    proxy.postInit();
  }
}

