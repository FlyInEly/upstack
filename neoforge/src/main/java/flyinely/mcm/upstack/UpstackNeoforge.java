package flyinely.mcm.upstack;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Constants.MOD_ID)
public class UpstackNeoforge {

   public UpstackNeoforge(IEventBus eventBus) {
      // Use NeoForge to bootstrap the Common mod.
      UpstackCommon.init();
   }

   @SubscribeEvent
   public void onServerStarting(ServerStartingEvent event) {
      // Bootstrap. TODO: Verify that this works even if it item component modifications run only on the server, not the client. Are item components synced to the client?
      UpstackCommon.onServerStarting();
   }
}
