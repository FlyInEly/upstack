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
		Cooldowns.init();

      SPEC = BUILDER.build();
   }

   public static class Cooldowns {

      private static final int MIN = 0;
      private static final int MAX = Integer.MAX_VALUE;

      public static final IntValue POTION_THROW_COOLDOWN;
      public static final IntValue EGG_THROW_COOLDOWN;
      public static final IntValue SNOWBALL_THROW_COOLDOWN;
		
		@Contract
		public static void init() {}

      static {
         BUILDER.push("cooldowns");

         POTION_THROW_COOLDOWN = BUILDER
               .comment("Cooldown, in ticks, of throwable potions. Set 0 to disable.")
               .defineInRange("potion_throw_cooldown", 10, MIN, MAX); // default: half of ender pearls. not lower due to combat balance implications, but not higher to cause less friction.
         EGG_THROW_COOLDOWN = BUILDER
               .comment("Cooldown, in ticks, of throwable eggs. Set 0 to disable.")
               .defineInRange("egg_throw_cooldown", 0, MIN, MAX); // default: unchanged.
         SNOWBALL_THROW_COOLDOWN = BUILDER
               .comment("Cooldown, in ticks, of snowballs. Set 0 to disable.")
               .defineInRange("snowball_throw_cooldown", 5, MIN, MAX); // default: quarter of ender pearls. not lower due to cheap projectile, esp. on blazes.

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
            BUILDER.push("c.tags");
            CHICKEN_EGGS = BUILDER.worldRestart()
                  .comment("#" + MItemTags.C.CHICKEN_EGGS)
                  .defineInRange("chicken_eggs", 64, MIN, MAX); // vanilla: 16. default: parity w/general items, for crafting QOL.
            BUCKETS = BUILDER.worldRestart()
                  .comment("#" + MItemTags.C.BUCKETS)
                  .defineInRange("buckets", 16, MIN, MAX); // vanilla: 1. default: parity w/honey bottles.
            HORSE_ARMOR = BUILDER.worldRestart()
                  .comment("#" + MItemTags.C.HORSE_ARMOR)
                  .defineInRange("horse_armor", 16, MIN, MAX); // vanilla: 1. default: not lower due to lack of durability and foreseen balance issues.
            MINECARTS = BUILDER.worldRestart()
                  .comment("#c:minecarts")
                  .defineInRange("minecarts", 16, MIN, MAX); // vanilla: 1. default: parity w/vanilla for entity-spawning items.
            BUILDER.pop(2); // c.tags
         }
      }

      public static class Pastel {
         // TAGS
         public static final IntValue BULBS;
         public static final IntValue FUSION_SHRINES;
         public static final IntValue ITEM_BOWLS;
         public static final IntValue NETWORK_NODES;
         public static final IntValue PEDESTALS;
         public static final IntValue ROUNDELS;
         public static final IntValue SHOOTING_STARS;
         public static final IntValue STRUCTURE_UPGRADES;
         // ITEMS
         public static final IntValue AETHER_VESTIGES;
         public static final IntValue BAG_OF_HOLDING;
         public static final IntValue CINDERHEARTH;
         public static final IntValue CLOTTED_CREAM;
         public static final IntValue COLOR_PICKER;
         public static final IntValue CRYSTAL_APOTHECARY;
         public static final IntValue CRYSTALLARIEUM;
         public static final IntValue DOWNSTONE_FRAGMENTS;
         public static final IntValue DRAGONBONE_BROTH;
         public static final IntValue ENCHANTER;
         public static final IntValue ENDER_SPLICE;
         public static final IntValue JADEITE_LOTUS_FLOWER;
         public static final IntValue MOONSTONE_CORE;
         public static final IntValue PALTERIA_GEM;
         public static final IntValue POTION_WORKSHOP;
         public static final IntValue RESONANCE_SHARD;
         public static final IntValue SPIRIT_INSTILLER;
         public static final IntValue STAR_FRAGMENT;
         public static final IntValue STARDUST_BLOCK;
         public static final IntValue STRATINE_GEM;
         public static final IntValue TRIPLE_MEAT_POT_PIE;
         public static final IntValue TRIPLE_MEAT_POT_STEW;

         @Contract
         public static void init() {}

         static {
            BUILDER.push("pastel");

            BUILDER.push("tags");
            BULBS = BUILDER.worldRestart()
                  .comment("#" + MItemTags.Pastel.BULBS)
                  .defineInRange("bulbs", 64, MIN, MAX); // mod: 16
            FUSION_SHRINES = BUILDER.worldRestart()
                  .comment("#" + MItemTags.Pastel.FUSION_SHRINES)
                  .defineInRange("fusion_shrines", 64, MIN, MAX); // mod: 1
            ITEM_BOWLS = BUILDER.worldRestart()
                  .comment("#" + MItemTags.Pastel.ITEM_BOWLS)
                  .defineInRange("item_bowls", 64, MIN, MAX); // mod: 16 (exc. enlightenment bowl: 64)
            NETWORK_NODES = BUILDER.worldRestart()
                  .comment("#" + MItemTags.Pastel.NETWORK_NODES)
                  .defineInRange("network_nodes", 64, MIN, MAX); // mod: 16
            PEDESTALS = BUILDER.worldRestart()
                  .comment("#" + MItemTags.Pastel.PEDESTALS)
                  .defineInRange("pedestals", 64, MIN, MAX); // mod: 1
            ROUNDELS = BUILDER.worldRestart()
                  .comment("#" + MItemTags.Pastel.ROUNDELS)
                  .defineInRange("roundels", 64, MIN, MAX); // mod: 16 (exc. preservation roundel: 64)
            SHOOTING_STARS = BUILDER.worldRestart()
                  .comment("#" + MItemTags.Pastel.SHOOTING_STARS)
                  .defineInRange("shooting_star", 16, MIN, MAX); // mod: 1. entity-spawning
            STRUCTURE_UPGRADES = BUILDER.worldRestart()
                  .comment("#" + MItemTags.Pastel.STRUCTURE_UPGRADES)
                  .defineInRange("structure_upgrades", 64, MIN, MAX); // mod: 16
            BUILDER.pop(); // tags

            AETHER_VESTIGES = BUILDER.worldRestart()
                  .comment("pastel:aether_vestiges")
                  .defineInRange("aether_vestiges", 64, MIN, MAX); // mod: 1
            BAG_OF_HOLDING = BUILDER.worldRestart()
                  .comment("pastel:bag_of_holding")
                  .defineInRange("bag_of_holding", 64, MIN, MAX); // mod: 1. upstack: parity with compass
            CINDERHEARTH = BUILDER.worldRestart()
                  .comment("pastel:cinderhearth")
                  .defineInRange("cinderhearth", 64, MIN, MAX); // mod: 1
            CLOTTED_CREAM = BUILDER.worldRestart()
                  .comment("pastel:clotted_cream (overrides #" + MItemTags.C.BUCKETS + ")")
                  .defineInRange("clotted_cream", 64, MIN, MAX); // mod: 64
            COLOR_PICKER = BUILDER.worldRestart()
                  .comment("pastel:color_picker")
                  .defineInRange("color_picker", 64, MIN, MAX); // mod: 1
            CRYSTAL_APOTHECARY = BUILDER.worldRestart()
                  .comment("pastel:crystal_apothecary")
                  .defineInRange("crystal_apothecary", 64, MIN, MAX); // mod: 1
            CRYSTALLARIEUM = BUILDER.worldRestart()
                  .comment("pastel:crystallarieum")
                  .defineInRange("crystallarieum", 64, MIN, MAX); // mod: 1
            DOWNSTONE_FRAGMENTS = BUILDER.worldRestart()
                  .comment("pastel:downstone_fragments")
                  .defineInRange("downstone_fragments", 64, MIN, MAX); // mod: 16
            DRAGONBONE_BROTH = BUILDER.worldRestart()
                  .comment("pastel:dragonbone_broth")
                  .defineInRange("dragonbone_broth", 16, MIN, MAX); // mod: 8. note: grants magic annulation. upstack: parity with farmers' delight bowl foods
            ENCHANTER = BUILDER.worldRestart()
                  .comment("pastel:enchanter")
                  .defineInRange("enchanter", 64, MIN, MAX); // mod: 1
            ENDER_SPLICE = BUILDER.worldRestart()
                  .comment("pastel:ender_splice")
                  .defineInRange("ender_splice", 0, MIN, MAX); // mod: 16
            JADEITE_LOTUS_FLOWER = BUILDER.worldRestart()
                  .comment("pastel:jadeite_lotus_flower")
                  .defineInRange("jadeite_lotus_flower", 64, MIN, MAX); // mod: 16
            MOONSTONE_CORE = BUILDER.worldRestart()
                  .comment("pastel:moonstone_core")
                  .defineInRange("moonstone_core", 64, MIN, MAX); // mod: 16
            PALTERIA_GEM = BUILDER.worldRestart()
                  .comment("pastel:palteria_gem")
                  .defineInRange("palteria_gem", 0, MIN, MAX); // mod: 16
            POTION_WORKSHOP = BUILDER.worldRestart()
                  .comment("pastel:potion_workshop")
                  .defineInRange("potion_workshop", 64, MIN, MAX); // mod: 1
            RESONANCE_SHARD = BUILDER.worldRestart()
                  .comment("pastel:resonance_shard")
                  .defineInRange("resonance_shard", 64, MIN, MAX); // mod: 16
            SPIRIT_INSTILLER = BUILDER.worldRestart()
                  .comment("pastel:spirit_instiller")
                  .defineInRange("spirit_instiller", 64, MIN, MAX); // mod: 1
            STAR_FRAGMENT = BUILDER.worldRestart()
                  .comment("pastel:star_fragment")
                  .defineInRange("star_fragment", 64, MIN, MAX); // mod: 16
            STARDUST_BLOCK = BUILDER.worldRestart()
                  .comment("pastel:stardust_block")
                  .defineInRange("stardust_block", 64, MIN, MAX); // mod: 1. likely a bug, since this is in #c:storage_blocks
            STRATINE_GEM = BUILDER.worldRestart()
                  .comment("pastel:stratine_gem")
                  .defineInRange("stratine_gem", 0, MIN, MAX); // mod: 16
            TRIPLE_MEAT_POT_PIE = BUILDER.worldRestart()
                  .comment("pastel:triple_meat_pot_pie")
                  .defineInRange("triple_meat_pot_pie", 16, MIN, MAX); // mod: 8. upstack: parity with farmers' delight bowl foods
            TRIPLE_MEAT_POT_STEW = BUILDER.worldRestart()
                  .comment("pastel:triple_meat_pot_stew")
                  .defineInRange("triple_meat_pot_stew", 16, MIN, MAX); // mod: 8. upstack: parity with farmers' delight bowl foods
            BUILDER.pop(); // pastel
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
         Pastel.init();

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
         BUILDER.pop(); // tags

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

         BUILDER.pop(); // stack sizes
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
