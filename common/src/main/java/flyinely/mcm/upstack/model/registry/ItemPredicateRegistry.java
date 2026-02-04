package flyinely.mcm.upstack.model.registry;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * An {@link ItemValue} registry. Entries consist of a {@link ItemPredicate} "key" and {@link T} {@link Supplier} "value".
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
public interface ItemPredicateRegistry<T> {
	
	/**
	 * The (constant) default value.
	 *
	 * @return the default value
	 * @implSpec Return a constant value. Document the rationale behind the particular
	 * default, and the expected behavior of consuming it in {@link #apply(ItemStack, Consumer)}.
	 */
	T defaultValue();
	
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
    * Returns a stream of all entries.
    * @return a stream of all entries
    */
   Stream<ItemValue<T>> stream();
	
	/**
	 * Registers an entry.
	 *
	 * @param item     the item predicate to match
	 * @param supplier the value supplier
	 */
	void register(ItemPredicate item, Supplier<T> supplier);
	
	/**
	 * Registers an entry which supplies a constant value.
	 *
	 * @param item  the item predicate to match
	 * @param value the constant value supplied
	 */
	default void register(ItemPredicate item, T value) {
		register(item, () -> value);
	}
	
	/**
	 * An {@link ItemPredicateRegistry} entry. Consists of an {@link ItemPredicate} and {@link T} {@link Supplier}.
	 * @param item the item predicate
	 * @param valueSupplier the value supplier
	 * @param <T> the value type
	 */
	record ItemValue<T>(ItemPredicate item, Supplier<T> valueSupplier) {
		
		/**
		 * If {@link ItemStack} matches {@code item}, then {@link Consumer} is called with {@code valueSupplier.get()}
		 * and {@code true} is returned.
		 *
		 * @param stack         the item for lookup
		 * @param valueConsumer the value consumer
		 * @return {@code true} if {@link ItemStack} matches {@code item}, {@code false} otherwise
		 */
		boolean apply(ItemStack stack, Consumer<T> valueConsumer) {
			if (item.test(stack)) {
				valueConsumer.accept(valueSupplier.get());
				return true;
			}
			return false;
		}
	}
	
}
