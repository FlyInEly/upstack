package flyinely.mcm.upstack.mixin;

import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {


//   @Inject(method = "setCarried", at = @At(value = "HEAD"))
//   void test(ItemStack stack, CallbackInfo ci) {
//      ItemComponentUtil.reset(stack, DataComponents.MAX_STACK_SIZE);
//   }

   @Shadow
   @Final
   public NonNullList<Slot> slots;

//   @WrapOperation(method = "moveItemStackTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isSameItemSameComponents(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z"))
//   boolean test(ItemStack stack, ItemStack other, Operation<Boolean> original) {
//      ItemComponentUtil.reset(stack, DataComponents.MAX_STACK_SIZE);
//      ItemComponentUtil.reset(other, DataComponents.MAX_STACK_SIZE);
//      // one of these will get reset O(n) times, which is not good. this is temoprary
//      return original.call(stack, other);
//   }

   // This seems to cover carrying and merging, including by quick move, ubt not what happens when
   // clicking an overstacked stack, nor the issue that overstacked unstackables are not showing their stack size
   @Inject(method = "doClick", at = @At(value = "HEAD"))
   void test(int slotId, int button, ClickType clickType, Player player, CallbackInfo ci) {
      if (slotId >= 0 && slotId < slots.size()) {
         ItemComponentUtil.reset(slots.get(slotId).getItem(), DataComponents.MAX_STACK_SIZE);
      }
   }

}
