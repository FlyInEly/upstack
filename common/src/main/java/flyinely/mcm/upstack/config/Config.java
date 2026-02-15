package flyinely.mcm.upstack.config;

import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import org.jetbrains.annotations.Contract;

public class Config {
	
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	
	
	static {
		StackSize.init();
		
		SPEC = BUILDER.build();
	}
	
	public static class Cooldowns {
		
		private static final int MIN = 0;
		private static final int MAX = Integer.MAX_VALUE;
		
		public static final IntValue POTION_THROW_COOLDOWN;
		public static final IntValue EGG_THROW_COOLDOWN;
		public static final IntValue SNOWBALL_THROW_COOLDOWN;
		
		
		static {
			BUILDER.push("cooldowns");
			
			// TODO: Implement all cooldowns
			
			POTION_THROW_COOLDOWN = BUILDER
					.comment("Cooldown, in ticks, of throwable potions. Set 0 to disable.")
					.defineInRange("potion_throw_cooldown", 20, MIN, MAX); // default: parity w/ender pearls.
			EGG_THROW_COOLDOWN = BUILDER
					.comment("Cooldown, in ticks, of throwable eggs. Set 0 to disable.")
					.defineInRange("egg_throw_cooldown", 0, MIN, MAX); // default: unchanged.
			SNOWBALL_THROW_COOLDOWN = BUILDER
					.comment("Cooldown, in ticks, of snowballs. Set 0 to disable.")
					.defineInRange("snowball_throw_cooldown", 10, MIN, MAX); // default: half of ender pearls. not lower due to cheap projectile.
			
			BUILDER.pop();
		}
		
	}
	
	public static class StackSize {
		
		// A value of MIN_STACK_SIZE - 1 indicates that the item(s)' stack size should be left unmodified.
		private static final int MIN = ItemComponentUtil.ABSOLUTE_MIN_STACK_SIZE - 1;
		private static final int MAX = ItemComponentUtil.ABSOLUTE_MAX_STACK_SIZE;
		
		public static final IntValue ARMOR_STAND;
		public static final IntValue BANNERS;
		public static final IntValue BEDS;
		public static final IntValue BUCKET;
		public static final IntValue CAKE;
		public static final IntValue CHICKEN_EGGS;
		public static final IntValue ENDER_PEARL;
		public static final IntValue FILLED_BUCKETS;
		public static final IntValue HONEY_BOTTLE;
		public static final IntValue LINGERING_POTION;
		public static final IntValue POTION;
		public static final IntValue SNOWBALL;
		public static final IntValue SPLASH_POTION;
		public static final IntValue TOTEM_OF_UNDYING;
		public static final IntValue WRITTEN_BOOK;
		public static final IntValue BOATS;
		
		static {
			ARMOR_STAND = BUILDER.worldRestart()
					.comment("minecraft:armor_stand")
					.defineInRange("armor_stand", 64, MIN, MAX); // vanilla: 16. default: parity w/general items.
			BANNERS = BUILDER.worldRestart()
					.comment("#minecraft:banners")
					.defineInRange("banners", 64, MIN, MAX); // vanilla: 16. default: parity w/general items.
			FILLED_BUCKETS = BUILDER.worldRestart()
					.comment("#c:buckets")
					.defineInRange("filled_buckets", 16, MIN, MAX); // vanilla: 1. default: parity w/honey bottles.
			BUCKET = BUILDER.worldRestart()
					.comment("minecraft:bucket (overrides #c:buckets)")
					.defineInRange("bucket", 64, MIN, MAX); // vanilla: 16. default: parity w/empty bottles.
			BEDS = BUILDER.worldRestart()
					.comment("#minecraft:beds")
					.defineInRange("beds", 16, MIN, MAX); // vanilla: 1. default: not higher due to cheap explosive power.
			POTION = BUILDER.worldRestart()
					.comment("minecraft:potion")
					.defineInRange("potion", 16, MIN, MAX); // vanilla: 1. default: parity w/honey bottles.
			SPLASH_POTION = BUILDER.worldRestart()
					.comment("minecraft:splash_potion")
					.defineInRange("splash_potion", 16, MIN, MAX); // vanilla: 1. default: parity w/ender pearls.
			LINGERING_POTION = BUILDER.worldRestart()
					.comment("minecraft:lingering_potion")
					.defineInRange("lingering_potion", 16, MIN, MAX); // vanilla: 1. default: parity w/ender pearls.
			WRITTEN_BOOK = BUILDER.worldRestart()
					.comment("minecraft:written_book")
					.defineInRange("written_book", 64, MIN, MAX); // vanilla: 16. default: parity w/general items.
			ENDER_PEARL = BUILDER.worldRestart()
					.comment("minecraft:ender_pearl")
					.defineInRange("ender_pearl", 0, MIN, MAX); // default: unchanged.
			TOTEM_OF_UNDYING = BUILDER.worldRestart()
					.comment("minecraft:totem_of_undying")
					.defineInRange("totem_of_undying", 0, MIN, MAX); // default: unchanged.
			SNOWBALL = BUILDER.worldRestart()
					.comment("minecraft:snowball")
					.defineInRange("snowball", 64, MIN, MAX); // vanilla: 16. default: parity w/general items, for crafting QOL.
			HONEY_BOTTLE = BUILDER.worldRestart()
					.comment("minecraft:honey_bottle")
					.defineInRange("honey_bottle", 0, MIN, MAX); // default: unchanged.
			CHICKEN_EGGS = BUILDER.worldRestart() // TODO: chicken eggs tag
					.comment("#c:chicken_eggs")
					.defineInRange("chicken_eggs", 64, MIN, MAX); // vanilla: 16. default: parity w/general items, for crafting QOL.
			CAKE = BUILDER.worldRestart()
					.comment("minecraft:cake")
					.defineInRange("cake", 16, MIN, MAX); // vanilla: 1. default: parity w/default for milk buckets, for crafting QOL.
			BOATS = BUILDER.worldRestart()
					.comment("#minecraft:boats")
					.defineInRange("boats", 16, MIN, MAX); // vanilla: 1. default: parity w/vanilla for entity-spawning items.
		}
		
		/**
		 * Dummy method to trigger the static initializer.
		 */
		@Contract()
		static void init() {}
		
	}
	
	// LATER: Fully customizable stack sizes by item/tag id. Or data-driven.
	//   public static final ListValueSpec STACK_SIZES;
	//
	//   public static final List<String> DEFAULT_STACK_SIZES = List.of(
	//         "minecraft:bucket,64"
	//   );
	
}
