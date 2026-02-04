package flyinely.mcm.upstack;

import flyinely.mcm.upstack.config.Config;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeModConfigEvents;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public class UpstackFabric implements ModInitializer {

   @Override
   public void onInitialize() {
      // Bootstrap common init
      UpstackCommon.init();

      // Register event listeners
      ServerLifecycleEvents.SERVER_STARTING.register(s -> UpstackCommon.onServerStarting());

      // TODO: This ain't working. And neoforge's is completely not working.
      NeoForgeModConfigEvents.reloading(Constants.MOD_ID).register(c -> UpstackCommon.onConfigReloading());

      // Register config w/screen
      NeoForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.COMMON, Config.SPEC);
      ConfigScreenFactoryRegistry.INSTANCE.register(Constants.MOD_ID, ConfigurationScreen::new);

      Constants.LOG.info("Finished fabric init");
   }

}
