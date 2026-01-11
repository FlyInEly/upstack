package flyinely.mcm.upstack;

import flyinely.mcm.upstack.platform.Services;
import flyinely.mcm.upstack.registry.UpstackStackSizes;

public class UpstackCommon {

   public static void init() {

      if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
         Constants.LOG.info("{} is loaded!", Constants.MOD_NAME);
      }
      UpstackStackSizes.register();
   }
}
