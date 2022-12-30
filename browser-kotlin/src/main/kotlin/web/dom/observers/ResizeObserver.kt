// Automatically generated - do not modify!

package web.dom.observers

import dom.Element

external class ResizeObserver(
    callback: ResizeObserverCallback,
) {
    fun disconnect()
    fun observe(
        target: Element,
        options: ResizeObserverOptions = definedExternally,
    )

    fun unobserve(target: Element)
}
