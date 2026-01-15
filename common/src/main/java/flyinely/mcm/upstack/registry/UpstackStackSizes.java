package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class UpstackStackSizes {

   // Only need to use the ItemPredicateRegistry for rules that aren't per-item.

   /*
    * 1.21.1 works out of the box for:
    * - Fluid buckets, e.g. water, tadpole
    * - Milk buckets
    * - Potions
    *
    * And needs mixins for:
    * - Solid buckets, e.g. powder snow
    */
   public static void register() {// TODO: get each from config
      // buckets
      ItemComponentUtil.setMaxStackSize(Items.WATER_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.LAVA_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.PUFFERFISH_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.SALMON_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.COD_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.TROPICAL_FISH_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.AXOLOTL_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.TADPOLE_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.MILK_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.POWDER_SNOW_BUCKET, 16);

      // potions
      ItemComponentUtil.setMaxStackSize(Items.POTION, 16);
      ItemComponentUtil.setMaxStackSize(Items.SPLASH_POTION, 4); // TODO: add cooldown
      ItemComponentUtil.setMaxStackSize(Items.LINGERING_POTION, 4);


   }

}
