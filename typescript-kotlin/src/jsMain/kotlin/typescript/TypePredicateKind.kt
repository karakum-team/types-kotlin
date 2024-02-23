// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package typescript

import seskar.js.JsVirtual
import seskar.js.JsIntValue

        @JsVirtual
        external sealed interface TypePredicateKind {
            companion object {
                @JsIntValue(0)
val This : TypePredicateKind.This
@JsIntValue(1)
val Identifier : TypePredicateKind.Identifier
@JsIntValue(2)
val AssertsThis : TypePredicateKind.AssertsThis
@JsIntValue(3)
val AssertsIdentifier : TypePredicateKind.AssertsIdentifier
            }
            
            sealed interface This : TypePredicateKind
sealed interface Identifier : TypePredicateKind
sealed interface AssertsThis : TypePredicateKind
sealed interface AssertsIdentifier : TypePredicateKind
        }
