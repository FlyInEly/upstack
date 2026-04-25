package flyinely.mcm.upstack.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class Overstacked {
	
	// Temporary import from Sophisticated Core
	public static void renderOverstackedSymbol(GuiGraphics guiGraphics, Font font, int x, int y) {
		PoseStack poseStack = guiGraphics.pose();
		poseStack.pushPose();
		poseStack.translate(0, 0, 190); // 10 layers below item counts
		guiGraphics.drawString(font, "#", x, y, 0xff5454);
		poseStack.popPose();
	}
}
