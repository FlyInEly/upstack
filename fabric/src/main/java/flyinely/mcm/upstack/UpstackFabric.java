package flyinely.mcm.upstack;

import net.fabricmc.api.ModInitializer;

public class UpstackFabric implements ModInitializer {

   @Override
   public void onInitialize() {
      // Use Fabric to bootstrap the Common mod.
      UpstackCommon.init();
   }
}
