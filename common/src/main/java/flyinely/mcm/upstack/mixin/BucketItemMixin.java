package flyinely.mcm.upstack.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import flyinely.mcm.upstack.util.StackUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Common-side mixin to correct issues caused by the assumption that {@link BucketItem} is unstackable.
 */
@Mixin(BucketItem.class)
public class BucketItemMixin {

   /**
    * Successfully using a stack of multiple filled buckets decrements instead of erasing it.
    *
    * @implNote Changes the semantics of the method wrapped. See @see.
    * @see StackUtil#emptyBucket(ItemStack, Player, boolean)
    */
   @WrapMethod(method = "getEmptySuccessItem")
   private static ItemStack getEmptySuccessItem(ItemStack stack, Player player, Operation<ItemStack> original) {
      return StackUtil.emptyBucket(stack, player, true); // TODO: Config remainderReplacesLast
   }

}
