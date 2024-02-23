// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.cssom

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

typealias Inset = AutoLengthProperty

   inline fun Inset(
        vertical: AutoLength,
horizontal: AutoLength,
   ): Inset =
       "$vertical $horizontal".unsafeCast<Inset>()

   inline fun Inset(
        top: AutoLength,
horizontal: AutoLength,
bottom: AutoLength,
   ): Inset =
       "$top $horizontal $bottom".unsafeCast<Inset>()

   inline fun Inset(
        top: AutoLength,
right: AutoLength,
bottom: AutoLength,
left: AutoLength,
   ): Inset =
       "$top $right $bottom $left".unsafeCast<Inset>()
