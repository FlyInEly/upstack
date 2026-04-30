package flyinely.mcm.upstack.mixin.patch.data_update;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.registry.ModConfig;
import flyinely.mcm.upstack.util.ComponentUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Player.class)
public class PlayerMixin {

   // TODO: Patch picking up items of unfixed stack sizes but which fit in the current stack size ending up splitting the item stack awkwardly
	// TODO FIX: Patch using numbers to swap overstacked items with a building block, causing strange desync that makes the overstacked
	// 	item act like the building block (and even get voided upon placing the block in survival)
	//		Was not able to reproduce the voiding behavior

   @WrapMethod(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;")
   private ItemEntity handleDropItem(ItemStack droppedItem, boolean dropAround, boolean includeThrowerName, Operation<ItemEntity> original) {

      ItemEntity result = original.call(droppedItem, dropAround, includeThrowerName);
      if (ModConfig.Patch.DataUpdate.ON_DROP.get()) { // TODO: Un-hook this class if the config isn't enabled on the server at time of server load. Mark config as onWorldRestart().
         if (result != null) {
            // Using droppedItem works because droppedItem is mutable and is retained by original.call
            if (ComponentUtil.reset(droppedItem, DataComponents.MAX_STACK_SIZE)) {
               Constants.LOG.info("Set the max stack size of {} to its default", result);
            }
         }
      }
      return result;
   }

}
