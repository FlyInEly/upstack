package flyinely.mcm.upstack.mixin.patch.cooldown;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import flyinely.mcm.upstack.registry.ModConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EggItem.class)
public class EggItemMixin {
	
	/**
	 * Sets and enforces a use cooldown for {@link Items#SNOWBALL} using the cooldown duration (in ticks) supplied by
	 * {@link ModConfig.Patch.Cooldown#SNOWBALL}, if greater than zero.
	 */
	@WrapMethod(method = "use")
	protected InteractionResultHolder<ItemStack> useWithCooldown(Level level, Player player, InteractionHand hand, Operation<InteractionResultHolder<ItemStack>> original) {
		int duration = ModConfig.Patch.Cooldown.EGG.getAsInt();
		if (duration > 0) {
			ItemStack stack = player.getItemInHand(hand);
			ItemCooldowns cooldowns = player.getCooldowns();
			if (cooldowns.isOnCooldown(stack.getItem())) {
				// Exit early if currently on cooldown
				return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
			}
			cooldowns.addCooldown(Items.EGG, duration);
		}
		// Call the original behavior (not currently on cooldown)
		return original.call(level, player, hand);
	}
	
}
