package flyinely.mcm.upstack.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.ThrowablePotionItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Common-side mixin.
 * <p>
 * Attempts to fix balance issues due to the vanilla assumption that {@link LingeringPotionItem} is not stackable
 * by adding a joint cooldown for {@link ThrowablePotionItem}.
 */
@Mixin(LingeringPotionItem.class)
public final class LingeringPotionItemMixin extends ThrowablePotionItemMixin {
	
	@Contract(pure = true)
   @WrapMethod(method = "use")
   private InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand, Operation<InteractionResultHolder<ItemStack>> original) {
		return upstack$useWithCooldown(level, player, hand, original);
   }
	
}
