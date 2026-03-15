package flyinely.mcm.upstack.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import flyinely.mcm.upstack.config.Config;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * TODO: Fix these temporary docs
 * Sets and enforces a joint use cooldown for {@link Items#SPLASH_POTION} and {@link Items#LINGERING_POTION}
 * using the cooldown duration (in ticks) supplied by {@link Config.Cooldowns#POTION_THROW_COOLDOWN}, if greater
 * than zero.
 *
 * @implNote For optimization and safety, it is assumed that the used item is either a {@link Items#SPLASH_POTION}
 * or {@link Items#LINGERING_POTION}, and that its cooldown thus reflects the joint cooldown. The {@link Items#SPLASH_POTION}
 * and {@link Items#LINGERING_POTION} cooldowns are set, and the used item's cooldown is enforced.
 */
@Mixin(ThrowablePotionItem.class)
abstract class ThrowablePotionItemMixin {

   /**
    * Sets and enforces a joint use cooldown for {@link Items#SPLASH_POTION} and {@link Items#LINGERING_POTION}
    * using the cooldown duration (in ticks) supplied by {@link Config.Cooldowns#POTION_THROW_COOLDOWN}, if greater
    * than zero.
    *
    * @implNote For optimization and safety, it is assumed that the used item is either a {@link Items#SPLASH_POTION}
    * or {@link Items#LINGERING_POTION}, and that its cooldown thus reflects the joint cooldown. The {@link Items#SPLASH_POTION}
    * and {@link Items#LINGERING_POTION} cooldowns are set, and the used item's cooldown is enforced.
    */
   @Unique
	protected InteractionResultHolder<ItemStack> upstack$useWithCooldown(Level level, Player player, InteractionHand hand, Operation<InteractionResultHolder<ItemStack>> original) {
      int cooldownTicks = Config.Cooldowns.POTION_THROW_COOLDOWN.getAsInt();
      if (cooldownTicks > 0) {
            ItemStack stack = player.getItemInHand(hand);
            ItemCooldowns cooldowns = player.getCooldowns();
            // Get only this item's cooldown (optimization, safety)
            if (cooldowns.isOnCooldown(stack.getItem())) {
               // Exit early if currently on cooldown
               return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
            }
            // Set both items' cooldown
            cooldowns.addCooldown(Items.SPLASH_POTION, cooldownTicks);
            cooldowns.addCooldown(Items.LINGERING_POTION, cooldownTicks);
      }
      // Call the original behavior (not currently on cooldown)
      return original.call(level, player, hand);
   }

}
