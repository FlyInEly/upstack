package flyinely.mcm.upstack.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import flyinely.mcm.upstack.render.Overstacked;
import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

   // Render item decoration goals:
   // - fixing non-rendering of overstacked unstackable item counts
   // - red-tinting the rendering of overstacked unstackable item counts
//   @WrapOperation(
//         method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
//         at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"))
//   int test(GuiGraphics tInstance, Font tFont, String tText, int tX, int tY, int tColor, boolean tDropShadow,
//            @NotNull Operation<Integer> original,
//            Font mFont, @NotNull ItemStack mStack, int mX, int mY, @Nullable String mText) {
//      if (mStack.getMaxStackSize() < mStack.getCount()) { // !ItemComponentUtil.isDefault(mStack, DataComponents.MAX_STACK_SIZE)
//         tColor = 0xFFAABB;
//      }
//      return original.call(tInstance, tFont, tText, tX, tY, tColor, tDropShadow);
//      // TODO: Have the color of overstacked items customizeable (and disableable)
//   }
	
	@WrapOperation(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
	at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
	void test2(PoseStack instance, Operation<Void> original, Font mFont, @NotNull ItemStack mStack, int mX, int mY, @Nullable String mText) {
		if (mStack.getMaxStackSize() < mStack.getCount()) {
			Overstacked.renderOverstackedSymbol((GuiGraphics) (Object) this, mFont, mX, mY);
			// TODO: Add a tooltip that indicates that the item is overstacked
		}
		original.call(instance);
	}
}
