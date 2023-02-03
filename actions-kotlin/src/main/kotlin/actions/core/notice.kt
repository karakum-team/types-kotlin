package actions.core

external fun notice(
    message: String,
    properties: AnnotationProperties = definedExternally,
)

external fun notice(
    message: Error,
    properties: AnnotationProperties = definedExternally,
)
