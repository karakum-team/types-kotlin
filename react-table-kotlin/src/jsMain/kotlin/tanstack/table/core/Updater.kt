// Automatically generated - do not modify!

package tanstack.table.core

import js.reflect.unsafeCast

sealed external interface Updater<T : Any> /* T | ((old: T) -> T) */

inline fun <T : Any> Updater(
    source: T,
): Updater<T> =
    unsafeCast(source)

inline fun <T : Any> Updater(
    noinline source: (old: T) -> T,
): Updater<T> =
    unsafeCast(source)
