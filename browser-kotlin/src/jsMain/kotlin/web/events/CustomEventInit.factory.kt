// Automatically generated - do not modify!

package web.events

import js.objects.jso
import web.events.Event
import web.events.EventInit

fun <T> CustomEventInit(
    detail: T?,
): CustomEventInit<T> =
    jso {
        asDynamic().detail = detail
    }
