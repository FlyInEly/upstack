package flyinely.mcm.upstack;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class UpstackForge {

   public UpstackForge(IEventBus eventBus) {
      // Use NeoForge to bootstrap the Common mod.
      UpstackCommon.init();
   }
}
