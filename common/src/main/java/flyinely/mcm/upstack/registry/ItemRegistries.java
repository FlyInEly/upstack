package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.model.registry.ItemRegistry;
import flyinely.mcm.upstack.model.registry.ItemRegistryImpl;
import flyinely.mcm.upstack.util.ItemComponentUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ItemRegistries {

   public static final ItemRegistry<Integer> STACK_SIZE = new ItemRegistryImpl<Integer>() {
      @Contract(pure = true)
      @Override
      public @NotNull Integer sentinelValue() {
         return ItemComponentUtil.ABSOLUTE_MIN_STACK_SIZE - 1;
      }
   };
}
