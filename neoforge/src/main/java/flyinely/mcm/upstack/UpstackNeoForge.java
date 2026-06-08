package flyinely.mcm.upstack;

import flyinely.mcm.upstack.config.ClientConfig;
import flyinely.mcm.upstack.config.CommonConfig;
import flyinely.mcm.upstack.config.ServerConfig;
import flyinely.mcm.upstack.event.TooltipHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.jetbrains.annotations.NotNull;

@Mod(Constants.MOD_ID)
@EventBusSubscriber(modid = Constants.MOD_ID)
public class UpstackNeoForge {
	
	public UpstackNeoForge(@NotNull FMLModContainer container) {
		// Bootstrap common init
		UpstackCommon.init();
		
		// Register configs
		container.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
		container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC); // automatically client-only
		container.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC); // automatically server-only; synced
		
		if (FMLLoader.getDist().isClient()) {
			// Register config screen (client-only)
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}
		
		Constants.LOG.info("Finished NeoForge init.");
	}
	
	@SubscribeEvent
	public static void onServerStarting(ServerStartingEvent event) {
		UpstackCommon.onServerStarting();
	}
	
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		TooltipHandler.onHandleTooltip(event.getItemStack(), event.getToolTip());
	}
}
