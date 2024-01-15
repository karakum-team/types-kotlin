package karakum.typescript

internal const val NODE_VISITOR_TYPE_PARAMETERS =
    "<TIn extends Node, TInArray extends NodeArray<TIn> | undefined, TOut extends Node>"

internal const val EXCLUDED_VISIT_NODES = "visitNodes$NODE_VISITOR_TYPE_PARAMETERS"
