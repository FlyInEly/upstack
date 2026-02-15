package flyinely.mcm.upstack.registry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static flyinely.mcm.upstack.util.TagUtil.item;

public class MItemTags {

	public static class C {
		public static final TagKey<Item> BUCKETS = item("c:buckets"); // auto-populated
		public static final TagKey<Item> CHICKEN_EGGS = item("c:chicken_eggs"); // TODO: mod-populated
	}
	
}
