package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import static flyinely.mcm.upstack.config.Config.*;
import static flyinely.mcm.upstack.util.ResUtil.id;

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
		ItemComponentUtil.setMaxStackSize(MItemTags.C.BUCKETS, StackSize.C.BUCKETS.get());
		ItemComponentUtil.setMaxStackSize(Items.BUCKET, StackSize.BUCKET.get()); // apply after MItemTags.C.BUCKETS to override stack size for Items.BUCKET specifically
		
		// bottles
		ItemComponentUtil.setMaxStackSize(Items.HONEY_BOTTLE, StackSize.HONEY_BOTTLE.get());
		ItemComponentUtil.setMaxStackSize(Items.POTION, StackSize.POTION.get());
		ItemComponentUtil.setMaxStackSize(Items.SPLASH_POTION, StackSize.SPLASH_POTION.get());
		ItemComponentUtil.setMaxStackSize(Items.LINGERING_POTION, StackSize.LINGERING_POTION.get());

      // entity-spawning
		ItemComponentUtil.setMaxStackSize(ItemTags.BOATS, StackSize.BOATS.get());
      ItemComponentUtil.setMaxStackSize(Items.ENDER_PEARL, StackSize.ENDER_PEARL.get());
      ItemComponentUtil.setMaxStackSize(Items.SNOWBALL, StackSize.SNOWBALL.get());
      ItemComponentUtil.setMaxStackSize(Items.ARMOR_STAND, StackSize.ARMOR_STAND.get());
      ItemComponentUtil.setMaxStackSize(MItemTags.C.MINECARTS, StackSize.C.MINECARTS.get());
      ItemComponentUtil.setMaxStackSize(MItemTags.C.CHICKEN_EGGS, StackSize.C.CHICKEN_EGGS.get());

      // misc
      ItemComponentUtil.setMaxStackSize(Items.WRITTEN_BOOK, StackSize.WRITTEN_BOOK.get());

      // food
      ItemComponentUtil.setMaxStackSize(id("pastel:triple_meat_pot_pie"), 16); // 8
      ItemComponentUtil.setMaxStackSize(id("pastel:triple_meat_pot_stew"), 16); // 8

      // equipment
      ItemComponentUtil.setMaxStackSize(MItemTags.C.HORSE_ARMOR, StackSize.C.HORSE_ARMOR.get());
      ItemComponentUtil.setMaxStackSize(Items.SADDLE, StackSize.SADDLE.get());
      ItemComponentUtil.setMaxStackSize(Items.TOTEM_OF_UNDYING, StackSize.TOTEM_OF_UNDYING.get());
		
		// blocks
		ItemComponentUtil.setMaxStackSize(ItemTags.BEDS, StackSize.BEDS.get());
      ItemComponentUtil.setMaxStackSize(Items.CAKE, StackSize.CAKE.get());
      ItemComponentUtil.setMaxStackSize(ItemTags.BANNERS, StackSize.BANNERS.get());

      // mod - pastel
      ItemComponentUtil.setMaxStackSize(MItemTags.Pastel.BULBS,64); // 16
      ItemComponentUtil.setMaxStackSize(MItemTags.Pastel.FUSION_SHRINES,64); // 1
      ItemComponentUtil.setMaxStackSize(MItemTags.Pastel.ITEM_BOWLS,64); // 16 (enlightenment: 64)
      ItemComponentUtil.setMaxStackSize(MItemTags.Pastel.NETWORK_NODES,64); // 16
      ItemComponentUtil.setMaxStackSize(MItemTags.Pastel.PEDESTALS,64); // 1
      ItemComponentUtil.setMaxStackSize(MItemTags.Pastel.ROUNDELS,64); // 16 (preservation: 64)
      ItemComponentUtil.setMaxStackSize(MItemTags.Pastel.SHOOTING_STARS,16); // 1; spawns entity
      ItemComponentUtil.setMaxStackSize(MItemTags.Pastel.STRUCTURE_UPGRADES,64); // 16
      ItemComponentUtil.setMaxStackSize(id("pastel:aether_vestiges"),64); // 1 (test multiple to repair nectar lance in anvil!)
      ItemComponentUtil.setMaxStackSize(id("pastel:bag_of_holding"), 0); // (1)
      ItemComponentUtil.setMaxStackSize(id("pastel:cinderhearth"), 64); // 1
      ItemComponentUtil.setMaxStackSize(id("pastel:clotted_cream"), 64); // 64, but in #c:buckets (why??) so needs override AFTER buckets is set
      ItemComponentUtil.setMaxStackSize(id("pastel:color_picker"), 64); // 8
      ItemComponentUtil.setMaxStackSize(id("pastel:crystal_apothecary"), 64); // 8
      ItemComponentUtil.setMaxStackSize(id("pastel:crystallarieum"), 64); // 1
      ItemComponentUtil.setMaxStackSize(id("pastel:downstone_fragments"), 64); // 16
      ItemComponentUtil.setMaxStackSize(id("pastel:dragonbone_broth"), 16); // 8. grants magic annulation
      ItemComponentUtil.setMaxStackSize(id("pastel:enchanter"), 64); // 1
      ItemComponentUtil.setMaxStackSize(id("pastel:ender_splice"), 0); // (16)
      ItemComponentUtil.setMaxStackSize(id("pastel:jadeite_lotus_flower"), 64); // 16
      ItemComponentUtil.setMaxStackSize(id("pastel:lucky_roll"), 0); // (16)
      ItemComponentUtil.setMaxStackSize(id("pastel:moonstone_core"), 64); // 16
      ItemComponentUtil.setMaxStackSize(id("pastel:palteria_gem"), 64); // 16
      ItemComponentUtil.setMaxStackSize(id("pastel:potion_workshop"), 64); // 1
      ItemComponentUtil.setMaxStackSize(id("pastel:resonance_shard"), 64); // 16
      ItemComponentUtil.setMaxStackSize(id("pastel:spirit_instiller"), 64); // 1
      ItemComponentUtil.setMaxStackSize(id("pastel:star_fragment"), 64); // 16
      ItemComponentUtil.setMaxStackSize(id("pastel:stardust_block"), 64); // 1
      ItemComponentUtil.setMaxStackSize(id("pastel:stratine_gem"), 64); // 16



   }

}
