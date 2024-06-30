// Automatically generated - do not modify!

package web.html

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType
import web.pip.PictureInPictureEvent

inline val <C : HTMLVideoElement> C.enterPictureInPictureEvent: EventInstance<PictureInPictureEvent, C, EventTarget>
    get() = EventInstance(this, EventType("enterpictureinpicture"))

inline val <C : HTMLVideoElement> C.leavePictureInPictureEvent: EventInstance<PictureInPictureEvent, C, EventTarget>
    get() = EventInstance(this, EventType("leavepictureinpicture"))
