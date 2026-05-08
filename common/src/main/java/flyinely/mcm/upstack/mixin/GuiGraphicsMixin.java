package flyinely.mcm.upstack.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import flyinely.mcm.upstack.config.Config;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

   // Does this mixin has the side effect of resolving issue #2?
   // It partially resolves the issue, but only for items in the inventory, not in containers.
   // If we render the decor on every item, will it update containers? No.
   // What if we also add the tooltip handler? No, containers still lag behind.

   // Only renders if config is enabled.
   @WrapOperation(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
         at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
   void overstackedDecor(PoseStack instance, Operation<Void> original, Font mFont, @NotNull ItemStack mStack, int mX, int mY, String mText) {
      if (Config.Display.OVERSTACKED_SYMBOL.get() && mStack.getCount() > mStack.getItem().getDefaultMaxStackSize()) {
         GuiGraphics graphics = (GuiGraphics) (Object) this;
         PoseStack poseStack = graphics.pose();
         poseStack.pushPose();
         poseStack.translate(0, 0, 190); // 10 layers below item counts
         graphics.drawString(mFont, "#", mX, mY, Config.Display.OVERSTACKED_COLOR.get());
         poseStack.popPose();
      }
      original.call(instance);
   }

}