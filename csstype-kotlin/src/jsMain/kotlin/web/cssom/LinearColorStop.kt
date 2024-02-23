// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.cssom

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

sealed external interface LinearColorStop

   inline fun stop(
        color: Color,
value: Length,
   ): LinearColorStop =
       "$color $value".unsafeCast<LinearColorStop>()

   inline fun stop(
        color: Color,
from: Length,
to: Length,
   ): LinearColorStop =
       "$color $from $to".unsafeCast<LinearColorStop>()
