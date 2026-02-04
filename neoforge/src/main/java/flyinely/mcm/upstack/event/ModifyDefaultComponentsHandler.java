package flyinely.mcm.upstack.event;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.platform.ItemPredicateRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class ModifyDefaultComponentsHandler {

   @SubscribeEvent
   static void onEvent(ModifyDefaultComponentsEvent event) {
      ItemPredicateRegistries.STACK_SIZE.stream().forEach(i -> {
         event.modifyMatching(, b -> b.set(DataComponents.MAX_STACK_SIZE, i.valueSupplier().get()));
      });
   }

}
