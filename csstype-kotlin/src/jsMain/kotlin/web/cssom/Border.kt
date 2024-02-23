// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.cssom

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

sealed external interface Border

   inline fun Border(
        width: Length,
style: LineStyle,
   ): Border =
       "$width $style".unsafeCast<Border>()

   inline fun Border(
        width: Length,
style: LineStyle,
color: Color,
   ): Border =
       "$width $style $color".unsafeCast<Border>()
