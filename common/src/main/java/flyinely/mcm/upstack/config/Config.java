package flyinely.mcm.upstack.config;

import flyinely.mcm.upstack.registry.MItemTags;
import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.tags.ItemTags;
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

   public static class Cooldowns {

      private static final int MIN = 0;
      private static final int MAX = Integer.MAX_VALUE;

      public static final IntValue POTION_THROW_COOLDOWN;
      public static final IntValue EGG_THROW_COOLDOWN;
      public static final IntValue SNOWBALL_THROW_COOLDOWN;


      static {
         BUILDER.push("cooldowns");

         // TODO: Implement all cooldowns

         POTION_THROW_COOLDOWN = BUILDER
               .comment("Cooldown, in ticks, of throwable potions. Set 0 to disable.")
               .defineInRange("potion_throw_cooldown", 20, MIN, MAX); // default: parity w/ender pearls.
         EGG_THROW_COOLDOWN = BUILDER
               .comment("Cooldown, in ticks, of throwable eggs. Set 0 to disable.")
               .defineInRange("egg_throw_cooldown", 0, MIN, MAX); // default: unchanged.
         SNOWBALL_THROW_COOLDOWN = BUILDER
               .comment("Cooldown, in ticks, of snowballs. Set 0 to disable.")
               .defineInRange("snowball_throw_cooldown", 10, MIN, MAX); // default: half of ender pearls. not lower due to cheap projectile.

         BUILDER.pop();
      }

   }

   public static class StackSize {


      // A value of MIN_STACK_SIZE - 1 indicates that the item(s)' stack size should be left unmodified.
      private static final int MIN = ItemComponentUtil.ABSOLUTE_MIN_STACK_SIZE - 1;
      private static final int MAX = ItemComponentUtil.ABSOLUTE_MAX_STACK_SIZE;

      public static class C {
         public static final IntValue CHICKEN_EGGS;
         public static final IntValue BUCKETS;
         public static final IntValue HORSE_ARMOR;
         public static final IntValue MINECARTS;

         @Contract
         public static void init() {}

         static {
            BUILDER.push("tags");
            CHICKEN_EGGS = BUILDER.worldRestart()
                  .comment("#" + MItemTags.C.CHICKEN_EGGS)
                  .defineInRange("c.chicken_eggs", 64, MIN, MAX); // vanilla: 16. default: parity w/general items, for crafting QOL.
            BUCKETS = BUILDER.worldRestart()
                  .comment("#" + MItemTags.C.BUCKETS)
                  .defineInRange("c.buckets", 16, MIN, MAX); // vanilla: 1. default: parity w/honey bottles.
            HORSE_ARMOR = BUILDER.worldRestart()
                  .comment("#" + MItemTags.C.HORSE_ARMOR)
                  .defineInRange("c.horse_armor", 16, MIN, MAX); // vanilla: 1. default: not lower due to lack of durability and foreseen balance issues.
            MINECARTS = BUILDER.worldRestart()
                  .comment("#c:minecarts")
                  .defineInRange("c.minecarts", 16, MIN, MAX); // vanilla: 1. default: parity w/vanilla for entity-spawning items.
            BUILDER.pop();
         }
      }

      public static final IntValue ARMOR_STAND;
      public static final IntValue BANNERS;
      public static final IntValue BEDS;
      public static final IntValue BOATS;
      public static final IntValue BUCKET;
      public static final IntValue CAKE;
      public static final IntValue ENDER_PEARL;
      public static final IntValue HONEY_BOTTLE;
      public static final IntValue LINGERING_POTION;
      public static final IntValue POTION;
      public static final IntValue SADDLE;
      public static final IntValue SNOWBALL;
      public static final IntValue SPLASH_POTION;
      public static final IntValue TOTEM_OF_UNDYING;
      public static final IntValue WRITTEN_BOOK;

      static {
         BUILDER.push("stack_size");

         C.init();

         BUILDER.push("tags");
         BANNERS = BUILDER.worldRestart()
               .comment("#" + ItemTags.BANNERS)
               .defineInRange("banners", 64, MIN, MAX); // vanilla: 16. default: parity w/general items.
         BEDS = BUILDER.worldRestart()
               .comment("#" + ItemTags.BEDS)
               .defineInRange("beds", 16, MIN, MAX); // vanilla: 1. default: not higher due to cheap explosive power.
         BOATS = BUILDER.worldRestart()
               .comment("#" + ItemTags.BOATS)
               .defineInRange("boats", 16, MIN, MAX); // vanilla: 1. default: parity w/vanilla for entity-spawning items.
         BUILDER.pop();

         ARMOR_STAND = BUILDER.worldRestart()
               .comment("minecraft:armor_stand")
               .defineInRange("armor_stand", 64, MIN, MAX); // vanilla: 16. default: parity w/general items.
         BUCKET = BUILDER.worldRestart()
               .comment("minecraft:bucket (overrides #" + MItemTags.C.BUCKETS + ")")
               .defineInRange("bucket", 64, MIN, MAX); // vanilla: 16. default: parity w/empty bottles.
         CAKE = BUILDER.worldRestart()
               .comment("minecraft:cake")
               .defineInRange("cake", 16, MIN, MAX); // vanilla: 1. default: parity w/default for milk buckets, for crafting QOL.
         ENDER_PEARL = BUILDER.worldRestart()
               .comment("minecraft:ender_pearl")
               .defineInRange("ender_pearl", 0, MIN, MAX); // default: unchanged.
         HONEY_BOTTLE = BUILDER.worldRestart()
               .comment("minecraft:honey_bottle")
               .defineInRange("honey_bottle", 0, MIN, MAX); // default: unchanged.
         LINGERING_POTION = BUILDER.worldRestart()
               .comment("minecraft:lingering_potion")
               .defineInRange("lingering_potion", 16, MIN, MAX); // vanilla: 1. default: parity w/ender pearls.
         POTION = BUILDER.worldRestart()
               .comment("minecraft:potion")
               .defineInRange("potion", 16, MIN, MAX); // vanilla: 1. default: parity w/honey bottles.
         SADDLE = BUILDER.worldRestart()
               .comment("minecraft:saddle")
               .defineInRange("saddle", 16, MIN, MAX); // vanilla: 1. default: not lower due to lack of durability and foreseen balance issues.
         SNOWBALL = BUILDER.worldRestart()
               .comment("minecraft:snowball")
               .defineInRange("snowball", 64, MIN, MAX); // vanilla: 16. default: parity w/general items, for crafting QOL.
         SPLASH_POTION = BUILDER.worldRestart()
               .comment("minecraft:splash_potion")
               .defineInRange("splash_potion", 16, MIN, MAX); // vanilla: 1. default: parity w/ender pearls.
         TOTEM_OF_UNDYING = BUILDER.worldRestart()
               .comment("minecraft:totem_of_undying")
               .defineInRange("totem_of_undying", 0, MIN, MAX); // default: unchanged.
         WRITTEN_BOOK = BUILDER.worldRestart()
               .comment("minecraft:written_book")
               .defineInRange("written_book", 64, MIN, MAX); // vanilla: 16. default: parity w/general items.

         BUILDER.pop();
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
