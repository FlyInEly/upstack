package flyinely.mcm.upstack.event;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.item.ItemEntity;

public class PlayerDropItemHandler {
	
	// TODO: Only hook this class (EventBus?) if autofix is enabled on the server.
	public static void onPlayerDropItem(ItemEntity item) {
		resetMaxStackSize(item);
	}
	
	private static void resetMaxStackSize(ItemEntity item) {
		if (ItemComponentUtil.reset(item.getItem(), DataComponents.MAX_STACK_SIZE)) {
			Constants.LOG.debug("Set the max stack size of item entity {} to the default for item {}", item.getId(), item.getItem().getItem());
		}
	}
	
	
}
