package flyinely.mcm.upstack.util;

import flyinely.mcm.upstack.mixin.registry.ItemAccessor;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Utility for modifying {@link net.minecraft.world.item.Item} components.
 */
public class ItemComponentUtil {

   /**
    * Sets each component to its given mapped value.
    *
    * @param item the target item
    * @param map  the component map
    * @implNote Replaces the item's current map with the given map.
    */
   public static void setAll(@NotNull Item item, @NotNull DataComponentMap map) {
      ((ItemAccessor) item).setComponents(map);
   }

   /**
    * Sets each component to its given mapped value, unless that value is {@code null}.
    *
    * @param item the target item
    * @param map  the component map
    * @implNote Replaces the item's current map with a modified copy: the given map composed onto the current map.
    * @see DataComponentMap#composite(DataComponentMap, DataComponentMap)
    */
   public static void setNotNull(@NotNull Item item, @NotNull DataComponentMap map) {
      setAll(item, DataComponentMap.composite(item.components(), map));
   }

   /**
    * Sets the component to the given value.
    *
    * @param item      the target item
    * @param component the target component
    * @param value     the component value
    * @param <T>       of the component value
    * @implNote Replaces the item's current map with a modified copy: the current map with the given modification.
    */
   public static <T> void set(@NotNull Item item, @NotNull DataComponentType<T> component, @Nullable T value) {
      setAll(item, DataComponentMap.builder().addAll(item.components()).set(component, value).build());
   }

   /**
    * Sets the {@link DataComponents#MAX_STACK_SIZE} component to the given value, clamped to
    * [1, {@link Item#ABSOLUTE_MAX_STACK_SIZE}].
    *
    * @param item  the target item
    * @param value the max stack size value
    */
   public static void setMaxStackSize(@NotNull Item item, int value) {
      set(item, DataComponents.MAX_STACK_SIZE, Math.clamp(value, 1, Item.ABSOLUTE_MAX_STACK_SIZE));
   }
}
