// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.window

import seskar.js.JsValue
import seskar.js.JsVirtual
import web.window.Window
import web.window.WindowTarget

        @JsVirtual
        sealed external interface WindowTarget {
            companion object {
                @JsValue("_self")    
val _self: WindowTarget
@JsValue("_blank")    
val _blank: WindowTarget
@JsValue("_parent")    
val _parent: WindowTarget
@JsValue("_top")    
val _top: WindowTarget
            }
        }
