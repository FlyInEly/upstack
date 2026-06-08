package flyinely.mcm.upstack.config;

import flyinely.mcm.upstack.annotation.CContract;
import flyinely.mcm.upstack.annotation.CContract.StaticRegistry;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

@ApiStatus.Internal
@StaticRegistry
public class ClientConfig {
	
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	
	static {
		// NOTE: The order of config values in the GUI matches the order they were initialized in.
		
		Display.init();
		
		SPEC = BUILDER.build();
	}
	
	@StaticRegistry
	public static class Display {

		public static final BooleanValue OVERSTACKED_SYMBOL;
		public static final BooleanValue OVERSTACKED_TOOLTIP;

		public static final IntValue OVERSTACKED_COLOR; // LATER: Use a ConfigValue which safely accepts direct hex.

		static {
			BUILDER.comment("Configure indicators for overstacked items: stacks whose count exceeds the max stack size.").push("display");

			OVERSTACKED_SYMBOL = BUILDER
					.comment("Overlay a \"#\" symbol on overstacked items: stacks whose count exceeds the max stack size.")
					.define("overstacked_symbol", true);

			OVERSTACKED_TOOLTIP = BUILDER
					.comment("Add a descriptive tooltip to overstacked items.")
					.define("overstacked_tooltip", true);

			OVERSTACKED_COLOR = BUILDER
					.comment("The color (24-bit RGB) of the overstacked symbol and tooltip on overstacked items.")
					.defineInRange("overstacked_color", 0xff5454, 0x000000, 0xFFFFFF);

			BUILDER.pop(); // display
		}

		@Contract
		@CContract.StaticInit
		@SuppressWarnings("EmptyMethod")
		private static void init() {}
	}
}
