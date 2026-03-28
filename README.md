# Upstack

Upstack is an open-source mod which allows the max stack size of common items to be configured between 1 and 99.
It also patches Vanilla issues that result from making some items stackable.

## How It Works

To work, Upstack must be installed on _both the server and the client_. (Stack size modification is purely server-side,
but bug fixes are dual-side.)

On server startup, Upstack modifies the max stack size component of each configured item. Items set to values outside
\[1, 99] are not modified.

In most cases, modifying an item's stack size doesn't change its expected behavior. However, there are known exceptions:

* Multiple saddles can be equipped to one horse/donkey/mule (redundant).
* Multiple banner patterns can be placed in one loom (redundant, as banner patterns are not consumed).
* Placing one powder snow bucket deletes all remaining items in the stack (bug).
* Combining an item with a stack of enchanted books in an anvil applies the enchantment once, but consumes 
all items in the stack, and costs the same as applying... [WIP] 
* Combining a stack of enchanted books with another item in an anvil  (exploit)

makes several assumptions about the stack size of certain items




Client and server

## Disclaimer

This mod is a passion project. Support for particular versions and features is not guaranteed.

## Version Support

| MC     | NeoForge | Fabric |
|--------|----------|--------|
| 1.21.1 | ✅        | ✅      |

## Planned Features

### Probably

* Make max stack size bug fixes toggleable, in case of unforeseen conflicts with other mods.
* Make max stack size modification data-driven, while retaining the ability to modify common items/tags through the
  config.

### Maybe

* Add compatibility with popular mods which make max stack sizes able to exceed 99 (the Vanilla limit).

### Not Planned

* Make max stack sizes able to exceed 99 (the Vanilla limit). Increasing Vanilla's max stack size limit and resolving
  consequent bugs and mod conflicts is out of the scope of this mod. 
