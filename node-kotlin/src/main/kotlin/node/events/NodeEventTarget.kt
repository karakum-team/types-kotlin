// Automatically generated - do not modify!

package node.events

sealed external interface NodeEventTarget {
    fun once(
        eventName: Any, /* string | symbol */
        listener: Function<Unit>,
    ): NodeEventTarget
}
