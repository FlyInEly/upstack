package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.annotation.CContract.StaticRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import static flyinely.mcm.upstack.util.TagUtil.item;

/**
 * Static registry of the non-vanilla item tags used by Upstack.
 *
 * @see net.minecraft.tags.ItemTags
 **/
@ApiStatus.Internal
@StaticRegistry
public class ModItemTags {

	/**
	 * Item tags in the {@code c} namespace used by upstack.
    * <p>
    * Common tags are unified between Fabric and NeoForge. However, general sources on available tags
	 * may contain tags only available in versions after 1.21.1 (e.g. {@code #c:minecarts}) which need to be
	 * manually populated by this mod.
	 *
	 * @see <a href="https://wiki.fabricmc.net/community:common_tags">Common tags - Fabric</a>
	 * @see <a href="https://docs.neoforged.net/docs/resources/server/tags/">Common tags - NeoForge</a>
	 */
	@StaticRegistry
   public static class C {

      // --- Populated by NeoForge and Fabric

		public static final TagKey<Item> BUCKETS              = item("c:buckets");
		public static final TagKey<Item> MUSIC_DISCS          = item("c:music_discs");

      // --- Populated by NeoForge and upstack

      /**Liquid food contained in a bowl, especially "soup" and "stew" items. */
      public static final TagKey<Item> SOUPS                = item("c:soups");

      // --- Populated by upstack

      public static final TagKey<Item> BANNER_PATTERNS      = item("c:banner_patterns");
      public static final TagKey<Item> CHICKEN_EGGS         = item("c:chicken_eggs");
      public static final TagKey<Item> HORSE_ARMOR          = item("c:horse_armor");
      public static final TagKey<Item> MINECARTS            = item("c:minecarts");

      /** Milk contained in a glass bottle. */
      public static final TagKey<Item> MILK_BOTTLES         = item("c:milk_bottles");
   }

   /** Item tags in the {@code pastel} namespace used by Upstack. */
	@StaticRegistry
   public static class Pastel {

      // --- Populated by pastel

      public static final TagKey<Item> SHOOTING_STARS       = item("pastel:shooting_stars");

      // --- Populated by upstack

		public static final TagKey<Item> FUSION_SHRINES       = item("pastel:fusion_shrines");
		public static final TagKey<Item> ITEM_BOWLS           = item("pastel:item_bowls");
		public static final TagKey<Item> NETWORK_NODES        = item("pastel:network_nodes");
		public static final TagKey<Item> STRUCTURE_UPGRADES   = item("pastel:structure_upgrades");
      public static final TagKey<Item> BULBS                = item("pastel:bulbs");
      public static final TagKey<Item> PEDESTALS            = item("pastel:pedestals");
      public static final TagKey<Item> ROUNDELS             = item("pastel:roundels");
	}

   /** Item tags in the {@code farmersdelight} namespace used by Upstack. */
	@StaticRegistry
   public static class Farmersdelight {

      // --- Populated by farmersdelight

      /** Block items which are not directly consumable, but can be subdivided into multiple edible portions once placed. */
      public static final TagKey<Item> FEASTS               = item("farmersdelight:feasts");
	}
}
