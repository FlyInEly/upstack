### PlayerMixin
Test both overstack splitting and understack combining.

### HorseInventoryMenuMixin
Configure the max stack sizes of minecraft:saddle and #c:horse_armor to 16.
Do not modify the max stack size of minecraft:cobblestone (64).
Use Survival mode.
1. An untamed horse should reject all saddles when a full stack is used on it in-world.
2. A tamed horse should accept 1 saddle when a full stack is used on it in-world.
3. A tamed horse's armor slot should:
   1. Accept 1 horse armor when given a full stack.
   2. Reject all saddles and cobblestone when given a full stack of each.
4. A tamed horse's saddle slot should:
   1. Accept 1 saddle when given a full stack.
   2. Reject all saddles and cobblestone when given a full stack of each.
5. A tamed, chest-equipped donkey's inventory slots should accept a full stack of horse armor, saddles, and cobblestone.

### SolidBucketItemMixin
Configure the max stack size of #c:buckets max stack size to 16. 
Use minecraft:powder_snow_bucket. Use Survival mode unless otherwise specified.
1. A full stack should consistently stack to 16 when moved around, split, and recombined in the inventory. 
2. Placing a full stack should:
   1. Non-full inventory: Add an empty bucket to the inventory.
   2. Full inventory: Drop an empty bucket on the ground.
3. Placing a full stack twice should decrement it twice.
4. Placing a 1-stack should replace it with 1 empty bucket.
5. Picking up a 1-stack while having a non-full stack should increment it.
6. Bucketing a powder snow block while having a stack should:
   1. Non-full stack, non-full inventory: Increment the snow stack and decrement the bucket stack. 
   2. Full stack, non-full inventory: Add a 1-stack to the inventory.
   3. Full stack, full inventory: Drop a 1-stack on the ground.
7. Bucketing a powder snow block twice should decrement the bucket stack twice.
8. Testing 6-1 through 6-3 in Creative mode should neither increment nor decrement any stacks.

### LoomMenuMixin
Configure the max stack sizes of #c:banner_patterns and #minecraft:banners to 64.
Do not modify the max stack size of minecraft:white_dye (64).
Use Survival mode.
1. The loom's banner pattern slot should:
   1. Accept 1 "thing" banner pattern when given a full stack.
   2. Reject all red banners and white dye when given a full stack of each.
2. The loom's banner slot should:
   1. Accept all red banners when given a full stack.
   2. Reject all white dye and "thing" banner patterns when given a full stack of each.
3. Apply 1 "thing" banner pattern to 64 red banners using 64 white dye in the loom, which should:
   1. Consume 1 banner and 1 dye.
   2. Produce 1 appropriately patterned banner.

### AnvilMenuMixin, BedrockAnvilScreenHandlerMixin
Configure the max stack size of minecraft:enchanted_book to 64.
Do not modify the max stack size of minecraft:leather (64).
Test separately in minecraft:anvil and pastel:bedrock_anvil (NeoForge)
Use Survival mode.
1. Combining a leather helmet at near 50% durability with a full leather stack should fully repair
the helmet and consume 2 leather.
2. Combining two leather helmets at near 50% durability should fully repair the first helmet and consume the second.
3. Combining a leather helmet at near 0% durability with a full leather stack should fully repair
the helmet and consume 4 leather.
4. Combining a leather helmet with a full stack of Protection IV enchanted books should apply the enchantment
and consume 1 book.
5. Combining the leather helmet from (3) with a 1-stack of Aqua Affinity enchanted books should apply the
enchantment and consume the book.