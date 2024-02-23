// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.cssom

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

        sealed external interface NumberType:
        AnimationIterationCount,
AspectRatio,
Flex,
FlexGrow,
FlexShrink,
LineHeight,
Opacity,
Scale,
Zoom
        
        inline fun number(
     value: Double,
): NumberType =
    value.unsafeCast<NumberType>()
