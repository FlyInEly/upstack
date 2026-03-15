package flyinely.mcm.upstack.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class TagUtil {
	
	public static @NotNull TagKey<Item> item(ResourceLocation id) {
		return TagKey.create(Registries.ITEM, id);
	}
	
	public static @NotNull TagKey<Item> item(String location) {
		return TagKey.create(Registries.ITEM, ResUtil.id(location));
	}
	
	public static <T> String tagString(TagKey<T> tag) {
		return "#" + tag.location();
	}
	
}
