// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package js.decorators

import seskar.js.JsValue
import seskar.js.JsVirtual

        @JsVirtual
        external sealed interface DecoratorContextKind {
            companion object {
                @JsValue("class")
val `class` : DecoratorContextKind.`class`
@JsValue("method")
val method : DecoratorContextKind.method
@JsValue("getter")
val getter : DecoratorContextKind.getter
@JsValue("setter")
val setter : DecoratorContextKind.setter
@JsValue("accessor")
val accessor : DecoratorContextKind.accessor
@JsValue("field")
val field : DecoratorContextKind.field
            }
            
            sealed interface `class` : DecoratorContextKind
sealed interface method : DecoratorContextKind
sealed interface getter : DecoratorContextKind
sealed interface setter : DecoratorContextKind
sealed interface accessor : DecoratorContextKind
sealed interface field : DecoratorContextKind
        }
