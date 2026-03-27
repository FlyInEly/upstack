package flyinely.mcm.upstack;

import flyinely.mcm.upstack.registry.MStackSizes;

public class UpstackCommon {
   
   public static void init() {
      Constants.LOG.info("Bootstrapped common init");
   }

   public static void onServerStarting() {
      Constants.LOG.info("Applying max stack size modifications...");
      MStackSizes.apply();
		Constants.LOG.info("Done applying max stack size modifications.");
   }
}
