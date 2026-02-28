package flyinely.mcm.upstack.mixin;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Invoker for adding a slot to an {@link AbstractContainerMenu}.
 */
@Mixin(AbstractContainerMenu.class)
interface AbstractContainerMenuInvoker {

   @Invoker
   Slot callAddSlot(Slot slot);

}
