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
    *
    * And needs mixins for:
    * - Solid buckets, e.g. powder snow
    */
   public static void register() {
      ItemComponentUtil.setMaxStackSize(Items.WATER_BUCKET, 16); // TODO: get each from config
      ItemComponentUtil.setMaxStackSize(Items.LAVA_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.PUFFERFISH_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.SALMON_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.COD_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.TROPICAL_FISH_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.AXOLOTL_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.TADPOLE_BUCKET, 16);
      ItemComponentUtil.setMaxStackSize(Items.MILK_BUCKET, 16);

      // Needs fix
      ItemComponentUtil.setMaxStackSize(Items.POWDER_SNOW_BUCKET, 16);
   }

}
