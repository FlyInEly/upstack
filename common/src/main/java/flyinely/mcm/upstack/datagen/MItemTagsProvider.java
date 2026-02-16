package flyinely.mcm.upstack.datagen;

import flyinely.mcm.upstack.registry.MItemTags;
import flyinely.mcm.upstack.util.ResUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MItemTagsProvider extends ItemTagsProvider {
	public MItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
		super(output, lookupProvider, blockTags);
	}
	
	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		tag(MItemTags.C.MINECARTS).add(
				Items.MINECART,
				Items.CHEST_MINECART,
				Items.FURNACE_MINECART,
				Items.HOPPER_MINECART,
				Items.TNT_MINECART,
				Items.COMMAND_BLOCK_MINECART);
		
		tag(MItemTags.C.CHICKEN_EGGS)
				.add(Items.EGG)
				.addOptional(ResUtil.id("minecraft:blue_egg")) // vanillabackport registers these under the minecraft namespace
				.addOptional(ResUtil.id("minecraft:brown_egg"));
	}
	// TODO: Entire common tags provider
}
