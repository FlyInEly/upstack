package flyinely.mcm.upstack;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class UpstackFabric implements ModInitializer {
   
   @Override
   public void onInitialize() {
      // Use Fabric to bootstrap the Common mod.
      UpstackCommon.init();
      ServerLifecycleEvents.SERVER_STARTING.register(s -> UpstackCommon.onServerStarting());
   }

}
