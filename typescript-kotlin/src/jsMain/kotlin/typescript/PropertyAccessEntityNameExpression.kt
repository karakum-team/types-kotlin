// Automatically generated - do not modify!

package typescript

/** Brand for a PropertyAccessExpression which, like a QualifiedName, consists of a sequence of identifiers separated by dots. */
sealed external interface PropertyAccessEntityNameExpression : PropertyAccessExpression,
    Union.PropertyAccessEntityNameExpression_ {
    var _propertyAccessExpressionLikeQualifiedNameBrand: Any?
    override val expression: EntityNameExpression
    override val name: Identifier
}
