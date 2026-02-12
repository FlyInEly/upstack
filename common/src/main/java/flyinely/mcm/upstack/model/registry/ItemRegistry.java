package flyinely.mcm.upstack.model.registry;

import flyinely.mcm.upstack.model.annotation.CContract;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * An {@link Entry} registry. Entries consist of a {@link Item} {@link Predicate} "key" and {@link T} {@link Supplier} "value".
 * <p>
 * If {@link #apply(ItemStack, Consumer)} finds an entry whose {@link ItemPredicate} matches its {@link ItemStack}
 * argument, it calls {@link Consumer} with the value supplied by that entry. If multiple entries match, the
 * implementation determines which match(es) {@link Consumer} is called for.
 *
 * @param <T> the value type
 * @implSpec Document how the registry handles the case that multiple entries match the {@link ItemStack}
 * passed to {@link #apply(ItemStack, Consumer)}. For instance, {@link Consumer} might be called for each match,
 * any match, or only the first match (based on a specified search order).
 */
public interface ItemRegistry<T> {

   /**
    * The sentinel value.
    *
    * @return the sentinel value
    * @implSpec Return a constant value. Document the rationale behind the particular
    * default, and the expected behavior of consuming it in {@link #apply(ItemStack, Consumer)}.
    */
   @CContract.ConstantReturn
   T sentinelValue();

   /**
    * Registers an entry.
    *
    * @param item  the item to match
    * @param value the constant value to supply
    */
   default void register(@NotNull Item item, T value) {
      register(item, () -> value);
   }

   /**
    * Registers an entry.
    *
    * @param item     the item to match
    * @param supplier the value supplier
    */
   default void register(@NotNull Item item, Supplier<T> supplier) {
      register(i -> i == item, supplier);
   }

   /**
    * Registers an entry.
    *
    * @param predicate the item predicate to match
    * @param value     the constant value to supply
    */
   default void register(Predicate<? super Item> predicate, T value) {
      register(predicate, () -> value);
   }

   /**
    * Registers an entry.
    *
    * @param predicate the item predicate to match
    * @param supplier  the value supplier
    */
   void register(Predicate<? super Item> predicate, Supplier<T> supplier);

   /**
    * If {@link ItemStack} matches an entry, then {@link Consumer} is called with the value it supplies.
    *
    * @param stack    the item for lookup
    * @param consumer the consumer
    * @implSpec Document how the method handles the case that multiple entries match the {@link ItemStack}.
    * For instance, {@link Consumer} might be called for each match, any match, or only the first match
    * (based on a specified search order).
    */
   void apply(ItemStack stack, Consumer<T> consumer);

   /**
    * Stream all entries.
    *
    * @return a stream of all entries
    */
   Stream<Entry<T>> stream();

   /**
    * Stream all entries which <i>presently</i> return non-sentinel values.
    * @return a stream of entries
    */
   default Stream<Entry<T>> streamData() {
      return stream().filter(i -> !Objects.equals(i, sentinelValue()));
   }

   /**
    * An {@link ItemRegistry} entry. Consists of an item predicate and value supplier.
    *
    * @param predicate the item predicate
    * @param supplier  the value supplier
    * @param <T>       the value type supplied
    */
   record Entry<T>(Predicate<? super Item> predicate, Supplier<@Nullable T> supplier) {

      /**
       * Gets the value supplied.
       *
       * @return the value supplied
       */
      public T value() {
         return supplier.get();
      }

      /**
       * If {@link ItemStack} matches {@code item}, then {@link Consumer} is called with {@code valueSupplier.get()}
       * and {@code true} is returned.
       *
       * @param stack         the item for lookup
       * @param consumer the value consumer
       * @return {@code true} if {@link ItemStack} matches {@code item}, {@code false} otherwise
       */
      public boolean apply(@NotNull ItemStack stack, Consumer<T> consumer) {
         if (predicate.test(stack.getItem())) {
            consumer.accept(supplier.get());
            return true;
         }
         return false;
      }
   }

}
