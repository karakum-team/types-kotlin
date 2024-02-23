// Automatically generated - do not modify!

package web.serviceworker

import js.objects.JsPlainObject
import web.events.Event
import web.events.EventInit
import web.notifications.Notification
import web.serviceworker.ExtendableEvent
import web.serviceworker.ExtendableEventInit

@JsPlainObject
external interface NotificationEventInit :
ExtendableEventInit  {
val action: String?
val notification: Notification
}
