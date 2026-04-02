package flyinely.mcm.upstack.mixin.compat;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import earth.terrarium.pastel.inventories.BedrockAnvilScreenHandler;
import flyinely.mcm.upstack.mixin.AnvilMenuMixin;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

// FIXME: Does this cause issues if Pastel is not loaded on NeoForge? No error should be printed in that situation.

/**
 * Common-side compat mixin for Pastel.
 * Fixes issues caused by the Pastel assumption that {@link net.minecraft.world.item.Items#ENCHANTED_BOOK} is not stackable,
 * which appears to originate from the mod's replication of vanilla {@link AnvilMenu} code.
 * <p>
 * Not fixing the bedrock anvil not allowing combination of enchanted books, which is Pastel's responsibility. If that is implemented
 * similar to vanilla logic, compat fixes will be needed.
 * @see AnvilMenuMixin
 */
@Mixin(BedrockAnvilScreenHandler.class)
public class BedrockAnvilScreenHandlerMixin {
	
	@Shadow
	private int repairItemCount;
	
	/**
	 * When taking a stack from the result slot, decrement the stack in the additional slot instead of clearing it.
	 */
	@WrapOperation(method = "onTake", at = @At(
			value = "INVOKE", target = "Lnet/minecraft/world/Container;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
	void shrinkAdditionalStack(Container instance, int pSlot, ItemStack pStack, Operation<Void> original) {
		if (pSlot == BedrockAnvilScreenHandler.SECOND_INPUT_SLOT_INDEX && this.repairItemCount == 0 && pStack.isEmpty()) {
			ItemStack additionalStack = instance.getItem(BedrockAnvilScreenHandler.SECOND_INPUT_SLOT_INDEX);
			original.call(instance, pSlot, additionalStack.copyWithCount(additionalStack.getCount() - 1));
		} else {
			original.call(instance, pSlot, pStack);
		}
	}
}
