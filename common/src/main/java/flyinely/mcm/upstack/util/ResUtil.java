package flyinely.mcm.upstack.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ResUtil {

   public static @NotNull TagKey<Item> itemTag(ResourceLocation id) {
      return TagKey.create(Registries.ITEM, id);
   }

   public static @NotNull TagKey<Item> itemTag(String location) {
      return TagKey.create(Registries.ITEM, id(location));
   }

   @Contract("_, _ -> new")
   public static @NotNull ResourceLocation id(String namespace, String path) {
      return ResourceLocation.fromNamespaceAndPath(namespace, path);
   }

   public static @NotNull ResourceLocation id(String location) {
      return ResourceLocation.parse(location);
   }

}
