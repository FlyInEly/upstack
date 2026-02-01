package flyinely.mcm.upstack;

import flyinely.mcm.upstack.config.Config;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.jetbrains.annotations.NotNull;

@Mod(Constants.MOD_ID)
public class UpstackNeoforge {

   public UpstackNeoforge(@NotNull FMLModContainer container) {
      // Bootstrap common init
      UpstackCommon.init();

      // Register config w/screen
      container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
      container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new); // nice! need translation keys TODO

      Constants.LOG.info("Finished NeoForge init");
   }

   // listener for datapack reload: how?

   @SubscribeEvent
   public void onServerStarting(ServerStartingEvent event) {
      // Bootstrap. TODO: Verify that this works even if it item component modifications run only on the server, not the client. Are item components synced to the client?
      UpstackCommon.onServerStarting();
   }
}
