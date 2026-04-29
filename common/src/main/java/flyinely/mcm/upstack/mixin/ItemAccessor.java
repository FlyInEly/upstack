package flyinely.mcm.upstack.mixin;

import flyinely.mcm.upstack.util.ComponentUtil;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accessor for setting the components field of an {@link Item}.
 *
 * @see ComponentUtil
 */
@Mixin(Item.class)
public interface ItemAccessor {
   @Accessor
   @Mutable
   void setComponents(DataComponentMap components);
}