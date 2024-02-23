// Automatically generated - do not modify!

package web.messaging

import js.objects.jso
import web.events.Event
import web.events.EventInit
import web.messaging.MessageEvent

fun <T> MessageEventInit(
    data: T?,
): MessageEventInit<T> =
    jso {
        asDynamic().data = data
    }
