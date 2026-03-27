package flyinely.mcm.upstack.registry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static flyinely.mcm.upstack.util.TagUtil.item;

public class MItemTags {
	
	/**
	 * Common tags are unified between Fabric and NeoForge. However, general sources on available tags
	 * may contain tags only available in versions after 1.21.1, e.g. #c:minecarts, which need to be
	 * manually populated by this mod.
	 *
	 * @see <a href="https://wiki.fabricmc.net/community:common_tags">Common tags - Fabric</a>
	 * @see <a href="https://docs.neoforged.net/docs/resources/server/tags/">Common tags - NeoForge</a>
	 */
	public static class C {
		public static final TagKey<Item> BANNER_PATTERNS = item("c:banner_patterns");              // upstack-populated
		public static final TagKey<Item> BUCKETS = item("c:buckets");                             // loader-populated
		public static final TagKey<Item> CHICKEN_EGGS = item("c:chicken_eggs");                   // upstack-populated
		public static final TagKey<Item> MINECARTS = item("c:minecarts");                         // upstack-populated
		public static final TagKey<Item> HORSE_ARMOR = item("c:horse_armor");                     // upstack-populated
		public static final TagKey<Item> MUSIC_DISCS = item("c:music_discs");                       // loader-populated
		/** Liquid food items served in a bowl, especially if their name contains "soup" or "stew". */
		public static final TagKey<Item> SOUPS = item("c:soups");                                 // neoforge-populated
		/** Milk contained in a glass bottle. */
		public static final TagKey<Item> MILK_BOTTLES = item("c:milk_bottles"); // upstack-populated
	}
	
	public static class Pastel {
		public static final TagKey<Item> PEDESTALS = item("pastel:pedestals");                    // mod-populated
		public static final TagKey<Item> SHOOTING_STARS = item("pastel:shooting_stars");          // mod-populated
		public static final TagKey<Item> BULBS = item("pastel:bulbs");                            // upstack-populated
		public static final TagKey<Item> ITEM_BOWLS = item("pastel:item_bowls");                  // upstack-populated
		public static final TagKey<Item> NETWORK_NODES = item("pastel:network_nodes");            // upstack-populated
		public static final TagKey<Item> ROUNDELS = item("pastel:roundels");                      // upstack-populated
		public static final TagKey<Item> STRUCTURE_UPGRADES = item("pastel:structure_upgrades");  // upstack-populated
		public static final TagKey<Item> FUSION_SHRINES = item("pastel:fusion_shrines");          // upstack-populated
	}
	
	public static class Farmersdelight {
		/** Block items which cannot be directly eaten, but can be subdivided into more than one edible portion after placement. */
		public static final TagKey<Item> FEASTS = item("farmersdelight:feasts"); // mod-populated
	}
}
