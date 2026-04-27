package flyinely.mcm.upstack.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import flyinely.mcm.upstack.Constants;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {
	
	// Render item decoration goals:
   // - fixing non-rendering of overstacked unstackable item counts
   // - red-tinting the rendering of overstacked unstackable item counts
	// TODO: Have the color of overstacked items customizeable (and disableable)
	
	@WrapOperation(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
	at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
	void test2(PoseStack instance, Operation<Void> original, Font mFont, @NotNull ItemStack mStack, int mX, int mY, String mText) {
		if (mStack.getMaxStackSize() < mStack.getCount()) {
			GuiGraphics graphics = (GuiGraphics) (Object) this;
			PoseStack poseStack = graphics.pose();
			poseStack.pushPose();
			poseStack.translate(0, 0, 190); // 10 layers below item counts
			graphics.drawString(mFont, "#", mX, mY, Constants.OVERSTACKED_COLOR);
			poseStack.popPose();
			// TODO: Add a tooltip that indicates that the item is overstacked
		}
		original.call(instance);
	}
}
