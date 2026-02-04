package flyinely.mcm.upstack.config;

import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import org.jetbrains.annotations.Contract;

public class Config {

   public static final ModConfigSpec SPEC;
   public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();


   static {
      StackSize.init();

      SPEC = BUILDER.build();
   }

   public static class StackSize {

      // A value of MIN_STACK_SIZE - 1 indicates that the item(s)' stack size should be left unmodified.
      private static final int MIN = ItemComponentUtil.ABSOLUTE_MIN_STACK_SIZE - 1;
      private static final int MAX = ItemComponentUtil.ABSOLUTE_MAX_STACK_SIZE;

      public static final IntValue FILLED_BUCKETS;
      public static final IntValue EMPTY_BUCKET;

      static {
         FILLED_BUCKETS = BUILDER
               .comment("Non-empty buckets in #c:buckets")
               .defineInRange("filled_buckets", 16, MIN, MAX); // vanilla: 1. default: parity w/honey bottles.
         EMPTY_BUCKET = BUILDER
               .comment("minecraft:bucket")
               .defineInRange("bucket", 64, MIN, MAX); // vanilla: 16. default: parity w/empty bottles.
      }

      /**
       * Dummy method to trigger the static initializer.
       */
      @Contract()
      static void init() {}

   }

   // LATER: Fully customizable stack sizes by item/tag id. Or data-driven.
   //   public static final ListValueSpec STACK_SIZES;
   //
   //   public static final List<String> DEFAULT_STACK_SIZES = List.of(
   //         "minecraft:bucket,64"
   //   );

}
