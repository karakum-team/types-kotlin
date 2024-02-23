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
        sealed external interface DisplayMode {
            companion object {
                @JsValue("fullscreen")    
val fullscreen: DisplayMode
@JsValue("standalone")    
val standalone: DisplayMode
@JsValue("minimal-ui")    
val minimalUi: DisplayMode
@JsValue("browser")    
val browser: DisplayMode
@JsValue("window-controls-overlay")    
val windowControlsOverlay: DisplayMode
            }
        }
