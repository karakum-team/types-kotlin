// Automatically generated - do not modify!

package web.dom

import web.events.EventInstance
import web.uievents.DragEvent
import web.uievents.PointerEvent

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Text/drag_event)
 */
inline val <C : Text> C.dragEvent: EventInstance<DragEvent, C, Node>
    get() = EventInstance(this, DragEvent.DRAG)

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Text/dragend_event)
 */
inline val <C : Text> C.dragEndEvent: EventInstance<DragEvent, C, Node>
    get() = EventInstance(this, DragEvent.DRAG_END)

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Text/dragstart_event)
 */
inline val <C : Text> C.dragStartEvent: EventInstance<DragEvent, C, Node>
    get() = EventInstance(this, DragEvent.DRAG_START)

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Text/pointercancel_event)
 */
inline val <C : Text> C.pointerCancelEvent: EventInstance<PointerEvent, C, Node>
    get() = EventInstance(this, PointerEvent.POINTER_CANCEL)
