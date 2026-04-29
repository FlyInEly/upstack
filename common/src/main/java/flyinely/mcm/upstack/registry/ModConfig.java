package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.annotation.CContract.StaticInit;
import flyinely.mcm.upstack.annotation.CContract.StaticRegistry;
import flyinely.mcm.upstack.util.ComponentUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static flyinely.mcm.upstack.util.ResUtil.id;
import static flyinely.mcm.upstack.util.TagUtil.tagString;

@ApiStatus.Internal
@StaticRegistry
public class ModConfig {

   public static final ModConfigSpec SPEC;
   public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

   static {
      StackSize.init();
      Cooldowns.init();

      SPEC = BUILDER.build();
   }

   @StaticRegistry
   @SuppressWarnings("EmptyMethod") // They are dummy methods to trigger static init
   public static class Cooldowns {

      private static final int MIN = 0;
      private static final int MAX = Integer.MAX_VALUE;

      public static final IntValue POTION_THROW_COOLDOWN;
      public static final IntValue EGG_THROW_COOLDOWN;
      public static final IntValue SNOWBALL_THROW_COOLDOWN;

      @Contract
      @StaticInit
      public static void init() {}

      static {
         BUILDER.push("cooldowns");

         POTION_THROW_COOLDOWN = BUILDER
               .comment("Cooldown, in ticks, of throwing splash and lingering potions. Set 0 to disable.")
               .defineInRange("throwable_potion", 10, MIN, MAX); // default: half of ender pearls. not lower due to combat balance implications, but not higher to cause less friction.
         EGG_THROW_COOLDOWN = BUILDER
               .comment("Cooldown, in ticks, of throwing eggs. Set 0 to disable.")
               .defineInRange("egg", 0, MIN, MAX); // default: unchanged.
         SNOWBALL_THROW_COOLDOWN = BUILDER
               .comment("Cooldown, in ticks, of throwing snowballs. Set 0 to disable.")
               .defineInRange("snowball", 5, MIN, MAX); // default: quarter of ender pearls. not lower due to cheap projectile, esp. on blazes.

         BUILDER.pop(); // cooldowns
      }

   }

   @SuppressWarnings("EmptyMethod") // They are dummy methods to trigger static init
   public static class StackSize {


      // A value of MIN_STACK_SIZE - 1 indicates that the item(s)' stack size should be left unmodified.
      private static final int MIN = ComponentUtil.MaxStackSize.MIN - 1;
      private static final int MAX = ComponentUtil.MaxStackSize.MAX;

      public static IntValue tag(TagKey<Item> tag, int defaultValue) {
         return BUILDER.worldRestart().comment(tagString(tag)).defineInRange(tag.location().getPath(), defaultValue, MIN, MAX);
      }

      public static IntValue item(@NotNull ResourceLocation id, int defaultValue) {
         return BUILDER.worldRestart().comment(id.toString()).defineInRange(id.getPath(), defaultValue, MIN, MAX);
      }

      @StaticRegistry
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
         @StaticInit
         public static void init() {}

         static {
            BUILDER.push("pastel");

            BUILDER.push("tags");
            BULBS = tag(ModItemTags.Pastel.BULBS, 64); // mod: 16
            FUSION_SHRINES = tag(ModItemTags.Pastel.FUSION_SHRINES, 64); // mod: 1
            ITEM_BOWLS = tag(ModItemTags.Pastel.ITEM_BOWLS, 64); // mod: 16 (exc. enlightenment bowl: 64)
            NETWORK_NODES = tag(ModItemTags.Pastel.NETWORK_NODES, 64); // mod: 16
            PEDESTALS = tag(ModItemTags.Pastel.PEDESTALS, 64); // mod: 1
            ROUNDELS = tag(ModItemTags.Pastel.ROUNDELS, 64); // mod: 16 (exc. preservation roundel: 64)
            SHOOTING_STARS = tag(ModItemTags.Pastel.SHOOTING_STARS, 16); // mod: 1. entity-spawning
            STRUCTURE_UPGRADES = tag(ModItemTags.Pastel.STRUCTURE_UPGRADES, 64); // mod: 16
            BUILDER.pop(); // tags

            AETHER_VESTIGES = item(id("pastel", "aether_vestiges"), 64); // mod: 1
            BAG_OF_HOLDING = item(id("pastel", "bag_of_holding"), 64);  // mod: 1. upstack: parity with compass
            CINDERHEARTH = item(id("pastel", "cinderhearth"), 64); // mod: 1
            CLOTTED_CREAM = item(id("pastel", "clotted_cream"), 64); // mod: 64. overrides #c:buckets
            COLOR_PICKER = item(id("pastel", "color_picker"), 64); // mod: 1
            CRYSTAL_APOTHECARY = item(id("pastel", "crystal_apothecary"), 64); // mod: 1
            CRYSTALLARIEUM = item(id("pastel", "crystallarieum"), 64); // mod: 1
            DOWNSTONE_FRAGMENTS = item(id("pastel", "downstone_fragments"), 64); // mod: 16
            DRAGONBONE_BROTH = item(id("pastel", "dragonbone_broth"), 16); // mod: 8. note: grants magic annulation. upstack: parity with farmers' delight bowl foods. Overrides #c:soups
            ENCHANTER = item(id("pastel", "enchanter"), 64); // mod: 1
            ENDER_SPLICE = item(id("pastel", "ender_splice"), 0); // mod: 16
            JADEITE_LOTUS_FLOWER = item(id("pastel", "jadeite_lotus_flower"), 64); // mod: 16
            MOONSTONE_CORE = item(id("pastel", "moonstone_core"), 64); // mod: 16
            PALTERIA_GEM = item(id("pastel", "palteria_gem"), 0); // mod: 16
            POTION_WORKSHOP = item(id("pastel", "potion_workshop"), 64); // mod: 1
            RESONANCE_SHARD = item(id("pastel", "resonance_shard"), 64); // mod: 16
            SPIRIT_INSTILLER = item(id("pastel", "spirit_instiller"), 64); // mod: 1
            STAR_FRAGMENT = item(id("pastel", "star_fragment"), 64); // mod: 16
            STARDUST_BLOCK = item(id("pastel", "stardust_block"), 64); // mod: 1. likely a bug, since this is in #c:storage_blocks
            STRATINE_GEM = item(id("pastel", "stratine_gem"), 0); // mod: 16
            TRIPLE_MEAT_POT_PIE = item(id("pastel", "triple_meat_pot_pie"), 16); // mod: 8. upstack: parity with farmers' delight bowl foods
            TRIPLE_MEAT_POT_STEW = item(id("pastel", "triple_meat_pot_stew"), 16); // mod: 8. upstack: parity with farmers' delight bowl foods. Overrides #c:soups
            BUILDER.pop(); // pastel
         }
      }

      @StaticRegistry
      public static class Farmersdelight {
         // TAGS
         public static final IntValue FEASTS;

         // ITEMS
         public static final IntValue COOKING_POT;

         @Contract
         @StaticInit
         public static void init() {}

         static {
            BUILDER.push("farmersdelight");

            BUILDER.push("tags");
            FEASTS = tag(ModItemTags.Farmersdelight.FEASTS, 16); // mod: 1. upstack: parity with bowl foods. feasts contain many serves, but aren't directly edible anyway
            BUILDER.pop(); // tags

            COOKING_POT = item(id("farmersdelight", "cooking_pot"), 16); // mod: 1. upstack: moderate QOL on par with filled bucket, especially for crafting/moving empty pots

            BUILDER.pop(); // farmersdelight
         }
      }

      // Include minecraft: and c: here
      @StaticRegistry
      public static class Common {

         // TAGS
         public static final IntValue BANNERS;
         public static final IntValue BEDS;
         public static final IntValue BOATS;
         public static final IntValue BUCKETS;
         public static final IntValue CHICKEN_EGGS;
         public static final IntValue HORSE_ARMOR;
         public static final IntValue MINECARTS;

         // ITEMS
         public static final IntValue ARMOR_STAND;
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
         public static final IntValue MUSIC_DISCS;
         public static final IntValue SOUPS;
         public static final IntValue WRITABLE_BOOK;
         public static final IntValue BANNER_PATTERNS;
         public static final IntValue ENCHANTED_BOOK;
         public static final IntValue MILK_BOTTLES;

         static {
            BUILDER.push("_common"); // Underscore to hoist folder

            BUILDER.push("tags");
            BANNERS = tag(ItemTags.BANNERS, 64); // vanilla: 16. default: parity w/general items.
            BEDS = tag(ItemTags.BEDS, 16); // vanilla: 1. default: not higher due to cheap explosive power.
            BOATS = tag(ItemTags.BOATS, 16); // vanilla: 1. default: parity w/vanilla for entity-spawning items.
            CHICKEN_EGGS = tag(ModItemTags.C.CHICKEN_EGGS, 64); // vanilla: 16. default: parity w/general items, for crafting QOL.
            BANNER_PATTERNS = tag(ModItemTags.C.BANNER_PATTERNS, 64); // vanilla: 1. default: parity w/general items, for carrying QOL. they are dupable and aren't
            BUCKETS = tag(ModItemTags.C.BUCKETS, 16); // vanilla: 1. default: parity w/honey bottles.
            HORSE_ARMOR = tag(ModItemTags.C.HORSE_ARMOR, 16); // vanilla: 1. default: not lower due to lack of durability and foreseen balance issues.
            MILK_BOTTLES = tag(ModItemTags.C.MILK_BOTTLES, 0); // usually 16. included for configurability.
            MINECARTS = tag(ModItemTags.C.MINECARTS, 16); // vanilla: 1. default: parity w/vanilla for entity-spawning items.
            MUSIC_DISCS = tag(ModItemTags.C.MUSIC_DISCS, 64); // vanilla: 1.
            SOUPS = tag(ModItemTags.C.SOUPS, 16); // vanilla: 1. default: parity w/farmersdelight; makes soups actually viable food
            BUILDER.pop(); // tags

            ARMOR_STAND = item(id("minecraft", "armor_stand"), 64); // vanilla: 16. default: parity w/general items.
            BUCKET = item(id("minecraft", "bucket"), 64); // vanilla: 16. default: parity w/empty bottles. Overrides #c:buckets
            CAKE = item(id("minecraft", "cake"), 16); // vanilla: 1. default: parity w/default for milk buckets, for crafting QOL.
            ENDER_PEARL = item(id("minecraft", "ender_pearl"), 0); // default: unchanged.
            ENCHANTED_BOOK = item(id("minecraft", "enchanted_book"), 64); // vanilla: 1. default: parity w/general items
            HONEY_BOTTLE = item(id("minecraft", "honey_bottle"), 0); // default: unchanged.
            LINGERING_POTION = item(id("minecraft", "lingering_potion"), 16); // vanilla: 1. default: parity w/ender pearls.
            POTION = item(id("minecraft", "potion"), 16); // vanilla: 1. default: parity w/honey bottles.
            SADDLE = item(id("minecraft", "saddle"), 16); // vanilla: 1. default: not lower due to lack of durability and foreseen balance issues.
            SNOWBALL = item(id("minecraft", "snowball"), 64); // vanilla: 16. default: parity w/general items, for crafting QOL.
            SPLASH_POTION = item(id("minecraft", "splash_potion"), 16); // vanilla: 1. default: parity w/ender pearls.
            TOTEM_OF_UNDYING = item(id("minecraft", "totem_of_undying"), 0); // default: unchanged.
            WRITABLE_BOOK = item(id("minecraft", "writable_book"), 64); // vanilla: 1. default: fixes undefined behavior when writing to a stack of more than one
            WRITTEN_BOOK = item(id("minecraft", "written_book"), 64); // vanilla: 16. default: parity w/general items.

            BUILDER.pop(); // common
         }

         @Contract
         public static void init() {}
      }

      static {
         BUILDER.push("stack_size");

         Common.init();
         Pastel.init();
         Farmersdelight.init();

         BUILDER.pop(); // stack_size
      }

      @Contract
      @StaticInit
      static void init() {}

   }

}
