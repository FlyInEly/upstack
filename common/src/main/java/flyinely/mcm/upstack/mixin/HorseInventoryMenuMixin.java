package flyinely.mcm.upstack.mixin;


import flyinely.mcm.upstack.Constants;
import net.minecraft.world.inventory.HorseInventoryMenu;
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
 * Common-side mixin.
 * Fixes issues due to the vanilla assumption that {@link net.minecraft.world.item.SaddleItem} is not stackable.
 * Affects horses, mules, and donkeys (but not striders).
 * <ul>
 * <li>Container slot 0 holds up to 64 saddles, but equipping >1 saddle is redundant.</li>
 * <li>Container slot 1 holds up to 1 horse armor (no issue).</li>
 * <li>Container slots 2+ hold up to 64 inventory items each (no issue).</li>
 * </ul>
 */
@Mixin(HorseInventoryMenu.class)
public abstract class HorseInventoryMenuMixin {

   /* LATER: Trying to find how to solve the general vanilla bug that shift-clicking to equip an item with MSS
   greater than the target slot also moves the item (less 1 count) as if the target slot were full.
   This can be seen with Carved Pumpkin to player and Carpet to llama equipping, but the brewing stand
   does NOT have this issue. Figure out what BrewingStandMenu does right by cancelling things one at a time. */
	
	@Unique
	private static final Logger upstack$LOG = LoggerFactory.getLogger(Constants.LOG.getName() + "/" + HorseInventoryMenuMixin.class.getSimpleName());
	
	@ModifyArg(
			method = "<init>",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/inventory/HorseInventoryMenu;addSlot(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;"
			)
	)
	Slot modifyMaxStackSize(@NotNull Slot original) {
		
		// Modify saddle slot only
		if (original.getContainerSlot() == 0) {
			upstack$LOG.debug("Restricting the max stack size of the horse saddle slot (container slot: 0).");
			return new Slot(original.container, original.getContainerSlot(), original.x, original.y) {
				
				// Override max stack size
				
				@Override
				public int getMaxStackSize() { return 1; }
				
				// Delegate other behavior(s)
				
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) { return original.mayPlace(stack); }
				
				@Override
				public boolean isActive() { return original.isActive(); }
			};
		}
		return original;
	}
	
}