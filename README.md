# Upstack

Upstack is an open-source mod which allows the max stack size of common items to be configured between 1 and 99.
It also patches Vanilla issues that occur when some items are made stackable.

### Disclaimer

This mod is a passion project. Support for particular versions and features is not guaranteed.
Feedback and feature requests (without expectation) are welcome!

## Version Support

| MC     | NeoForge | Fabric |
|--------|----------|--------|
| 1.21.1 | ✅        | ✅      |

## How does Upstack work?

To work, Upstack must be installed on **both the server and the client**. (Stack size modification is purely
server-side,
but bug fixes are dual-side.)

On server startup, Upstack modifies the max stack size component of each configured item. Items set to values outside
\[1, 99] are not modified.

In most cases, modifying an item's stack size doesn't change its expected behavior.
However, there are several known exceptions, each resulting in different issues:

1. Multiple saddles can be equipped to one horse/donkey/mule (redundant).
2. Multiple banner patterns can be placed in a loom at a time, even though banner patterns non-consumable (redundant).
3. Placing one powder snow bucket deletes all remaining items in the stack (bug).
4. Combining a stack of X items with a stack of Y enchanted books in an anvil violates item conservation (bug, exploit).
    1. Enchants all X items, even if there aren't enough enchanted books to do so (Y < X).
    2. Consumes all Y enchanted books, even if there are fewer items to enchant (X < Y).
    3. Costs Y times the experience of enchanting one item, even if the actual number of items enchanted differs (X /=
       Y).
5. Throwing splash potions, lingering potions, eggs, and snowballs has no cooldown, even though this could cause balance
   issues (balance issue).

Upstack injects the following patches to address these issues:

1. Set the max stack size of the horse/donkey/mule's saddle slot to 1.
2. Set the max stack size of the loom's banner pattern slot to 1.
3. When a powder snow bucket is placed, decrement the stack size instead of deleting the stack.
4. When a stack of X items is combined with a stack of Y enchanted books:
    1. Enchant only 1 item at a time.
    2. Consume only 1 enchanted book at a time.
    3. Cost the experience of enchanting one item.
5. Add optional, configurable cooldowns to splash potions, lingering potions, eggs, and snowballs.

## Planned Features

### Planned

* Make max stack size bug fixes toggleable, in case of unforeseen conflicts with other mods.
* Make max stack size modification data-driven, while retaining the ability to modify common items/tags through the
  config.

### Considering

* Add compatibility with popular mods which make max stack sizes able to exceed 99 (the Vanilla limit).

### Not Planned

* Make max stack sizes able to exceed 99 (the Vanilla limit). Increasing Vanilla's max stack size limit and resolving
  consequent bugs and mod conflicts is out of the scope of this mod. 
