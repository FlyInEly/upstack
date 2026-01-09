package flyinely.mcm.upstack.platform;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.platform.services.IPlatformHelper;
import org.jetbrains.annotations.Contract;

import java.util.ServiceLoader;


public class Services {

   @Contract(pure = true)
   private Services() {}

   /**
    * Service for queries about the current platform.
    */
   public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

   /**
    * Loads the environment-specific implementation of the service.
    * <p>
    * The implementation is specified by the fully qualified class name in a META-INF/services file,
    * whose name is the service's fully qualified class name.
    *
    * @param service the service interface
    * @param <T>     of the service
    * @return the service implementation loaded
    * @see ServiceLoader
    */
   public static <T> T load(Class<T> service) {
      final T loadedService = ServiceLoader.load(service).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + service.getName()));
      Constants.LOG.debug("Loaded {} for service {}", loadedService, service);
      return loadedService;
   }
}
