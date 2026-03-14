package flyinely.mcm.upstack.registry;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import static flyinely.mcm.upstack.config.Config.*;
import static flyinely.mcm.upstack.util.ItemComponentUtil.setMaxStackSize;
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
       *
       * Some principles for default stack sizes include:
       * - If there's no balance issue caused by allowing a 64-stack size, allow it. For instance,
       *   compasses and clocks have no utility benefit from being stacked, but their stackability
       *   allows them to fit more in bundles, be distributed to other players, be sold to villagers,
       *   be crafted into other things.
       * - Use 1, 16, 64 -- the precedent set by vanilla.
       */

      // TODO: Also archive some stuff that should not compile to final jar export because its currently unused, but keep it in
      //		some dedicated folder, rather than just a commit you'll forget about

      // TAGS
      setMaxStackSize(ItemTags.BANNERS, StackSize.BANNERS.get());
      setMaxStackSize(ItemTags.BEDS, StackSize.BEDS.get());
      setMaxStackSize(ItemTags.BOATS, StackSize.BOATS.get());
      setMaxStackSize(MItemTags.C.BUCKETS, StackSize.C.BUCKETS.get());
      setMaxStackSize(MItemTags.C.CHICKEN_EGGS, StackSize.C.CHICKEN_EGGS.get());
      setMaxStackSize(MItemTags.C.HORSE_ARMOR, StackSize.C.HORSE_ARMOR.get());
      setMaxStackSize(MItemTags.C.MINECARTS, StackSize.C.MINECARTS.get());
      setMaxStackSize(MItemTags.Pastel.BULBS, StackSize.Pastel.BULBS.get());
      setMaxStackSize(MItemTags.Pastel.FUSION_SHRINES, StackSize.Pastel.FUSION_SHRINES.get());
      setMaxStackSize(MItemTags.Pastel.ITEM_BOWLS, StackSize.Pastel.ITEM_BOWLS.get());
      setMaxStackSize(MItemTags.Pastel.NETWORK_NODES, StackSize.Pastel.NETWORK_NODES.get());
      setMaxStackSize(MItemTags.Pastel.PEDESTALS, StackSize.Pastel.PEDESTALS.get());
      setMaxStackSize(MItemTags.Pastel.ROUNDELS, StackSize.Pastel.ROUNDELS.get());
      setMaxStackSize(MItemTags.Pastel.SHOOTING_STARS, StackSize.Pastel.SHOOTING_STARS.get());
      setMaxStackSize(MItemTags.Pastel.STRUCTURE_UPGRADES, StackSize.Pastel.STRUCTURE_UPGRADES.get());

      // ITEMS - Must be listed after tags to override for individual items
      // TODO: test aether vestiges using multiple to repair nectar lance in anvil!
      setMaxStackSize(Items.ARMOR_STAND, StackSize.ARMOR_STAND.get());
      setMaxStackSize(Items.BUCKET, StackSize.BUCKET.get());
      setMaxStackSize(Items.CAKE, StackSize.CAKE.get());
      setMaxStackSize(Items.ENDER_PEARL, StackSize.ENDER_PEARL.get());
      setMaxStackSize(Items.HONEY_BOTTLE, StackSize.HONEY_BOTTLE.get());
      setMaxStackSize(Items.LINGERING_POTION, StackSize.LINGERING_POTION.get());
      setMaxStackSize(Items.POTION, StackSize.POTION.get());
      setMaxStackSize(Items.SADDLE, StackSize.SADDLE.get());
      setMaxStackSize(Items.SNOWBALL, StackSize.SNOWBALL.get());
      setMaxStackSize(Items.SPLASH_POTION, StackSize.SPLASH_POTION.get());
      setMaxStackSize(Items.TOTEM_OF_UNDYING, StackSize.TOTEM_OF_UNDYING.get());
      setMaxStackSize(Items.WRITTEN_BOOK, StackSize.WRITTEN_BOOK.get());
      setMaxStackSize(id("pastel:aether_vestiges"), StackSize.Pastel.AETHER_VESTIGES.get());
      setMaxStackSize(id("pastel:bag_of_holding"), StackSize.Pastel.BAG_OF_HOLDING.get());
      setMaxStackSize(id("pastel:cinderhearth"), StackSize.Pastel.CINDERHEARTH.get());
      setMaxStackSize(id("pastel:clotted_cream"), StackSize.Pastel.CLOTTED_CREAM.get());
      setMaxStackSize(id("pastel:color_picker"), StackSize.Pastel.COLOR_PICKER.get());
      setMaxStackSize(id("pastel:crystal_apothecary"), StackSize.Pastel.CRYSTAL_APOTHECARY.get());
      setMaxStackSize(id("pastel:crystallarieum"), StackSize.Pastel.CRYSTALLARIEUM.get());
      setMaxStackSize(id("pastel:downstone_fragments"), StackSize.Pastel.DOWNSTONE_FRAGMENTS.get());
      setMaxStackSize(id("pastel:dragonbone_broth"), StackSize.Pastel.DRAGONBONE_BROTH.get());
      setMaxStackSize(id("pastel:enchanter"), StackSize.Pastel.ENCHANTER.get());
      setMaxStackSize(id("pastel:ender_splice"), StackSize.Pastel.ENDER_SPLICE.get());
      setMaxStackSize(id("pastel:jadeite_lotus_flower"), StackSize.Pastel.JADEITE_LOTUS_FLOWER.get());
      setMaxStackSize(id("pastel:moonstone_core"), StackSize.Pastel.MOONSTONE_CORE.get());
      setMaxStackSize(id("pastel:palteria_gem"), StackSize.Pastel.PALTERIA_GEM.get());
      setMaxStackSize(id("pastel:potion_workshop"), StackSize.Pastel.POTION_WORKSHOP.get());
      setMaxStackSize(id("pastel:resonance_shard"), StackSize.Pastel.RESONANCE_SHARD.get());
      setMaxStackSize(id("pastel:spirit_instiller"), StackSize.Pastel.SPIRIT_INSTILLER.get());
      setMaxStackSize(id("pastel:star_fragment"), StackSize.Pastel.STAR_FRAGMENT.get());
      setMaxStackSize(id("pastel:stardust_block"), StackSize.Pastel.STARDUST_BLOCK.get());
      setMaxStackSize(id("pastel:stratine_gem"), StackSize.Pastel.STRATINE_GEM.get());
      setMaxStackSize(id("pastel:triple_meat_pot_pie"), StackSize.Pastel.TRIPLE_MEAT_POT_PIE.get());
      setMaxStackSize(id("pastel:triple_meat_pot_stew"), StackSize.Pastel.TRIPLE_MEAT_POT_STEW.get());

   }

}
