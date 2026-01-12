package flyinely.mcm.upstack.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Utility for manipulating {@link ItemStack} objects.
 */
public class StackUtil {

   /**
    * Handles inventory changes when a player successfully uses a filled bucket.
    * <p>
    * Returns the updated bucket stack, and adds the remainder (default: {@link Items#BUCKET}) to the
    * player's inventory (or drops it). If {@code remainderReplacesLast} is {@code true}, the remainder
    * will replace the last item in the bucket stack, on parity with {@link net.minecraft.world.item.PotionItem}.
    *
    * @param bucketStack           the bucket stack emptied
    * @param player                the player
    * @param remainderReplacesLast whether the remainder should replace the last item in the bucket stack
    * @return the stack after use
    * @apiNote Assumes that the bucket stack has a count of at least one.
    */
   public static ItemStack emptyBucket(@NotNull final ItemStack bucketStack, @NotNull Player player, boolean remainderReplacesLast) {
      if (player.hasInfiniteMaterials()) // If creative mode...
         return bucketStack; // Do not replace stack

      // Get remainder (default: bucket)
      @NotNull ItemStack remainderStack = Optional.ofNullable(bucketStack.getItem().getCraftingRemainingItem()).orElse(Items.BUCKET).getDefaultInstance();

      if (bucketStack.getCount() == 1 && remainderReplacesLast) // If last item in stack...
         return remainderStack; // Replace stack with remainder

      // Add remainder to inventory (or drop it)
      if (!player.getInventory().add(remainderStack)) {
         player.drop(remainderStack, false);
      }
      return bucketStack.copyWithCount(bucketStack.getCount() - 1); // Replace stack with decremented copy
   }
}
