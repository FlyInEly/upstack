package flyinely.mcm.upstack.mixin.registry;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accessor for setting the components field of an {@link Item}.
 *
 * @see flyinely.mcm.upstack.util.ItemComponentUtil
 */
@Mixin(Item.class)
public interface ItemAccessor {
   @Accessor
   @Mutable
   void setComponents(DataComponentMap components);
}