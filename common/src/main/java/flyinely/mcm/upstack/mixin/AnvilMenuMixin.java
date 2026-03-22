package flyinely.mcm.upstack.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Common-side mixin.
 * Fixes issues caused by the vanilla assumption that {@link net.minecraft.world.item.Items#ENCHANTED_BOOK} is not stackable.
 */
@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
	
	@Shadow
	private int repairItemCountCost;
	
	/**
	 * When taking a stack from {@link AnvilMenu#RESULT_SLOT}, decrement the stack in {@link AnvilMenu#ADDITIONAL_SLOT}
	 * instead of clearing it.
	 */
	@WrapOperation(method = "onTake", at = @At(
			value = "INVOKE", target = "Lnet/minecraft/world/Container;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
	void shrinkAdditionalStack(Container instance, int pSlot, ItemStack pStack, Operation<Void> original) {
		if (pSlot == AnvilMenu.ADDITIONAL_SLOT && this.repairItemCountCost == 0 && pStack.isEmpty()) {
			ItemStack additionalStack = instance.getItem(AnvilMenu.ADDITIONAL_SLOT);
			original.call(instance, pSlot, additionalStack.copyWithCount(additionalStack.getCount() - 1));
		} else {
			original.call(instance, pSlot, pStack);
		}
	}
}
