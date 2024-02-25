// Automatically generated - do not modify!

package react.dom.events

import web.dom.Element

external interface DragEvent<out T : Element> : MouseEvent<T, NativeDragEvent<T>> {
    val dataTransfer: web.data.DataTransfer
}
