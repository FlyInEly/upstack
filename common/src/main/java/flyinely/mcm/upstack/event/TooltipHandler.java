package flyinely.mcm.upstack.event;

import flyinely.mcm.upstack.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static flyinely.mcm.upstack.util.ItemComponentUtil.*;

public class TooltipHandler {
	
	public static void onHandleTooltip(ItemStack stack, List<Component> list) {
		if (stack.getCount() > MaxStackSize.getDefault(stack)) {
			if (list.size() > 1) {
				list.add(1, Component
						.literal(
								"Overstacked (" + stack.getCount() + "/" + MaxStackSize.getDefault(stack) + ")")
						.withColor(Constants.OVERSTACKED_COLOR)); // TODO: Make "overstacked" translatable
			}
		}
	}
	
}
