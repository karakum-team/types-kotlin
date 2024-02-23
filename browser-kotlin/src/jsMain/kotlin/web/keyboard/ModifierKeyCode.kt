// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.keyboard

import web.keyboard.KeyCode
import web.keyboard.ModifierKeyCode

sealed external interface ModifierKeyCode
        
inline fun ModifierKeyCode(
    code: String,
): ModifierKeyCode =
    code.unsafeCast<ModifierKeyCode>()
