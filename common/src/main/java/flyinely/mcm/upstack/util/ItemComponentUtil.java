package flyinely.mcm.upstack.util;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.mixin.registry.ItemAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility for modifying {@link net.minecraft.world.item.Item} components. Generally, desired modifications
 * should be made on server start and resource reload; mod initialization is too early for methods like
 * {@link #set(TagKey, DataComponentType, Object)}, which have no effect if tags are unpopulated.
 * <p>
 * Each method logs the tag and/or item(s) being modified. Due to the aforementioned expectation that
 * method calls are infrequent, the logs should be more helpful for debugging than spammy.
 */
public class ItemComponentUtil {

   public static final int ABSOLUTE_MIN_STACK_SIZE = 1; // hardcoded: value copied from DataComponents.MAX_STACK_SIZE
   public static final int ABSOLUTE_MAX_STACK_SIZE = Item.ABSOLUTE_MAX_STACK_SIZE;

   private static final Logger LOG = LoggerFactory.getLogger(Constants.LOG.getName() + "/" + ItemComponentUtil.class.getSimpleName());

   // GENERAL

   /**
    * Sets each component to its given mapped value.
    *
    * @param item the target item
    * @param map  the component map
    * @implNote Replaces the item's current map with the given map.
    */
   public static void setAll(@NotNull Item item, @NotNull DataComponentMap map) {
      LOG.info("Modifying {}", item);
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
   @ApiStatus.Experimental // Currently unused, and quick to replicate. Documentation is good though.
   public static void setAllNonNull(@NotNull Item item, @NotNull DataComponentMap map) {
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

   // TODO NEXT: Hook UpstackStackSizes.register() to resource reload
   // TODO NEXT: Make other setters support item holders or some other abstraction that allows for modifying modded items

   /**
    * Sets the component to the given value, on all items in the given tag.
    *
    * @param tag       the target tag
    * @param component the target component
    * @param value     the component value
    * @param <T>       of the component value
    * @see #set(Item, DataComponentType, Object)
    */
   public static <T> void set(@NotNull TagKey<Item> tag, @NotNull DataComponentType<T> component, @Nullable T value) {
      if (BuiltInRegistries.ITEM.getTag(tag).isEmpty()) {
         LOG.warn("Tried to modify {} of all items in #{}, but the tag is empty. Was modification attempted too early?", component, tag.location());
      } else {
         LOG.info("Modifying {} of all items in #{}", component, tag.location());
         BuiltInRegistries.ITEM.getTag(tag).get().stream().filter(Holder::isBound).map(Holder::value).forEach(item -> set(item, component, value));
      }
   }

   // SPECIFIC

   /**
    * Sets the {@link DataComponents#MAX_STACK_SIZE} component to the given value,
    * if the value is within [1, {@link Item#ABSOLUTE_MAX_STACK_SIZE}].
    *
    * @param item  the target item
    * @param value the max stack size value
    */
   public static void setMaxStackSize(@NotNull Item item, int value) {
      if (value >= ABSOLUTE_MIN_STACK_SIZE && value <= ABSOLUTE_MAX_STACK_SIZE) {
         set(item, DataComponents.MAX_STACK_SIZE, value);
      }
   }

   /**
    * Sets the {@link DataComponents#MAX_STACK_SIZE} component to the given value on all items
    * in the tag, if the value is within [1, {@link Item#ABSOLUTE_MAX_STACK_SIZE}].
    *
    * @param tag   the target tag
    * @param value the max stack size value
    */
   public static void setMaxStackSize(@NotNull TagKey<Item> tag, int value) {
      if (value >= ABSOLUTE_MIN_STACK_SIZE && value <= ABSOLUTE_MAX_STACK_SIZE) {
         set(tag, DataComponents.MAX_STACK_SIZE, value);
      }
   }
}
