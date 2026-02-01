package flyinely.mcm.upstack;

import flyinely.mcm.upstack.registry.UpstackStackSizes;

public class UpstackCommon {
   
   public static void init() {
      Constants.LOG.info("Bootstrapped common init");
   }

   public static void onServerStarting() {
      Constants.LOG.info("Bootstrapped server starting handler");
      UpstackStackSizes.register(); // TODO: Should (also) do this on resource reload. Is doing it ONLY on resource reload sufficient? Probably not?
   }

   public static void onResourceReload() {
      Constants.LOG.info("Bootstrapped resource reload handler");
      UpstackStackSizes.register();
   }
}
