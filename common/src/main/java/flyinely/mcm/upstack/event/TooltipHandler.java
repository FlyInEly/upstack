package flyinely.mcm.upstack.event;

import flyinely.mcm.upstack.config.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class TooltipHandler {

   // Only renders if config is enabled.
   public static void onHandleTooltip(ItemStack stack, List<Component> list) {
      if (Config.Display.OVERSTACKED_TOOLTIP.get() && stack.getCount() > stack.getItem().getDefaultMaxStackSize()) {
         var component = Component.literal("Overstacked (" + stack.getCount() + "/" + stack.getItem().getDefaultMaxStackSize() + ")")
               .withColor(Config.Display.OVERSTACKED_COLOR.get());
         if (list.size() > 1) {
            list.add(1, component);
         } else {
            list.add(component);
         }
      }
   }

}