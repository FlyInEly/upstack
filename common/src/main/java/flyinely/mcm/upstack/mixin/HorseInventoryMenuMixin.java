package flyinely.mcm.upstack.mixin;


import flyinely.mcm.upstack.Constants;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.HorseInventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * Common-side mixin.
 * Fixes issues due to the vanilla assumption that {@link net.minecraft.world.item.SaddleItem} is not stackable.
 * Affects horses, mules, and donkeys. As a side note, striders do not require this fix.
 * <ul>
 * <li>Slot 0 holds up to 64 saddles. Issue: Equipping >1 saddle is redundant.</li>
 * <li>Slot 1 holds up to 1 horse armor. No issue.</li>
 * <li>Slots 2+ hold up to 64 inventory items each. No issue.</li>
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
	
	@ModifyArgs(
			method = "<init>",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/inventory/HorseInventoryMenu;addSlot(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;"
			)
	)
	void modifyMaxStackSize(Args args, int containerId, Inventory inventory, Container horseContainer, final AbstractHorse horse, int columns) {
		
		// Safeguard against unsafe mixin
		if (args.size() != 1) {
			upstack$LOG.error("Failed to restrict the max stack size of horse saddle slot (index: 0, container slot: 0): unexpected argument count");
			return;
		}
		
		// Modify saddle slot only
		//		- Index 0, container slot 0.
		//		- Does not accept horse armor. Use "!original.mayPlace(Items.LEATHER_HORSE_ARMOR...)" rather than
		//		  "original.mayPlace(Items.SADDLE...)" because the former depends only on ItemStack#is(), whereas the
		//		  latter also depends on Slot#hasItem() and AbstractHorse#isSaddleable().
		final Slot original = args.get(0);
		if (original.index == 0 && original.getContainerSlot() == 0 && !original.mayPlace(Items.LEATHER_HORSE_ARMOR.getDefaultInstance())) {
			args.set(0, new Slot(original.container, original.index, original.x, original.y) {
				
				//#region Override max stack size
				
				@Override
				public int getMaxStackSize() {
					return 1;
				}
				
				//#endregion
				
				//#region Delegate other behaviors
				
				public boolean mayPlace(@NotNull ItemStack stack) {
					return original.mayPlace(stack);
				}
				
				public boolean isActive() {
					return original.isActive();
				}
				
				//#endregion
			});
			upstack$LOG.debug("Restricted the max stack size of horse saddle slot (index: 0, container slot: 0).");
		}
		
	}
	
}