// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.cssom

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

        sealed external interface StringType:
        Content,
FontFamily
        
        inline fun string(
     value: String,
): StringType =
    value.unsafeCast<StringType>()
