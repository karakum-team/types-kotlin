// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.window

import web.window.Window
import web.window.WindowName
import web.window.WindowTarget

sealed external interface WindowName :
    WindowTarget

inline fun WindowName(
    value: String,
): WindowName =
    value.unsafeCast<WindowName>()
