package flyinely.mcm.upstack.mixin.patch;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import flyinely.mcm.upstack.mixin.balance_change.ThrowablePotionItemMixin;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Common-side mixin.
 * <p>
 * Attempts to fix balance issues due to the vanilla assumption that {@link SplashPotionItem} is not stackable
 * by adding a joint cooldown for {@link LingeringPotionItem} and {@link net.minecraft.world.item.SplashPotionItem}.
 */
@Mixin(SplashPotionItem.class)
public abstract class SplashPotionItemMixin extends ThrowablePotionItemMixin {
	
   @Contract(pure = true)
   @WrapMethod(method = "use")
   private InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand, Operation<InteractionResultHolder<ItemStack>> original) {
      return upstack$useWithCooldown(level, player, hand, original);
   }
	
}