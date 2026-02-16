package flyinely.mcm.upstack.registry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static flyinely.mcm.upstack.util.TagUtil.item;

public class MItemTags {
	
	/**
	 * Common tags are unified between Fabric and NeoForge. However, general sources on available tags
	 * may contain tags only available in versions after 1.21.1, e.g. #c:minecarts, which need to be
	 * manually populated by this mod.
	 * @see <a href="https://wiki.fabricmc.net/community:common_tags">Common tags - Fabric</a>
	 * @see <a href="https://docs.neoforged.net/docs/resources/server/tags/">Common tags - NeoForge</a>
	 */
	public static class C {
		public static final TagKey<Item> BUCKETS = item("c:buckets"); // loader-populated
		public static final TagKey<Item> CHICKEN_EGGS = item("c:chicken_eggs"); // TODO: mod-populated
		public static final TagKey<Item> MINECARTS = item("c:minecarts"); // TODO: mod-populated
	}
	
}
