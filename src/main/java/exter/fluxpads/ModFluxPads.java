package exter.fluxpads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import exter.fluxpads.block.BlockFluxPad;
import exter.fluxpads.config.FluxPadsConfig;
import exter.fluxpads.item.ItemBlockMulti;
import exter.fluxpads.proxy.CommonFluxPadsProxy;
import exter.fluxpads.tileentity.TileEntityFluxPad;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(
    modid = "fluxpads",
    name = "FluxPads",
    version = "1.0.0.0",
    dependencies = 
      "required-after:Forge@[10.13.4.1448,);" +
      "required-after:ThermalFoundation;")
public class ModFluxPads
{
  @Mod.Instance
  static public ModFluxPads instance;

  static public BlockFluxPad block;
  
  
  public static Logger log = LogManager.getLogger("fluxpads");

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
    Configuration conf = new Configuration(event.getSuggestedConfigurationFile());
    FluxPadsConfig.load(conf);
    conf.save();
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event)
  {
    proxy.init();
    GameRegistry.registerTileEntity(TileEntityFluxPad.class, "Flux_Pad");

    // @formatter:off
    GameRegistry.addRecipe(new ShapedOreRecipe(
        new ItemStack(block,1,BlockFluxPad.FLUXPAD_BASIC),
        " R ",
        "IPI",
        " G ",
        'I', "ingotIron",
        'G', "gearTin",
        'P', new ItemStack(Blocks.heavy_weighted_pressure_plate),
        'R', Items.redstone));
    GameRegistry.addRecipe(new ShapedOreRecipe(
        new ItemStack(block,1,BlockFluxPad.FLUXPAD_HARDENED),
        " I ",
        "ICI",
        " G ",
        'I', "ingotInvar",
        'G', "gearElectrum",
        'C', new ItemStack(block,1,BlockFluxPad.FLUXPAD_BASIC)));
    GameRegistry.addRecipe(new ShapedOreRecipe(
        new ItemStack(block,1,BlockFluxPad.FLUXPAD_REINFORCED),
        " I ",
        "ICI",
        " G ",
        'I', "ingotLumium",
        'G', "gearSignalum",
        'C', new ItemStack(block,1,BlockFluxPad.FLUXPAD_HARDENED)));
    GameRegistry.addRecipe(new ShapedOreRecipe(
        new ItemStack(block,1,BlockFluxPad.FLUXPAD_RESONANT),
        " I ",
        "ICI",
        " G ",
        'I', "ingotSilver",
        'G', "gearEnderium",
        'C', new ItemStack(block,1,BlockFluxPad.FLUXPAD_REINFORCED)));
    // @formatter:on
    }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
    proxy.postInit();
  }
}

