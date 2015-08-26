package exter.fluxpads.config;

import net.minecraftforge.common.config.Configuration;


public class FluxPadsConfig
{
  static public int basic_capacity;
  static public int basic_rate;

  static public int hardened_capacity;
  static public int hardened_rate;
  
  static public int reinforced_capacity;
  static public int reinforced_rate;

  static public int resonant_capacity;
  static public int resonant_rate;

  static public void load(Configuration config)
  {
    basic_capacity = config.get("fluxpads", "fluxpad.basic.capacity", 10000).getInt(10000);    
    basic_rate = config.get("fluxpads", "fluxpad.basic.rate", 100).getInt(100); 

    hardened_capacity = config.get("fluxpads", "fluxpad.hardened.capacity", 50000).getInt(50000);    
    hardened_rate = config.get("fluxpads", "fluxpad.hardened.rate", 400).getInt(400);

    reinforced_capacity = config.get("fluxpads", "fluxpad.reinforced.capacity", 500000).getInt(500000);    
    reinforced_rate = config.get("fluxpads", "fluxpad.reinforced.rate", 2000).getInt(2000);

    resonant_capacity = config.get("fluxpads", "fluxpad.resonant.capacity", 2000000).getInt(2000000);    
    resonant_rate = config.get("fluxpads", "fluxpad.resonant.rate", 8000).getInt(8000);
  }
}
