package flyinely.mcm.upstack.mixin.patch.assumption;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import flyinely.mcm.upstack.registry.ModConfig;
import flyinely.mcm.upstack.registry.ModConfig.Patch.Fix;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.world.inventory.AnvilMenu.*;

/**
 * Common-side mixin. Does nothing if {@link ModConfig.Patch.Fix#FIX_ANVIL_ENCHANTING} is disabled.
 * Fixes issues caused by vanilla's assumption that enchanted books are not stackable.
 */
@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
	
	@Shadow
	private int repairItemCountCost;
	
	/**
	 * Pretends that there's only one item in the input slot so that the game's logic doesn't auto-set
	 * the price to 40 levels, the "too expensive" threshold in survival.
	 */
	@WrapOperation(
			method = "createResult",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I", slice = "0"),
			slice = @Slice(
					from = @At(
							value = "INVOKE",
							target = "Lnet/minecraft/world/item/enchantment/Enchantment;getAnvilCost()I"),
					to = @At(
							value = "INVOKE",
							target = "Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V")))
	int fixResultCost(ItemStack inputStackInstance, Operation<Integer> getCountOriginal) {
		return (Fix.FIX_ANVIL_ENCHANTING.get() && getCountOriginal.call(inputStackInstance) > 1) ? 1 : getCountOriginal.call(inputStackInstance);
	}
	
	
	@Inject(method = "createResult", at = @At("TAIL"))
	void setOutputCountOnTake(CallbackInfo ci) {
		if (!Fix.FIX_ANVIL_ENCHANTING.get()) return; // exit if config disabled
		
		var self = (AnvilMenu) (Object) this;
		Slot resultSlot = self.getSlot(RESULT_SLOT);
		if (self.getSlot(INPUT_SLOT).getItem().getCount() > 1
				&& self.getSlot(ADDITIONAL_SLOT).hasItem()
				&& resultSlot.getItem().getCount() > 1) {
			resultSlot.set(resultSlot.getItem().copyWithCount(1));
		}
	}
	
	/**
	 * Create the next result immediately after the previous is taken, if possible (i.e. the result slot still contains an item).
	 *
	 * @implNote TODO update: The implementation calls {@link AnvilMenu#createResult()} at the cost of some unnecessary calculations.
	 * However, the call itself - or comparable inline logic (too fragile) - is needed because {@link AnvilMenu} expects
	 * that a result is only possible after an update to either input slot. But #decrementInputsOnTake() allows
	 * for a "1 (of >1) items + 1 (of >1) items -> 1 item" case, where a next (presumably identical) result is possible
	 * immediately after the previous result is taken. Without this injected recalculation, vanilla fails to recalculate
	 * result cost and make the item takeable.
	 */
	@Inject(method = "onTake", at = @At("TAIL"))
	void refreshResultOnTake(Player pPlayer, ItemStack pStack, CallbackInfo ci) {
		if (!Fix.FIX_ANVIL_ENCHANTING.get()) return; // exit if config disabled
		
		var self = (AnvilMenu) (Object) this;
		if (self.getSlot(RESULT_SLOT).hasItem()) {
			self.createResult(); // recalculate cost
		}
	}
	
	/**
	 * When the result is taken from an anvil with non-empty input slots, decrement the input stacks instead of clearing them.
	 * <ul>
	 * <li> Non-empty + empty (e.g. bulk renaming) is unchanged. </li>
	 * <li> Single + single (e.g. equipment combination) is unchanged. </li>
	 * <li> Single + multiple (e.g. equipment repair, equipment enchanting) is changed so that equipment enchanting applies
	 * one additional book at a time. </li>
	 * <li> Multiple + multiple (e.g. enchantment book combination) is changed so that enchantment book combination applies
	 * one additional book to one input book at a time. </li>
	 * </ul>
	 */
	@WrapOperation(method = "onTake", at = @At(
			value = "INVOKE", target = "Lnet/minecraft/world/Container;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
	void shrinkInputStacks(Container instance, int pSlot, ItemStack pStack, Operation<Void> original) {
		if (Fix.FIX_ANVIL_ENCHANTING.get() && pStack.isEmpty()) {
			if (pSlot == INPUT_SLOT && !instance.getItem(ADDITIONAL_SLOT).isEmpty()) {
				pStack = instance.getItem(INPUT_SLOT);
				original.call(instance, pSlot, pStack.copyWithCount(pStack.getCount() - 1));
				return;
			} else if (pSlot == ADDITIONAL_SLOT && this.repairItemCountCost == 0) {
				pStack = instance.getItem(ADDITIONAL_SLOT);
				original.call(instance, pSlot, pStack.copyWithCount(pStack.getCount() - 1));
				return;
			}
		}
		original.call(instance, pSlot, pStack);
	}
}
