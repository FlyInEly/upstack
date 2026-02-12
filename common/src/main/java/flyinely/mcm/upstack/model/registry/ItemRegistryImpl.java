package flyinely.mcm.upstack.model.registry;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Implements {@link ItemRegistry}.
 * <p>
 * If multiple entries match the {@link ItemStack} passed to {@link #apply(ItemStack, Consumer)},
 * {@link Consumer} is called only once. The earliest-registered entry supplies the consumed value.
 *
 * @param <T> of registered values
 * @implNote Stores registration order using a linked list. Consequently, duplicate entries can be registered,
 * but since only the earliest-registered entry supplies the value, they have no effect.
 */
public abstract class ItemRegistryImpl<T> implements ItemRegistry<T> {

	private final List<Entry<T>> ITEM_VALUES = new LinkedList<>();
	
	/**
	 * If {@link ItemStack} matches an entry, then {@link Consumer} is called with the value it supplies.
	 * If multiple entries match, {@link Consumer} is called only once; the earliest-registered entry
	 * supplies the value.
	 *
	 * @param stack    the item for lookup
	 * @param consumer the consumer
	 */
	@Override
	public void apply(ItemStack stack, Consumer<T> consumer) {
		for (var itemValue : ITEM_VALUES) {
			if (itemValue.apply(stack, consumer)) {
				return;
			}
		}
	}

   @Override
   public Stream<Entry<T>> stream() {
      return ITEM_VALUES.stream();
   }

   @Override
	public void register(Predicate<? super Item> predicate, Supplier<T> supplier) {
		ITEM_VALUES.add(new Entry<>(predicate, supplier));
	}
	
}
