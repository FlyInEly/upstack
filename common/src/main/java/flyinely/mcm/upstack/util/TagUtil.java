package flyinely.mcm.upstack.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class TagUtil {

	public static @NotNull TagKey<Item> item(String location) {
		return TagKey.create(Registries.ITEM, ResUtil.id(location));
	}
	
	@Contract(pure = true)
   public static <T> @NotNull String tagString(TagKey<T> tag) {
		return "#" + tag.location();
	}
	
}
