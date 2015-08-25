package exter.fluxpads;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import exter.fluxpads.proxy.CommonFluxPadsProxy;

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
  public static ModFluxPads instance;

  @SidedProxy(
      clientSide = "exter.fluxpads.proxy.ClientFluxPadsProxy",
      serverSide = "exter.fluxpads.proxy.CommonFluxPadsProxy")
  public static CommonFluxPadsProxy proxy;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    proxy.preInit();
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event)
  {
    proxy.init();
  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
    proxy.postInit();
  }
}

