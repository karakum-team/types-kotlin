// Automatically generated - do not modify!

package typescript

import js.array.ReadonlyArray
import js.collections.JsMap

external sealed interface ConditionalRoot {
var node: ConditionalTypeNode
var checkType: Type
var extendsType: Type
var isDistributive: Boolean
var inferTypeParameters: ReadonlyArray<TypeParameter>?
var outerTypeParameters: ReadonlyArray<TypeParameter>?
var instantiations: JsMap<String, Type>?
var aliasSymbol: Symbol?
var aliasTypeArguments: ReadonlyArray<Type>?
}
