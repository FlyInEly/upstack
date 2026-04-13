package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.annotation.SoftSided;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import static flyinely.mcm.upstack.Config.StackSize;
import static flyinely.mcm.upstack.util.ItemComponentUtil.setMaxStackSize;
import static flyinely.mcm.upstack.util.ResUtil.id;

/**
 * Set max stack sizes according to the mod's config.
 * @see #apply()
 */
@SoftSided.Server
public final class MStackSizes {

   /**
	 * Set max stack sizes by tag, then by item, according to the mod's config.
	 * <p>
	 * The order ensures that an item's stack size takes priority over that of its tag(s). It does not guarantee any particular
	 * priority for an item with two tags of different stack sizes.
    */
   public static void apply() {
      tags();
		items();
   }
	
	private static void tags() {
		// minecraft
		setMaxStackSize(ItemTags.BANNERS, StackSize.Common.BANNERS.get());
		setMaxStackSize(ItemTags.BEDS, StackSize.Common.BEDS.get());
		setMaxStackSize(ItemTags.BOATS, StackSize.Common.BOATS.get());
		
		// c
		setMaxStackSize(ModItemTags.C.BANNER_PATTERNS, StackSize.Common.BANNER_PATTERNS.get());
		setMaxStackSize(ModItemTags.C.BUCKETS, StackSize.Common.BUCKETS.get());
		setMaxStackSize(ModItemTags.C.CHICKEN_EGGS, StackSize.Common.CHICKEN_EGGS.get());
		setMaxStackSize(ModItemTags.C.HORSE_ARMOR, StackSize.Common.HORSE_ARMOR.get());
		setMaxStackSize(ModItemTags.C.MILK_BOTTLES, StackSize.Common.MILK_BOTTLES.get());
		setMaxStackSize(ModItemTags.C.MINECARTS, StackSize.Common.MINECARTS.get());
		setMaxStackSize(ModItemTags.C.MUSIC_DISCS, StackSize.Common.MUSIC_DISCS.get());
		setMaxStackSize(ModItemTags.C.SOUPS, StackSize.Common.SOUPS.get());
		
		// pastel
		setMaxStackSize(ModItemTags.Pastel.BULBS, StackSize.Pastel.BULBS.get());
		setMaxStackSize(ModItemTags.Pastel.FUSION_SHRINES, StackSize.Pastel.FUSION_SHRINES.get());
		setMaxStackSize(ModItemTags.Pastel.ITEM_BOWLS, StackSize.Pastel.ITEM_BOWLS.get());
		setMaxStackSize(ModItemTags.Pastel.NETWORK_NODES, StackSize.Pastel.NETWORK_NODES.get());
		setMaxStackSize(ModItemTags.Pastel.PEDESTALS, StackSize.Pastel.PEDESTALS.get());
		setMaxStackSize(ModItemTags.Pastel.ROUNDELS, StackSize.Pastel.ROUNDELS.get());
		setMaxStackSize(ModItemTags.Pastel.SHOOTING_STARS, StackSize.Pastel.SHOOTING_STARS.get());
		setMaxStackSize(ModItemTags.Pastel.STRUCTURE_UPGRADES, StackSize.Pastel.STRUCTURE_UPGRADES.get());
		
		// farmersdelight
		setMaxStackSize(ModItemTags.Farmersdelight.FEASTS, StackSize.Farmersdelight.FEASTS.get());
	}
	
	private static void items() {
		// minecraft
		setMaxStackSize(Items.ARMOR_STAND, StackSize.Common.ARMOR_STAND.get());
		setMaxStackSize(Items.BUCKET, StackSize.Common.BUCKET.get());
		setMaxStackSize(Items.CAKE, StackSize.Common.CAKE.get());
		setMaxStackSize(Items.ENCHANTED_BOOK, StackSize.Common.ENCHANTED_BOOK.get());
		setMaxStackSize(Items.ENDER_PEARL, StackSize.Common.ENDER_PEARL.get());
		setMaxStackSize(Items.HONEY_BOTTLE, StackSize.Common.HONEY_BOTTLE.get());
		setMaxStackSize(Items.LINGERING_POTION, StackSize.Common.LINGERING_POTION.get());
		setMaxStackSize(Items.POTION, StackSize.Common.POTION.get());
		setMaxStackSize(Items.SADDLE, StackSize.Common.SADDLE.get());
		setMaxStackSize(Items.SNOWBALL, StackSize.Common.SNOWBALL.get());
		setMaxStackSize(Items.SPLASH_POTION, StackSize.Common.SPLASH_POTION.get());
		setMaxStackSize(Items.TOTEM_OF_UNDYING, StackSize.Common.TOTEM_OF_UNDYING.get());
		setMaxStackSize(Items.WRITABLE_BOOK, StackSize.Common.WRITABLE_BOOK.get());
		setMaxStackSize(Items.WRITTEN_BOOK, StackSize.Common.WRITTEN_BOOK.get());
		
		// pastel
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
		
		// farmersdelight
		setMaxStackSize(id("farmersdelight:cooking_pot"), StackSize.Farmersdelight.COOKING_POT.get());
	}
	
}
