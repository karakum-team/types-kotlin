// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.html

import web.dom.Element
import web.html.HTMLElement
import web.html.HtmlTagName

sealed external interface HtmlTagName<T : HTMLElement>

inline fun <T : HTMLElement> HtmlTagName(
    tagName: String,
): HtmlTagName<T> =
    tagName.unsafeCast<HtmlTagName<T>>()
