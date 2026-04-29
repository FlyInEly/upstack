package flyinely.mcm.upstack.util;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.annotation.SoftSided;
import flyinely.mcm.upstack.mixin.ItemAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Utility for modifying {@link net.minecraft.world.item.Item} components. Generally, desired modifications
 * should be made on server start and resource reload; mod initialization is too early for methods like
 * {@link #set(TagKey, DataComponentType, Object)}, which have no effect if tags are unpopulated.
 * <p>
 * Each tag or item modification produces an info-level or debug-level log message respectively.
 * Due to the aforementioned expectation that method calls are infrequent, these debug messages should hopefully
 * be more helpful than spammy.
 *
 * @since 1.0.0
 */
@SoftSided.Server
public class ComponentUtil {

   private static final Logger LOG = LoggerFactory.getLogger(Constants.LOG.getName() + "/" + ComponentUtil.class.getSimpleName());

   /**
    * Sets each component to its given mapped value.
    *
    * @param item the target item
    * @param map  the component map
    * @implNote Replaces the item's current map with the given map.
    * @since 1.0.0
    */
   @SoftSided.Server
   public static void setAll(@NotNull Item item, @NotNull DataComponentMap map) {
      LOG.debug("Modifying {}", item);
      ((ItemAccessor) item).setComponents(map);
   }

   /**
    * Sets the component to the given value.
    *
    * @param item      the target item
    * @param component the target component
    * @param value     the component value
    * @param <T>       of the component value
    * @implNote Replaces the item's current map with a modified copy: the current map with the given modification.
    * @since 1.0.0
    */
   @SoftSided.Server
   public static <T> void set(@NotNull Item item, @NotNull DataComponentType<T> component, @Nullable T value) {
      setAll(item, DataComponentMap.builder().addAll(item.components()).set(component, value).build());
   }

   /**
    * Sets the component to the given value, on all items in the given tag.
    *
    * @param tag       the target tag
    * @param component the target component
    * @param value     the component value
    * @param <T>       of the component value
    * @see #set(Item, DataComponentType, Object)
    * @since 1.0.0
    */
   @SoftSided.Server
   public static <T> void set(@NotNull TagKey<Item> tag, @NotNull DataComponentType<T> component, @Nullable T value) {
      if (BuiltInRegistries.ITEM.getTag(tag).isEmpty()) {
         LOG.warn("Tried to modify {} of all items in #{}, but the tag is empty. Was modification attempted too early?", component, tag.location());
      } else {
         LOG.info("Modifying {} of all items in #{}", component, tag.location());
         BuiltInRegistries.ITEM.getTag(tag).get().stream().filter(Holder::isBound).map(Holder::value).forEach(item -> set(item, component, value));
      }
   }

   /**
    * Resets the specified component of the stack to the default value for that item.
    * Returns whether the component's value was changed.
    *
    * @param stack     the target item stack
    * @param component the component
    * @return true if and only if the component's value was changed
    * @since 2.0.0
    */
   @SoftSided.Server
   public static <T> boolean reset(@NotNull ItemStack stack, DataComponentType<T> component) {
      T defaultValue = stack.getItem().getDefaultInstance().get(component);
      if (Objects.equals(stack.get(component), defaultValue)) {
         stack.set(component, defaultValue);
         return true;
      }
      return false;
   }

   /**
    * Static utility to manipulate the {@link DataComponents#MAX_STACK_SIZE} of items and item stacks.
    *
    * @since 2.0.0
    */
   public static class MaxStackSize {

      /**
       * The lowest allowed max stack size (inclusive).
       *
       * @implNote This value is a hardcoded copy of the one encoded in {@link DataComponents#MAX_STACK_SIZE}.
       */
      public static final int MIN = 1;

      /**
       * The highest allowed max stack size (inclusive).
       *
       * @implNote This value is {@link Item#ABSOLUTE_MAX_STACK_SIZE}.
       */
      public static final int MAX = Item.ABSOLUTE_MAX_STACK_SIZE;

      /**
       * Gets the default max stack size for the stack's item.
       *
       * @param stack the target stack
       * @return the default max stack size for the stack's item
       * @since 2.0.0
       */
      public static int getDefault(@NotNull ItemStack stack) {
         return stack.getItem().getDefaultMaxStackSize();
      }

      /**
       * Checks whether the max stack size of the stack is the default for its item.
       *
       * @param stack the target stack
       * @return whether the stack's max stack size is the default for its item
       * @since 2.0.0
       */
      @ApiStatus.Experimental
      public static boolean isDefault(@NotNull ItemStack stack) {
         return stack.getMaxStackSize() == getDefault(stack);
      }

      /**
       * Resets the max stack size of the stack to the default for its item.
       *
       * @param stack the target stack
       * @since 2.0.0
       */
      public static void reset(@NotNull ItemStack stack) {
         stack.set(DataComponents.MAX_STACK_SIZE, getDefault(stack));
      }

      /**
       * Resets the max stack size of the stack to the default for its item,
       * if the current stack size does not exceed the default max stack size.
       *
       * @param stack the target stack
       * @since 2.0.0
       */
      @ApiStatus.Experimental
      public static void resetIfAtMostDefault(@NotNull ItemStack stack) {
         int defaultValue = getDefault(stack);
         if (stack.getCount() <= defaultValue) {
            stack.set(DataComponents.MAX_STACK_SIZE, defaultValue);
         }
      }

      /**
       * Sets the max stack size of the item to the given value,
       * if the value is within [{@link #MIN}, {@link #MAX}].
       *
       * @param item  the target item
       * @param value the max stack size value
       * @since 2.0.0
       */
      @SoftSided.Server
      public static void set(@NotNull Item item, int value) {
         if (value >= MIN && value <= MAX) {
            ComponentUtil.set(item, DataComponents.MAX_STACK_SIZE, value);
         }
      }

      /**
       * Sets the max stack size of the identified item to the given value,
       * if the item exists and the value is within [{@link #MIN}, {@link #MAX}].
       *
       * @param id    the target item's ID
       * @param value the max stack size value
       * @since 2.0.0
       */
      @SoftSided.Server
      public static void set(@NotNull ResourceLocation id, int value) {
         BuiltInRegistries.ITEM.getOptional(id).ifPresent(item -> set(item, value));
      }

      /**
       * Sets the max stack size of each item in the tag to the given value,
       * if the value is within [{@link #MIN}, {@link #MAX}].
       *
       * @param tag   the target tag
       * @param value the max stack size value
       * @since 2.0.0
       */
      @SoftSided.Server
      public static void set(@NotNull TagKey<Item> tag, int value) {
         if (value >= MIN && value <= MAX) {
            ComponentUtil.set(tag, DataComponents.MAX_STACK_SIZE, value);
         }
      }
   }
}
