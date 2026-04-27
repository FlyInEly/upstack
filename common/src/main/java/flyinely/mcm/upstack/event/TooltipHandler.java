package flyinely.mcm.upstack.event;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class TooltipHandler {
	
	public static void onHandleTooltip(ItemStack stack, List<Component> list) {
		if (stack.getCount() > ItemComponentUtil.getDefault(stack, DataComponents.MAX_STACK_SIZE)) {
			if (list.size() > 1) {
				list.add(1, Component
						.literal(
								"Overstacked (" + stack.getCount() + "/" + ItemComponentUtil.getDefault(stack, DataComponents.MAX_STACK_SIZE) + ")")
						.withColor(Constants.OVERSTACKED_COLOR)); // TODO: Make "overstacked" translatable
			}
		}
	}
	
}
