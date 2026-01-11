package flyinely.mcm.upstack.registry;

import flyinely.mcm.upstack.util.ItemComponentUtil;
import net.minecraft.world.item.Items;

public class UpstackStackSizes {

   // Only need to use the ItemPredicateRegistry for rules that aren't per-item.

   public static void register() {
      ItemComponentUtil.setMaxStackSize(Items.WATER_BUCKET, 16); // TODO: get from config

   }

}
