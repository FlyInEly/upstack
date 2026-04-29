package flyinely.mcm.upstack.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static flyinely.mcm.upstack.util.ComponentUtil.MaxStackSize;

// TODO: FIX: First load of the world after a stack size reduction seems to cause overstacks
//    to degrade to unstackables. Reloading the world a second time fixes the issue.
@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {
	
	// TODO: It seems like none of this is necessary? In both fabric and NeoForge.
	//		See on-paper design reference for possible ways to change stack size correction, but
	//		note that it is more involved. Probably would involve modifying doClick
	// TODO: Fix issue with stack size set to 0 does not behave as expected
   // May be necessary here for the quick stack to cursor double click action.
	@Inject(method = "setCarried", at = @At(value = "HEAD"))
	void onSetCarried(ItemStack stack, CallbackInfo ci) {
		MaxStackSize.resetIfAtMostDefault(stack); // Only correct max stack size when count is below it
	}
//
	@Shadow
	@Final
	public NonNullList<Slot> slots;


   // Necessary to utilize increased default max stack size
	@WrapOperation(method = "moveItemStackTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isSameItemSameComponents(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z"))
	boolean onMoveItemStackTo(ItemStack stack, ItemStack other, Operation<Boolean> original) {
		MaxStackSize.resetIfAtMostDefault(stack);
		MaxStackSize.resetIfAtMostDefault(other);
		// one of these will get reset O(n) times, which is not good. this is temoprary
		return original.call(stack, other);
	}
//
//	//    This seems to cover carrying and merging, including by quick move, ubt not what happens when
//	//    clicking an overstacked stack, nor the issue that overstacked unstackables are not showing their stack size
	@Inject(method = "doClick", at = @At(value = "HEAD"))
	void onDoClick(int slotId, int button, ClickType clickType, Player player, CallbackInfo ci) {
		if (slotId >= 0 && slotId < slots.size()) {
			MaxStackSize.resetIfAtMostDefault(slots.get(slotId).getItem());
		}
	}
	
}
