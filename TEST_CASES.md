### HorseInventoryMenuMixin
Set minecraft:saddle max stack size above 1 in the config.
- Horse armor slot accepts horse armor but not saddles, and vice versa (GUI).
- Horse inventory slots accept any item of any stack size.
- Tamed horse accepts only 1 saddle when given a full stack (GUI, in-world use).
- Untamed horse rejects saddles when given a full stack (in-world use).
- Mules and donkeys behave likewise.

### SolidBucketItemMixin
Set #c:buckets max stack size above 1 in the config. Test all cases using minecraft:powder_snow_bucket.
- Stacks consistently reflect the configured max stack size when moved around, split, and recombined in the inventory.
- Picking up the item increments the stack.
- Placing the item decrements the stack.
- Placing the item with a non-full inventory adds an empty bucket to the inventory.
- Placing the item with a full inventory drops an empty bucket on the ground.
- Placing the last item in the stack leaves an empty bucket in its place. 
- Bucketing the block form increments the stack.
- In Creative Mode, neither placing the item nor bucketing the block affect the stack size.
