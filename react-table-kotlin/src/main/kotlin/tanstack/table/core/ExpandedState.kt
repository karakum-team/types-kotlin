// Automatically generated - do not modify!

package tanstack.table.core

import kotlinx.js.Record

sealed external interface ExpandedState /* true | Record<string, boolean> */

inline fun ExpandedState(
    source: Boolean, /* true */
): ExpandedState =
    source.unsafeCast<ExpandedState>()

inline fun ExpandedState(
    source: Record<String, Boolean>,
): ExpandedState =
    source.unsafeCast<ExpandedState>()
