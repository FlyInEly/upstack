package flyinely.mcm.upstack.event;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.platform.ItemRegistries;
import flyinely.mcm.upstack.registry.StackSizes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class ModifyDefaultComponentsHandler {

   /**
    * Called during game loading, not on config reload.
    */
   @SubscribeEvent
   static void onEvent(ModifyDefaultComponentsEvent event) {
      StackSizes.init();
      ItemRegistries.STACK_SIZE.streamData().forEach(i -> {
               Constants.LOG.info("Modifying stack size");
               event.modifyMatching(i.predicate(), b -> b.set(DataComponents.MAX_STACK_SIZE, i.value()));   
            }
      );
   }

}
