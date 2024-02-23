// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.mathml

import web.dom.Element
import web.mathml.MathMLElement
import web.mathml.MathMLTagName

sealed external interface MathMLTagName<T : MathMLElement>

inline fun <T : MathMLElement> MathMLTagName(
    tagName: String,
): MathMLTagName<T> =
    tagName.unsafeCast<MathMLTagName<T>>()
