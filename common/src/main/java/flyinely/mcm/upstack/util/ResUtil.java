package flyinely.mcm.upstack.util;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ResUtil {

	@SuppressWarnings("unused") // api
   @Contract("_, _ -> new")
   public static @NotNull ResourceLocation id(String namespace, String path) {
      return ResourceLocation.fromNamespaceAndPath(namespace, path);
   }

   public static @NotNull ResourceLocation id(String location) {
      return ResourceLocation.parse(location);
   }

}
