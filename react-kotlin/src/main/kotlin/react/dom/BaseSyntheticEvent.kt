// Automatically generated - do not modify!

package react.dom

external interface BaseSyntheticEvent<E : Any, C : Any, T : Any> {
    val nativeEvent: E
    val currentTarget: C
    val target: T
    val bubbles: Boolean
    val cancelable: Boolean
    val defaultPrevented: Boolean
    val eventPhase: Number
    val isTrusted: Boolean

    // preventDefault(): void
    // isDefaultPrevented(): boolean
    // stopPropagation(): void
    // isPropagationStopped(): boolean
    // persist(): void
    val timeStamp: Number
    val type: String
}
