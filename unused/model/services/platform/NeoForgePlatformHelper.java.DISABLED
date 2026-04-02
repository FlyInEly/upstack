package flyinely.mcm.upstack.platform;

import flyinely.mcm.upstack.platform.services.IPlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.Collection;

public class NeoForgePlatformHelper implements IPlatformHelper {

   /* PLATFORM */

   @Override
   public String getName() {
      return "NeoForge";
   }

   /* PATHS */

   @Override
   public Path getRootDir() {
      return FMLPaths.GAMEDIR.get();
   }

   @Override
   public Path getConfigDir() {
      return FMLPaths.CONFIGDIR.get();
   }

   @Override
   public Path getModsDir() {
      return FMLPaths.MODSDIR.get();
   }

   /* MODS */

   @Override
   public Collection<String> getModIds() {
      return ModList.get().getMods().stream().map(IModInfo::getModId).toList();
   }

   @Override
   public boolean isModLoaded(String id) {
      return ModList.get().isLoaded(id);
   }

   /* ENVIRONMENT */

   @Override
   public boolean isDevelopment() {
      return !FMLLoader.isProduction();
   }

   @Override
   public boolean isProduction() {
      return FMLLoader.isProduction();
   }
}
