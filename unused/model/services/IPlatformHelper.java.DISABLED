package flyinely.mcm.upstack.platform.services;

import java.nio.file.Path;
import java.util.Collection;

/**
 * Service for queries about the current platform.
 */
@SuppressWarnings("unused") // api
public interface IPlatformHelper {

   /* PLATFORM */

   /**
    * Gets the current platform's name.
    *
    * @return the platform's name
    */
   String getName();

   /* PATHS */

   /**
    * Gets the game's root directory.
    * @return the root directory path
    */
   Path getRootDir();

   /**
    * Gets the game's config directory.
    * @return the config directory path
    */
   Path getConfigDir();

   /**
    * Gets the game's mods directory.
    * @return the mods directory path
    */
   Path getModsDir();

   /* MODS */

   /**
    * Gets a collection of loaded mod ids.
    *
    * @return a collection of loaded mod ids
    */
   Collection<String> getModIds();

   /**
    * Checks if a mod with the given id is loaded.
    *
    * @param id the mod id to check
    * @return true if the mod is loaded, false otherwise
    */
   boolean isModLoaded(String id);

   /* ENVIRONMENT */

   /**
    * Checks if the game is running in a development environment.
    *
    * @return true if in a development environment, false otherwise
    */
   boolean isDevelopment();

   /**
    * Checks if the game is running in a production environment.
    *
    * @return true if in a production environment, false otherwise
    */
   boolean isProduction();

   /**
    * Gets the current environment's name: {@code development} or {@code production}.
    *
    * @return the environment's name
    */
   default String getEnvironmentName() {
      return isDevelopment() ? "development" : "production";
   }
}
