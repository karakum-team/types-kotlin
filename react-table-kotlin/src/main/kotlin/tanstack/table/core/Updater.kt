// Automatically generated - do not modify!

package tanstack.table.core

sealed external interface Updater<T> /* T | ((old: T) -> T) */

inline fun <T> Updater(
    source: T,
): Updater<T> =
    source.unsafeCast<Updater<T>>()

inline fun <T> Updater(
    source: (old: T) -> T,
): Updater<T> =
    source.unsafeCast<Updater<T>>()