## Unreleased

### Added
* Add configurable indicators for overstacked items (stacks which exceed the max stack size).

### Changed
* Make config comments more descriptive, including a notice that a game restart is required to restore the default max 
stack size of an item.

### Fixed
* Fix crash when mod loads on a dedicated server (#3).

### Known Issues
* Two world restarts are required to update the max stack size of existing items (#2) and show their correct 
item count (#1). Since v1.0.0.

## v1.0.0
* Make stack sizes configurable for common items and tags in vanilla, Pastel, Farmer's Delight, Cobblemon, and Vanilla
  Backport
* Fix issues that occur when making saddles, banner patterns, powder snow buckets, and enchanted books stackable
* Add configurable cooldowns for throwable potions, eggs, and snowballs to address potential balance issues with
  increasing their stack size