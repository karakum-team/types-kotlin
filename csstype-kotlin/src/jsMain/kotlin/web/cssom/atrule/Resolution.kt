// Automatically generated - do not modify!

package web.cssom.atrule

import web.cssom.Length
import web.cssom.MediaQuery
import web.cssom.SizeQuery
import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

sealed interface Resolution

inline val Number.dpi: Resolution
    get() = "${this}dpi".unsafeCast<Resolution>()

inline val Number.dpcm: Resolution
    get() = "${this}dpcm".unsafeCast<Resolution>()

inline val Number.dppx: Resolution
    get() = "${this}dppx".unsafeCast<Resolution>()
