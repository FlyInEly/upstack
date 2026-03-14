package flyinely.mcm.upstack.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ItemUtil {

   @ApiStatus.Experimental
   public static @NotNull Optional<Item> fromId(ResourceLocation id) {
      return BuiltInRegistries.ITEM.getOptional(id);
   }

}
