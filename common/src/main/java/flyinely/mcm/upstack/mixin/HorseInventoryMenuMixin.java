package flyinely.mcm.upstack.mixin;


import flyinely.mcm.upstack.Constants;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.HorseInventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Common-side mixin.
 * Fixes issues due to the vanilla assumption that {@link net.minecraft.world.item.SaddleItem} is not stackable.
 * <ul>
 * <li>Slot 0 holds up to 64 saddles. Issue: Equipping >1 saddle is redundant.</li>
 * <li>Slot 1 holds up to 1 horse armor.</li>
 * <li>Slots 2+ hold up to 64 inventory items each.</li>
 * </ul>
 */
@Mixin(HorseInventoryMenu.class)
public class HorseInventoryMenuMixin {

   /* LATER: Trying to find how to solve the general vanilla bug that shift-clicking to equip an item with MSS
   greater than the target slot also moves the item (less 1 count) as if the target slot were full.
   This can be seen with Carved Pumpkin to player and Carpet to llama equipping, but the brewing stand
   does NOT have this issue. Figure out what BrewingStandMenu does right by cancelling things one at a time. */

   @Redirect(method = "<init>", at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/inventory/HorseInventoryMenu;addSlot(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;"))
   private Slot withMaxStackSize(HorseInventoryMenu instance, @NotNull Slot slot, int containerId, Inventory inventory,
                                 Container horseContainer, final AbstractHorse horse, int columns) {
      int slotIndex = slot.getContainerSlot();
      Constants.LOG.info("{}, {}", slot.index, slotIndex);

      // Subclass the saddle slot only (slot 0)
      // Issue: Need to detect that this is the horse, not armor, container
      if (slotIndex == 0 && slot.container.equals(horseContainer)) {
         slot = new Slot(slot.container, slotIndex, slot.x, slot.y) {
            // Override max stack size
            @Contract(pure = true)
            @Override
            public int getMaxStackSize() {
               return 1;
            }

            // Hardcoded replica of original behavior (b/c anonymous subclass)
            @Override
            public boolean mayPlace(@NotNull ItemStack pStack) {
               return pStack.is(Items.SADDLE) && !this.hasItem() && horse.isSaddleable();
            }

            // Hardcoded replica of original behavior (b/c anonymous subclass)
            @Override
            public boolean isActive() {
               return horse.isSaddleable();
            }
         };
      }
      return ((AbstractContainerMenuInvoker) this).callAddSlot(slot);
   }
}