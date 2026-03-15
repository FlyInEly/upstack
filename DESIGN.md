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