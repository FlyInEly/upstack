package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.Constants;
import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import static flyinely.mcm.upstack.config.Config.*;

// Server side
public class StackSizes {

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

      Constants.LOG.info("{} empty bucket", StackSize.EMPTY_BUCKET.get());

      // buckets
      ItemComponentUtil.setMaxStackSize(Items.BUCKET, StackSize.EMPTY_BUCKET.get()); // vanilla: 16
      ItemComponentUtil.setMaxStackSize(Items.WATER_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.LAVA_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.PUFFERFISH_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.SALMON_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.COD_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.TROPICAL_FISH_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.AXOLOTL_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.TADPOLE_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.MILK_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.POWDER_SNOW_BUCKET, StackSize.FILLED_BUCKETS.get()); // vanilla: 1

      // bottles
      ItemComponentUtil.setMaxStackSize(Items.HONEY_BOTTLE, 16); // only matters if customized
      ItemComponentUtil.setMaxStackSize(Items.POTION, 16); // vanilla: 1
      ItemComponentUtil.setMaxStackSize(Items.SPLASH_POTION, 4); // vanilla: 1. TODO splash, lingering: add cooldown
      ItemComponentUtil.setMaxStackSize(Items.LINGERING_POTION, 4); // vanilla: 1

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
