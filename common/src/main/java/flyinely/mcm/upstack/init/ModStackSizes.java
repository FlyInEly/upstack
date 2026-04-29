package flyinely.mcm.upstack.init;

import flyinely.mcm.upstack.annotation.SoftSided;
import flyinely.mcm.upstack.registry.ModItemTags;
import flyinely.mcm.upstack.util.ComponentUtil;
import flyinely.mcm.upstack.util.TagUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static flyinely.mcm.upstack.registry.ModConfig.StackSize;
import static flyinely.mcm.upstack.util.ComponentUtil.MaxStackSize.set;
import static flyinely.mcm.upstack.util.ResUtil.id;

/**
 * Set max stack sizes according to the mod's config.
 */
@ApiStatus.Internal
@SoftSided.Server
public final class ModStackSizes {

   // tagPrefix MUST also be a valid prefix for a tag. no backslashes or other special characters
   // which would mess up the regex replaceFirst(). Should move to a public class for use by other mods' datagen
   private static final String TAG_PREFIX = "upstack:max_stack_size/";

   /**
    * Set max stack sizes by tag, then by item, according to the mod's config.
    * <p>
    * The order ensures that an item's stack size takes priority over that of its tag(s). It does not guarantee any particular
    * priority for an item with two tags of different stack sizes.
    */
   public static void apply() {
      tags();
      items();

      // For each tag with the prefix #TAG_PREFIX, the
      BuiltInRegistries.ITEM.getTagNames()
            .map(ModStackSizes::parseStackSize)
            .flatMap(Optional::stream)
            .forEach(size -> set(tag(size), size));
   }

   private static @NotNull TagKey<Item> tag(Integer size) {
      return TagUtil.item(TAG_PREFIX + size);
   }

   /**
    * Return the stack size represented by the item tag, if applicable.
    * <p>
    * An item tag represents a stack size if its location is {@link #TAG_PREFIX}
    * followed by the stack size it represents (an unsigned integer). This method
    * does not verify that the represented stack size is in-range, to avoid redundancy
    * when called with {@link ComponentUtil.MaxStackSize#set(TagKey, int)}.
    * <p>
    * In the future, upgrade this system to use custom JSON. Using tags means that a given item can be
    * tagged with multiple different stack sizes, with no control over precedence.
    *
    * @param tag the item tag
    * @return an optional containing the stack size represented by the item tag, if applicable, or {@link Optional#empty()}.
    */
   private static Optional<Integer> parseStackSize(@NotNull TagKey<Item> tag) {
      String id = tag.location().toString();
      if (id.startsWith(TAG_PREFIX)) {
         try {
            return Optional.of(Integer.parseUnsignedInt(id.replaceFirst(TAG_PREFIX, "")));
         } catch (NumberFormatException ignored) {
         }
      }
      return Optional.empty();
   }

   private static void tags() {
      // minecraft
      set(ItemTags.BANNERS, StackSize.Common.BANNERS.get());
      set(ItemTags.BEDS, StackSize.Common.BEDS.get());
      set(ItemTags.BOATS, StackSize.Common.BOATS.get());

      // c
      set(ModItemTags.C.BANNER_PATTERNS, StackSize.Common.BANNER_PATTERNS.get());
      set(ModItemTags.C.BUCKETS, StackSize.Common.BUCKETS.get());
      set(ModItemTags.C.CHICKEN_EGGS, StackSize.Common.CHICKEN_EGGS.get());
      set(ModItemTags.C.HORSE_ARMOR, StackSize.Common.HORSE_ARMOR.get());
      set(ModItemTags.C.MILK_BOTTLES, StackSize.Common.MILK_BOTTLES.get());
      set(ModItemTags.C.MINECARTS, StackSize.Common.MINECARTS.get());
      set(ModItemTags.C.MUSIC_DISCS, StackSize.Common.MUSIC_DISCS.get());
      set(ModItemTags.C.SOUPS, StackSize.Common.SOUPS.get());

      // pastel
      set(ModItemTags.Pastel.BULBS, StackSize.Pastel.BULBS.get());
      set(ModItemTags.Pastel.FUSION_SHRINES, StackSize.Pastel.FUSION_SHRINES.get());
      set(ModItemTags.Pastel.ITEM_BOWLS, StackSize.Pastel.ITEM_BOWLS.get());
      set(ModItemTags.Pastel.NETWORK_NODES, StackSize.Pastel.NETWORK_NODES.get());
      set(ModItemTags.Pastel.PEDESTALS, StackSize.Pastel.PEDESTALS.get());
      set(ModItemTags.Pastel.ROUNDELS, StackSize.Pastel.ROUNDELS.get());
      set(ModItemTags.Pastel.SHOOTING_STARS, StackSize.Pastel.SHOOTING_STARS.get());
      set(ModItemTags.Pastel.STRUCTURE_UPGRADES, StackSize.Pastel.STRUCTURE_UPGRADES.get());

      // farmersdelight
      set(ModItemTags.Farmersdelight.FEASTS, StackSize.Farmersdelight.FEASTS.get());
   }

   private static void items() {
      // minecraft
      ComponentUtil.MaxStackSize.set(Items.ARMOR_STAND, StackSize.Common.ARMOR_STAND.get());
      ComponentUtil.MaxStackSize.set(Items.BUCKET, StackSize.Common.BUCKET.get());
      ComponentUtil.MaxStackSize.set(Items.CAKE, StackSize.Common.CAKE.get());
      ComponentUtil.MaxStackSize.set(Items.ENCHANTED_BOOK, StackSize.Common.ENCHANTED_BOOK.get());
      ComponentUtil.MaxStackSize.set(Items.ENDER_PEARL, StackSize.Common.ENDER_PEARL.get());
      ComponentUtil.MaxStackSize.set(Items.HONEY_BOTTLE, StackSize.Common.HONEY_BOTTLE.get());
      ComponentUtil.MaxStackSize.set(Items.LINGERING_POTION, StackSize.Common.LINGERING_POTION.get());
      ComponentUtil.MaxStackSize.set(Items.POTION, StackSize.Common.POTION.get());
      ComponentUtil.MaxStackSize.set(Items.SADDLE, StackSize.Common.SADDLE.get());
      ComponentUtil.MaxStackSize.set(Items.SNOWBALL, StackSize.Common.SNOWBALL.get());
      ComponentUtil.MaxStackSize.set(Items.SPLASH_POTION, StackSize.Common.SPLASH_POTION.get());
      ComponentUtil.MaxStackSize.set(Items.TOTEM_OF_UNDYING, StackSize.Common.TOTEM_OF_UNDYING.get());
      ComponentUtil.MaxStackSize.set(Items.WRITABLE_BOOK, StackSize.Common.WRITABLE_BOOK.get());
      ComponentUtil.MaxStackSize.set(Items.WRITTEN_BOOK, StackSize.Common.WRITTEN_BOOK.get());

      // pastel
      ComponentUtil.MaxStackSize.set(id("pastel:aether_vestiges"), StackSize.Pastel.AETHER_VESTIGES.get());
      ComponentUtil.MaxStackSize.set(id("pastel:bag_of_holding"), StackSize.Pastel.BAG_OF_HOLDING.get());
      ComponentUtil.MaxStackSize.set(id("pastel:cinderhearth"), StackSize.Pastel.CINDERHEARTH.get());
      ComponentUtil.MaxStackSize.set(id("pastel:clotted_cream"), StackSize.Pastel.CLOTTED_CREAM.get());
      ComponentUtil.MaxStackSize.set(id("pastel:color_picker"), StackSize.Pastel.COLOR_PICKER.get());
      ComponentUtil.MaxStackSize.set(id("pastel:crystal_apothecary"), StackSize.Pastel.CRYSTAL_APOTHECARY.get());
      ComponentUtil.MaxStackSize.set(id("pastel:crystallarieum"), StackSize.Pastel.CRYSTALLARIEUM.get());
      ComponentUtil.MaxStackSize.set(id("pastel:downstone_fragments"), StackSize.Pastel.DOWNSTONE_FRAGMENTS.get());
      ComponentUtil.MaxStackSize.set(id("pastel:dragonbone_broth"), StackSize.Pastel.DRAGONBONE_BROTH.get());
      ComponentUtil.MaxStackSize.set(id("pastel:enchanter"), StackSize.Pastel.ENCHANTER.get());
      ComponentUtil.MaxStackSize.set(id("pastel:ender_splice"), StackSize.Pastel.ENDER_SPLICE.get());
      ComponentUtil.MaxStackSize.set(id("pastel:jadeite_lotus_flower"), StackSize.Pastel.JADEITE_LOTUS_FLOWER.get());
      ComponentUtil.MaxStackSize.set(id("pastel:moonstone_core"), StackSize.Pastel.MOONSTONE_CORE.get());
      ComponentUtil.MaxStackSize.set(id("pastel:palteria_gem"), StackSize.Pastel.PALTERIA_GEM.get());
      ComponentUtil.MaxStackSize.set(id("pastel:potion_workshop"), StackSize.Pastel.POTION_WORKSHOP.get());
      ComponentUtil.MaxStackSize.set(id("pastel:resonance_shard"), StackSize.Pastel.RESONANCE_SHARD.get());
      ComponentUtil.MaxStackSize.set(id("pastel:spirit_instiller"), StackSize.Pastel.SPIRIT_INSTILLER.get());
      ComponentUtil.MaxStackSize.set(id("pastel:star_fragment"), StackSize.Pastel.STAR_FRAGMENT.get());
      ComponentUtil.MaxStackSize.set(id("pastel:stardust_block"), StackSize.Pastel.STARDUST_BLOCK.get());
      ComponentUtil.MaxStackSize.set(id("pastel:stratine_gem"), StackSize.Pastel.STRATINE_GEM.get());
      ComponentUtil.MaxStackSize.set(id("pastel:triple_meat_pot_pie"), StackSize.Pastel.TRIPLE_MEAT_POT_PIE.get());
      ComponentUtil.MaxStackSize.set(id("pastel:triple_meat_pot_stew"), StackSize.Pastel.TRIPLE_MEAT_POT_STEW.get());

      // farmersdelight
      ComponentUtil.MaxStackSize.set(id("farmersdelight:cooking_pot"), StackSize.Farmersdelight.COOKING_POT.get());
   }
}
