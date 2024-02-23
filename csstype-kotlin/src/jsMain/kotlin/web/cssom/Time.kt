// Automatically generated - do not modify!

package web.cssom

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

sealed external interface Time: TimeProperty

inline val Number.ms: Time
    get() = "${this}ms".unsafeCast<Time>()

inline val Number.s: Time
    get() = "${this}s".unsafeCast<Time>()
