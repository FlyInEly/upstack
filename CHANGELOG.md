## Unreleased

### Added
* Add configurable indicators for overstacked items (stacks which exceed the max stack size).

### Changed
* Make config comments more descriptive, including a notice that a game restart is required to restore the default max 
stack size of an item.

## v1.0.1

### Fixed

* Fix crash when mod loads on a dedicated server (#3).

### Known Issues

* After stack sizes are reconfigured, the world must be restarted twice to update existing items (#2) (#1).

## v1.0.0

### Added

* Make stack sizes configurable for common items and tags in vanilla, Pastel, Farmer's Delight, Cobblemon, and Vanilla
  Backport.
* Add patches for issues that occur when making saddles, banner patterns, powder snow buckets, and enchanted books
  stackable.
* Add configurable cooldowns for throwable potions, eggs, and snowballs to address balance issues with increasing their
  stack size.