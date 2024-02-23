// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.cssom.atrule

import web.cssom.Length
import web.cssom.MediaQuery
import web.cssom.SizeQuery
import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

        @JsVirtual
        sealed external interface ColorGamut {
            companion object {
                @JsValue("srgb")    
val srgb: ColorGamut
@JsValue("p3")    
val p3: ColorGamut
@JsValue("rec2020")    
val rec2020: ColorGamut
            }
        }
