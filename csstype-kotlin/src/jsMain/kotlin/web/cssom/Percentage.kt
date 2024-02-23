// Automatically generated - do not modify!

package web.cssom

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

sealed external interface Percentage:
    Length

inline val Number.pct: Percentage
    get() = "${this}%".unsafeCast<Percentage>()
