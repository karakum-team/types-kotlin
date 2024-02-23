// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.keyboard

import web.keyboard.KeyCode

sealed external interface KeyCode
        
inline fun KeyCode(
    code: String,
): KeyCode =
    code.unsafeCast<KeyCode>()
