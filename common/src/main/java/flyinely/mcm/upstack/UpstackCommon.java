package flyinely.mcm.upstack;

import flyinely.mcm.upstack.registry.MStackSizes;

public class UpstackCommon {
   
   public static void init() {
      Constants.LOG.info("Bootstrapped common init");
   }

   public static void onServerStarting() {
      Constants.LOG.info("Bootstrapped server starting handler");
      MStackSizes.apply();
   }

   public static void onConfigReloading() {
      Constants.LOG.info("Bootstrapped config reloading handler");
		// Intentionally do not apply stack sizes until world restart, to give chances to revert a decision.
   }
}
