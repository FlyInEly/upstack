package flyinely.mcm.upstack.platform;

import flyinely.mcm.upstack.model.registry.ItemPredicateRegistry;
import flyinely.mcm.upstack.model.registry.ItemPredicateRegistryImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ItemPredicateRegistries {

   public static ItemPredicateRegistry<Integer> STACK_SIZE = new ItemPredicateRegistryImpl<>() {
      @Contract(pure = true)
      @Override
      public @NotNull Integer defaultValue() {
         return 0;
      }
   };

}
