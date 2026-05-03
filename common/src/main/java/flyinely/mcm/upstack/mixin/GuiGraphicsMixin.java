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

import static flyinely.mcm.upstack.util.ComponentUtil.MaxStackSize;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {
	
	// TODO: fix non-rendering of the count and overstackedDecor on overstacked unstackable items
	//		inside containers.
	
	@WrapOperation(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
	at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
	void overstackedDecor(PoseStack instance, Operation<Void> original, Font mFont, @NotNull ItemStack mStack, int mX, int mY, String mText) {
		if (mStack.getCount() > MaxStackSize.getDefault(mStack)) {
			GuiGraphics graphics = (GuiGraphics) (Object) this;
			PoseStack poseStack = graphics.pose();
			poseStack.pushPose();
			poseStack.translate(0, 0, 190); // 10 layers below item counts
			graphics.drawString(mFont, "#", mX, mY, Constants.OVERSTACKED_COLOR);
			poseStack.popPose();
		}
		original.call(instance);
	}

}
