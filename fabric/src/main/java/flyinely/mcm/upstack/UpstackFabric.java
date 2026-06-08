package flyinely.mcm.upstack;

import flyinely.mcm.upstack.config.ClientConfig;
import flyinely.mcm.upstack.config.CommonConfig;
import flyinely.mcm.upstack.event.TooltipHandler;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public class UpstackFabric implements ModInitializer {

   @Override
   public void onInitialize() {
      // Bootstrap common init
      UpstackCommon.init();

      // Register event listeners
      ServerLifecycleEvents.SERVER_STARTING.register(s -> UpstackCommon.onServerStarting());

      // Register configs
      NeoForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.COMMON, CommonConfig.SPEC);
		NeoForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.CLIENT, ClientConfig.SPEC); // automatically client-only
		
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			// Register config screen (client-only)
         ConfigScreenFactoryRegistry.INSTANCE.register(Constants.MOD_ID, ConfigurationScreen::new);

         // Register tooltip handler (client-only)
         ItemTooltipCallback.EVENT.register((stack, ignoredContext, ignoredFlag, list) ->
               TooltipHandler.onHandleTooltip(stack, list));
      }

      Constants.LOG.info("Finished Fabric init.");
   }

}
