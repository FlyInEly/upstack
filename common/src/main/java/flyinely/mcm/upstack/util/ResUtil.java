package flyinely.mcm.upstack.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ResUtil {

   @Contract("_, _ -> new")
   public static @NotNull ResourceLocation id(String namespace, String path) {
      return ResourceLocation.fromNamespaceAndPath(namespace, path);
   }

   public static @NotNull ResourceLocation id(String location) {
      return ResourceLocation.parse(location);
   }

}
