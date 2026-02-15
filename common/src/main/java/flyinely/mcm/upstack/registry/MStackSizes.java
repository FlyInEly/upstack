package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import static flyinely.mcm.upstack.config.Config.*;

// Server side
public class MStackSizes {
	
	/*
		Notes on approach for item component modification.
		
		Decided not to use NeoForge's ModifyDefaultComponentsEvent because it fires before item tag data is loaded,
		and so we can't modify the stack sizes of all items belonging to a given tag. Moreover, using our own system
		allows us to update stack sizes on config reload, though we will need to minimize breaking side effects of both
		increasing and decreasing stack size settings in an existing world. Finally, Fabric and NeoForge both support our
		custom system, which simply works with Vanilla's tools.
	 */

   /**
    * Set max stack sizes for the mod.
    */
   public static void apply() {
      /*
       * 1.21.1 works out of the box for:
       * - Fluid buckets, e.g. water, tadpole
       * - Milk buckets
       * - Potions
       * - Boats
       * - Honey bottles
       * - Blocks
       * - Armor stands
       * - Throwables
       * - Totems
       *
       * And needs mixins for:
       * - Solid buckets, e.g. powder snow
       */
		
		// TODO: Also archive some stuff that should not compile to final jar export because its currently unused, but keep it in
		//		some dedicated folder, rather than just a commit you'll forget about
		
		// buckets
		// minecraft:bucket is in #c:buckets; apply last to override
		ItemComponentUtil.setMaxStackSize(MItemTags.C.BUCKETS, StackSize.FILLED_BUCKETS.get());
		ItemComponentUtil.setMaxStackSize(Items.BUCKET, StackSize.BUCKET.get());
		
		// bottles
		ItemComponentUtil.setMaxStackSize(Items.HONEY_BOTTLE, StackSize.HONEY_BOTTLE.get());
		ItemComponentUtil.setMaxStackSize(Items.POTION, StackSize.POTION.get());
		ItemComponentUtil.setMaxStackSize(Items.SPLASH_POTION, StackSize.SPLASH_POTION.get());
		ItemComponentUtil.setMaxStackSize(Items.LINGERING_POTION, StackSize.LINGERING_POTION.get());

      // placed foods
      ItemComponentUtil.setMaxStackSize(Items.CAKE, StackSize.CAKE.get()); // LATER: Do by tag?

      // entity-spawning
		ItemComponentUtil.setMaxStackSize(ItemTags.BOATS, StackSize.BOATS.get());
      ItemComponentUtil.setMaxStackSize(Items.ENDER_PEARL, StackSize.ENDER_PEARL.get());
      ItemComponentUtil.setMaxStackSize(Items.SNOWBALL, StackSize.SNOWBALL.get());
      ItemComponentUtil.setMaxStackSize(Items.ARMOR_STAND, StackSize.ARMOR_STAND.get());

      // misc
      ItemComponentUtil.setMaxStackSize(Items.WRITTEN_BOOK, StackSize.WRITTEN_BOOK.get());
      ItemComponentUtil.setMaxStackSize(Items.TOTEM_OF_UNDYING, StackSize.TOTEM_OF_UNDYING.get());
		
		// blocks
		ItemComponentUtil.setMaxStackSize(ItemTags.BEDS, StackSize.BEDS.get());

      // TESTING:
      ItemComponentUtil.setMaxStackSize(ItemTags.BANNERS, StackSize.BANNERS.get());
		ItemComponentUtil.setMaxStackSize(MItemTags.C.CHICKEN_EGGS, StackSize.CHICKEN_EGGS.get());

      // LATER: Minecarts via tag
   }

}
