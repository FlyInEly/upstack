package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.model.registry.ItemPredicateRegistry;
import flyinely.mcm.upstack.model.registry.ItemPredicateRegistryImpl;
import flyinely.mcm.upstack.util.ItemComponentUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ItemPredicateRegistries {

   public static final ItemPredicateRegistry<Integer> STACK_SIZE_REGISTRY = new ItemPredicateRegistryImpl<Integer>() {
      @Contract(pure = true)
      @Override
      public @NotNull Integer defaultValue() {
         return ItemComponentUtil.ABSOLUTE_MIN_STACK_SIZE - 1;
      }
   };
}
