package flyinely.mcm.upstack;

import flyinely.mcm.upstack.config.Config;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.jetbrains.annotations.NotNull;

@Mod(Constants.MOD_ID)
public class UpstackNeoForge {

   public UpstackNeoForge(@NotNull FMLModContainer container) {
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
      UpstackCommon.onServerStarting(); // Bootstrap
   }

   @SubscribeEvent
   public void onConfigReload(ModConfigEvent.Reloading event) {
      UpstackCommon.onConfigReloading(); // Bootstrap
   }
}
