package flyinely.mcm.upstack.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Common-side mixin.
 * <p>
 * Fixes issues due to the vanilla assumption that {@link SolidBucketItem} is not stackable.
 */
@Mixin(SolidBucketItem.class)
public class SolidBucketItemMixin {

   /**
    * When placing a {@link SolidBucketItem} without infinite materials:
    * <ul>
    *    <li>
    *       Replace the filled stack with the empty stack <i>only if</i> the filled stack has no items. (It has already been decremented in
    *       {@link net.minecraft.world.item.BlockItem#useOn(UseOnContext)}, which {@link SolidBucketItem#useOn(UseOnContext)} calls before this redirect.)
    *    </li>
    *    <li>
    *       Otherwise, try to add the empty stack to the inventory, dropping the stack if not possible.
    *    </li>
    * </ul>
    */
   @Redirect(method = "useOn", at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/item/BucketItem;getEmptySuccessItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/item/ItemStack;"))
    ItemStack preserveFilledStack(ItemStack filledStack, @NotNull Player player) {
      // This check is to ensure that when player.hasInfiniteMaterials(), we do not duplicate filledStack
      // by adding getEmptySuccessItem() == filledStack to the inventory.
      if (!player.hasInfiniteMaterials()) {
         ItemStack emptyStack = BucketItem.getEmptySuccessItem(filledStack, player);
         if (filledStack.isEmpty()) {
            return emptyStack;
         } else if (!player.getInventory().add(emptyStack)) {
            player.drop(emptyStack, false);
         }
      }
      return filledStack;
   }

}
