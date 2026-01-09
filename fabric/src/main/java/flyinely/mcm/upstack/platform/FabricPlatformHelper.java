package flyinely.mcm.upstack.platform;

import flyinely.mcm.upstack.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public class FabricPlatformHelper implements IPlatformHelper {

   /* PLATFORM */

   @Override
   public String getName() {
      return "Fabric";
   }

   /* PATHS */

   @Override
   public Path getRootDir() {
      return FabricLoader.getInstance().getGameDir();
   }

   @Override
   public Path getConfigDir() {
      return FabricLoader.getInstance().getConfigDir();
   }

   /**
    * Gets the game's mods directory: {@code ./mods}.
    * @return the mods directory path
    */
   @Override
   public Path getModsDir() {
      return FabricLoader.getInstance().getGameDir().resolve("mods");
   }

   /* MODS */

   @Override
   public Collection<String> getModIds() {
      return FabricLoader.getInstance().getAllMods().stream().map(ModContainer::getMetadata).map(ModMetadata::getId).toList();
   }

   @Override
   public boolean isModLoaded(String id) {
      return FabricLoader.getInstance().isModLoaded(id);
   }

   /* ENVIRONMENT */

   @Override
   public boolean isDevelopment() {
      return FabricLoader.getInstance().isDevelopmentEnvironment();
   }

   @Override
   public boolean isProduction() {
      return !FabricLoader.getInstance().isDevelopmentEnvironment();
   }
}