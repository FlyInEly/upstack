package flyinely.mcm.upstack.mixin.patch.assumption;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.registry.ModConfig;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * Common-side mixin. Does nothing if {@link ModConfig.Patch.Fix#LIMIT_BANNER_PATTERN_SLOT} is disabled.
 * Fixes issues due to vanilla's assumption that banner patterns are not stackable.
 * <ul>
 * <li>Slot 2 holds up to 64 banner patterns. Issue: Using >1 banner pattern is redundant - banner patterns are not consumed
 * on use (unlike the dyes used to print them).</li>
 * </ul>
 */
@Mixin(LoomMenu.class)
public class LoomMenuMixin {
	
	@Unique
	private static final Logger upstack$LOG = LoggerFactory.getLogger(Constants.LOG.getName() + "/" + LoomMenuMixin.class.getSimpleName());
	
	@ModifyArg(
			method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/inventory/LoomMenu;addSlot(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;"
			)
	)
	Slot modifyMaxStackSize(Slot slot) {
		
		// If enabled
		if (ModConfig.Patch.Fix.LIMIT_BANNER_PATTERN_SLOT.get()) {
			
			// Modify banner pattern slot only
			if (slot.getContainerSlot() == 2) {
				upstack$LOG.debug("Restricting the max stack size of loom banner pattern slot (container slot: 2).");
				return new Slot(slot.container, slot.getContainerSlot(), slot.x, slot.y) {
					
					// Override max stack size
					@Override
					public int getMaxStackSize() {return 1;}
					
					// Delegate other behavior(s)
					public boolean mayPlace(@NotNull ItemStack stack) {return slot.mayPlace(stack);}
				};
			}
		}
		return slot;
	}
}
