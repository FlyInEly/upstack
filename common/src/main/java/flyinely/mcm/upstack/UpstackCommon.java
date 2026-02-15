package flyinely.mcm.upstack;

import flyinely.mcm.upstack.registry.StackSizes;
import net.minecraft.world.item.Items;

public class UpstackCommon {
   
   public static void init() {
      Constants.LOG.info("Bootstrapped common init");
   }

   public static void onServerStarting() {
      Constants.LOG.info("Bootstrapped server starting handler");
      StackSizes.apply(); // TODO: Should (also) do this on resource reload. Is doing it ONLY on resource reload sufficient? Probably not?
   }

   public static void onConfigReloading() {
      Constants.LOG.info("Bootstrapped config reloading handler");
      StackSizes.apply();
   }
}
