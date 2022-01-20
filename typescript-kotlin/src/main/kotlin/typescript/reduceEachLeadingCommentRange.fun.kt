// Automatically generated - do not modify!

@file:JsModule("typescript")
@file:JsNonModule

package typescript

external fun <T, U> reduceEachLeadingCommentRange(
    text: String,
    pos: Double,
    cb: (pos: Double, end: Double, kind: CommentKind, hasTrailingNewLine: Boolean, state: T, memo: U) -> U,
    state: T,
    initial: U,
): U?
