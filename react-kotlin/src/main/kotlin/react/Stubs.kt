// Automatically generated - do not modify!

@file:Suppress("NOTHING_TO_INLINE")

package react

typealias Props = RProps
typealias PropsWithChildren = RProps

external interface IntrinsicType<P : Props> : ElementType<P>

inline fun <P : Props> IntrinsicType(
    tag: String,
): IntrinsicType<P> =
    tag.unsafeCast<IntrinsicType<P>>()
