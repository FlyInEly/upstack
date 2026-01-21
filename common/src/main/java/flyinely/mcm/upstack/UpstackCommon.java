package flyinely.mcm.upstack;

import flyinely.mcm.upstack.platform.Services;
import flyinely.mcm.upstack.registry.UpstackStackSizes;

public class UpstackCommon {

   public static void init() {

      if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
         Constants.LOG.info("{} is loaded!", Constants.MOD_NAME);
      }
   }

   public static void onServerStarting() {
      Constants.LOG.info("Successfully bootstrapped server starting handler");
      UpstackStackSizes.register(); // TODO: Should (also) do this on resource reload. Is doing it ONLY on resource reload sufficient? Probably not?
   }
}
