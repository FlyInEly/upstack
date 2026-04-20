package flyinely.mcm.upstack.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Player.class)
public class PlayerMixin {

   @WrapMethod(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;")
   private ItemEntity handleDropItem(ItemStack droppedItem, boolean dropAround, boolean includeThrowerName, Operation<ItemEntity> original) {
      ItemEntity result = original.call(droppedItem, dropAround, includeThrowerName);
      if (result != null) {
         // TODO: Only hook this class (EventBus?) if autofix is enabled on the server.
         // TODO: Patch visually incorrect item counts, or include as limitation of the mod
         // Using droppedItem works because droppedItem is mutable and is retained by original.call
         if (ItemComponentUtil.reset(droppedItem, DataComponents.MAX_STACK_SIZE)) {
            Constants.LOG.debug("Set the max stack size of item entity {} to the default for item {}", result, droppedItem.getItem());
         }
      }
      return result;
   }

}
