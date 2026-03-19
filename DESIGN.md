### Why no datagen?
- Mod is small
- Only static tags would need generating
- Not worth the author's time to figure out multi-loader datagen for this project

### Why include blue_egg and brown_egg in #c:chicken_eggs, even though they aren't in Vanilla 1.21.1?
- For compat with Vanilla Backport 1.1.5.4.2+, which backports from 1.21.5 these items in the Minecraft namespace
- Additionally, netherite_horse_armor is included in #c:horse_armor to cover any future backport of Vanilla 1.21.11

### Notes on approach for item component modification.

Decided not to use NeoForge's ModifyDefaultComponentsEvent because it fires before item tag data is loaded,	
and so we can't modify the stack sizes of all items belonging to a given tag. Moreover, using our own system	
allows us to update stack sizes on config reload, though we will need to minimize breaking side effects of both	
increasing and decreasing stack size settings in an existing world. Finally, Fabric and NeoForge both support our	
custom system, which simply works with Vanilla's tools.

### Tests show that these work in vanilla 1.21.1.
## Consumable
- Potions
- Bowl foods (1.20.1: needed BowlFoodItemMixin, SuspiciousStewItemMixin to patch item loss)
- Milk bucket (1.20.1: needed MilkBucketItemMixin to patch item loss)
- Totems
## Entity-spawning
- Splash potions, lingering potions
- Boats, minecarts, armor stands
## Other
- Fluid/mob buckets (1.20.1: needed BucketItemMixin to patch item loss)
- Music discs (1.20.1: needed RecordItemMixin to patch a duplication glitch)

### Tests show that these need fixes to support arbitrary stack sizes in 1.21.1.
- Solid buckets (e.g. powder snow) -> SolidBucketItemMixin to patch item loss
- Saddles, horse armor -> HorseInventoryMenuMixin to prevent redundant equipping

### Configurable cooldowns were added to balance increased stack sizes for
- Eggs -> EggItemMixin
- Throwable potions -> ThrowablePotionItemMixin, SplashPotionItemMixin, LingeringPotionItemMixin
- Snowballs -> SnowballItemMixin