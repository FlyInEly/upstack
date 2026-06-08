package flyinely.mcm.upstack.config;

import flyinely.mcm.upstack.annotation.CContract;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

import static flyinely.mcm.upstack.annotation.CContract.*;
import static net.neoforged.neoforge.common.ModConfigSpec.*;

// TODO: Hook up to throw cooldowns (currently just a dummy testing class)
// TODO: Add datafixer to migrate from old (common) config paths to the new (server) config paths
// TODO: Translation key migration
@ApiStatus.Internal
public class ServerConfig {
	
	private static final Builder BUILDER = new Builder();
	
	public static final ModConfigSpec SPEC;
	
	static {
		CooldownsTest.init();
		SPEC = BUILDER.build();
	}
	
	@StaticRegistry
	public static class CooldownsTest {
		
		private static final int MIN = 0;
		private static final int MAX = Integer.MAX_VALUE;
		
		public static final IntValue THROWABLE_POTION;
		public static final IntValue EGG;
		public static final IntValue SNOWBALL;
		
		static {
			BUILDER.comment("""
							The usage cooldown (in ticks) of specific items. The default cooldowns are intended to help balance the default stack size changes.
							
							Enter '0' to disable the cooldown.""")
					.push("cooldowns");
			
			THROWABLE_POTION = BUILDER
					.comment("Potion-throwing cooldown (shared between splash and lingering potions)")
					.defineInRange("throwable_potion", 10, MIN, MAX); // default: half of ender pearls. not lower due to combat balance implications, but not higher to cause less friction.
			EGG = BUILDER
					.comment("Egg-throwing cooldown")
					.defineInRange("egg", 0, MIN, MAX); // default: unchanged.
			SNOWBALL = BUILDER
					.comment("Snowball-throwing cooldown")
					.defineInRange("snowball", 5, MIN, MAX); // default: quarter of ender pearls. not lower due to cheap projectile, esp. on blazes.
			
			BUILDER.pop(); // cooldowns
		}
		
		@Contract
		@CContract.StaticInit
		@SuppressWarnings("EmptyMethod")
		private static void init() {}
		
	}
}
