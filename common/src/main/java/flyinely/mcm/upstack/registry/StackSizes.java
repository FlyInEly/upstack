package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.platform.ItemRegistries;
import flyinely.mcm.upstack.util.ItemComponentUtil;
import flyinely.mcm.upstack.util.ResUtil;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;

import static flyinely.mcm.upstack.config.Config.*;

// Server side
public class StackSizes {
	
	/*
		Notes on approach for item component modification.
		
		Decided not to use NeoForge's ModifyDefaultComponentsEvent because it fires before item tag data is loaded,
		and so we can't modify the stack sizes of all items belonging to a given tag. Moreover, using our own system
		allows us to update stack sizes on config reload, though we will need to minimize breaking side effects of both
		increasing and decreasing stack size settings in an existing world. Finally, Fabric and NeoForge both support our
		custom system, which simply works with Vanilla's tools.
	 */
	// TODO: Revert to previous approach for stack size modification.
	
   public static void init() {
		// buckets
      // bucket is in #c:buckets; register it first for priority
      ItemRegistries.STACK_SIZE.register(Items.BUCKET, StackSize.EMPTY_BUCKET::getAsInt);
      ItemRegistries.STACK_SIZE.register(ResUtil.itemTag("c:buckets"), StackSize.FILLED_BUCKETS::getAsInt); // TODO: By tag
		
		// bottles
		ItemRegistries.STACK_SIZE.register(Items.HONEY_BOTTLE, 16); // only matters if customized
		ItemRegistries.STACK_SIZE.register(Items.POTION, 16); // vanilla: 1
		ItemRegistries.STACK_SIZE.register(Items.SPLASH_POTION, 4); // vanilla: 1. TODO splash, lingering: add cooldown
		ItemRegistries.STACK_SIZE.register(Items.LINGERING_POTION, 4); // vanilla: 1
	}

   /**
    * Set max stack sizes for the mod.
    */
   public static void apply() {// TODO: get each from owo config
      /*
       * 1.21.1 works out of the box for:
       * - Fluid buckets, e.g. water, tadpole
       * - Milk buckets
       * - Potions
       *
       * And needs mixins for:
       * - Solid buckets, e.g. powder snow
       */

      // Only need to use the ItemPredicateRegistry for rules that aren't per-item.

//      Constants.LOG.info("{} empty bucket", StackSize.EMPTY_BUCKET.get());


      

      // placed foods
      ItemComponentUtil.setMaxStackSize(Items.CAKE, 16); // vanilla: 1. do by tag?

      // entity-spawning
      ItemComponentUtil.setMaxStackSize(Items.ENDER_PEARL, 16); // only matters if customized
      ItemComponentUtil.setMaxStackSize(Items.SNOWBALL, 64); // vanilla: 16
      ItemComponentUtil.setMaxStackSize(Items.ARMOR_STAND, 64); // vanilla: 16

      // misc
      ItemComponentUtil.setMaxStackSize(Items.WRITTEN_BOOK, 64); // vanilla: 16
      ItemComponentUtil.setMaxStackSize(Items.TOTEM_OF_UNDYING, 1); // only matters if customized

      // TESTING:
      ItemComponentUtil.setMaxStackSize(ItemTags.BANNERS, 64);

      // TODO: banner tag, boats tag
   }

}
