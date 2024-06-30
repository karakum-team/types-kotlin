// Automatically generated - do not modify!

package web.window

import web.animations.AnimationEvent
import web.clipboard.ClipboardEvent
import web.csp.SecurityPolicyViolationEvent
import web.cssom.TransitionEvent
import web.device.DeviceMotionEvent
import web.device.DeviceOrientationEvent
import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType
import web.form.FormDataEvent
import web.form.SubmitEvent
import web.gamepad.GamepadEvent
import web.history.HashChangeEvent
import web.history.PageTransitionEvent
import web.history.PopStateEvent
import web.messaging.MessageEvent
import web.pip.PictureInPictureEvent
import web.promise.PromiseRejectionEvent
import web.storage.StorageEvent
import web.uievents.*

inline val <C : Window> C.abortEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("abort"))

inline val <C : Window> C.afterPrintEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("afterprint"))

inline val <C : Window> C.animationCancelEvent: EventInstance<AnimationEvent, C, EventTarget>
    get() = EventInstance(this, EventType("animationcancel"))

inline val <C : Window> C.animationEndEvent: EventInstance<AnimationEvent, C, EventTarget>
    get() = EventInstance(this, EventType("animationend"))

inline val <C : Window> C.animationIterationEvent: EventInstance<AnimationEvent, C, EventTarget>
    get() = EventInstance(this, EventType("animationiteration"))

inline val <C : Window> C.animationStartEvent: EventInstance<AnimationEvent, C, EventTarget>
    get() = EventInstance(this, EventType("animationstart"))

inline val <C : Window> C.appinstalledEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("appinstalled"))

inline val <C : Window> C.auxClickEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("auxclick"))

inline val <C : Window> C.beforeInputEvent: EventInstance<InputEvent, C, EventTarget>
    get() = EventInstance(this, EventType("beforeinput"))

inline val <C : Window> C.beforePrintEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("beforeprint"))

inline val <C : Window> C.beforeUnloadEvent: EventInstance<BeforeUnloadEvent, C, EventTarget>
    get() = EventInstance(this, EventType("beforeunload"))

inline val <C : Window> C.blurEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("blur"))

inline val <C : Window> C.cancelEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("cancel"))

inline val <C : Window> C.changeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("change"))

inline val <C : Window> C.clickEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("click"))

inline val <C : Window> C.clipboardchangeEvent: EventInstance<ClipboardEvent, C, EventTarget>
    get() = EventInstance(this, EventType("clipboardchange"))

inline val <C : Window> C.compositionEndEvent: EventInstance<CompositionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("compositionend"))

inline val <C : Window> C.compositionStartEvent: EventInstance<CompositionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("compositionstart"))

inline val <C : Window> C.compositionUpdateEvent: EventInstance<CompositionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("compositionupdate"))

inline val <C : Window> C.contextMenuEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("contextmenu"))

inline val <C : Window> C.copyEvent: EventInstance<ClipboardEvent, C, EventTarget>
    get() = EventInstance(this, EventType("copy"))

inline val <C : Window> C.cutEvent: EventInstance<ClipboardEvent, C, EventTarget>
    get() = EventInstance(this, EventType("cut"))

inline val <C : Window> C.dblClickEvent: EventInstance<MouseEvent, C, EventTarget>
    get() = EventInstance(this, EventType("dblclick"))

inline val <C : Window> C.deviceMotionEvent: EventInstance<DeviceMotionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("devicemotion"))

inline val <C : Window> C.deviceOrientationEvent: EventInstance<DeviceOrientationEvent, C, EventTarget>
    get() = EventInstance(this, EventType("deviceorientation"))

inline val <C : Window> C.deviceOrientationAbsoluteEvent: EventInstance<DeviceOrientationEvent, C, EventTarget>
    get() = EventInstance(this, EventType("deviceorientationabsolute"))

inline val <C : Window> C.DOMActivateEvent: EventInstance<UIEvent, C, EventTarget>
    get() = EventInstance(this, EventType("DOMActivate"))

inline val <C : Window> C.domContentLoadedEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("DOMContentLoaded"))

inline val <C : Window> C.DOMFocusInEvent: EventInstance<FocusEvent, C, EventTarget>
    get() = EventInstance(this, EventType("DOMFocusIn"))

inline val <C : Window> C.DOMFocusOutEvent: EventInstance<FocusEvent, C, EventTarget>
    get() = EventInstance(this, EventType("DOMFocusOut"))

inline val <C : Window> C.dragEvent: EventInstance<DragEvent, C, EventTarget>
    get() = EventInstance(this, EventType("drag"))

inline val <C : Window> C.dragEndEvent: EventInstance<DragEvent, C, EventTarget>
    get() = EventInstance(this, EventType("dragend"))

inline val <C : Window> C.dragEnterEvent: EventInstance<DragEvent, C, EventTarget>
    get() = EventInstance(this, EventType("dragenter"))

inline val <C : Window> C.dragLeaveEvent: EventInstance<DragEvent, C, EventTarget>
    get() = EventInstance(this, EventType("dragleave"))

inline val <C : Window> C.dragOverEvent: EventInstance<DragEvent, C, EventTarget>
    get() = EventInstance(this, EventType("dragover"))

inline val <C : Window> C.dragStartEvent: EventInstance<DragEvent, C, EventTarget>
    get() = EventInstance(this, EventType("dragstart"))

inline val <C : Window> C.dropEvent: EventInstance<DragEvent, C, EventTarget>
    get() = EventInstance(this, EventType("drop"))

inline val <C : Window> C.enterPictureInPictureEvent: EventInstance<PictureInPictureEvent, C, EventTarget>
    get() = EventInstance(this, EventType("enterpictureinpicture"))

inline val <C : Window> C.errorEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : Window> C.focusEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("focus"))

inline val <C : Window> C.focusInEvent: EventInstance<FocusEvent, C, EventTarget>
    get() = EventInstance(this, EventType("focusin"))

inline val <C : Window> C.focusOutEvent: EventInstance<FocusEvent, C, EventTarget>
    get() = EventInstance(this, EventType("focusout"))

inline val <C : Window> C.formDataEvent: EventInstance<FormDataEvent, C, EventTarget>
    get() = EventInstance(this, EventType("formdata"))

inline val <C : Window> C.fullscreenChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("fullscreenchange"))

inline val <C : Window> C.fullscreenErrorEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("fullscreenerror"))

inline val <C : Window> C.gamepadConnectedEvent: EventInstance<GamepadEvent, C, EventTarget>
    get() = EventInstance(this, EventType("gamepadconnected"))

inline val <C : Window> C.gamepadDisconnectedEvent: EventInstance<GamepadEvent, C, EventTarget>
    get() = EventInstance(this, EventType("gamepaddisconnected"))

inline val <C : Window> C.gotPointerCaptureEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("gotpointercapture"))

inline val <C : Window> C.hashChangeEvent: EventInstance<HashChangeEvent, C, EventTarget>
    get() = EventInstance(this, EventType("hashchange"))

inline val <C : Window> C.inputEvent: EventInstance<InputEvent, C, EventTarget>
    get() = EventInstance(this, EventType("input"))

inline val <C : Window> C.keyDownEvent: EventInstance<KeyboardEvent, C, EventTarget>
    get() = EventInstance(this, EventType("keydown"))

inline val <C : Window> C.keyPressEvent: EventInstance<KeyboardEvent, C, EventTarget>
    get() = EventInstance(this, EventType("keypress"))

inline val <C : Window> C.keyUpEvent: EventInstance<KeyboardEvent, C, EventTarget>
    get() = EventInstance(this, EventType("keyup"))

inline val <C : Window> C.languageChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("languagechange"))

inline val <C : Window> C.leavePictureInPictureEvent: EventInstance<PictureInPictureEvent, C, EventTarget>
    get() = EventInstance(this, EventType("leavepictureinpicture"))

inline val <C : Window> C.loadEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("load"))

inline val <C : Window> C.lostPointerCaptureEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("lostpointercapture"))

inline val <C : Window> C.messageEvent: EventInstance<MessageEvent<Any?>, C, EventTarget>
    get() = EventInstance(this, EventType("message"))

inline val <C : Window> C.messageErrorEvent: EventInstance<MessageEvent<Any?>, C, EventTarget>
    get() = EventInstance(this, EventType("messageerror"))

inline val <C : Window> C.mouseDownEvent: EventInstance<MouseEvent, C, EventTarget>
    get() = EventInstance(this, EventType("mousedown"))

inline val <C : Window> C.mouseMoveEvent: EventInstance<MouseEvent, C, EventTarget>
    get() = EventInstance(this, EventType("mousemove"))

inline val <C : Window> C.mouseOutEvent: EventInstance<MouseEvent, C, EventTarget>
    get() = EventInstance(this, EventType("mouseout"))

inline val <C : Window> C.mouseOverEvent: EventInstance<MouseEvent, C, EventTarget>
    get() = EventInstance(this, EventType("mouseover"))

inline val <C : Window> C.mouseUpEvent: EventInstance<MouseEvent, C, EventTarget>
    get() = EventInstance(this, EventType("mouseup"))

inline val <C : Window> C.offlineEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("offline"))

inline val <C : Window> C.onlineEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("online"))

inline val <C : Window> C.orientationchangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("orientationchange"))

inline val <C : Window> C.pageHideEvent: EventInstance<PageTransitionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pagehide"))

inline val <C : Window> C.pageShowEvent: EventInstance<PageTransitionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pageshow"))

inline val <C : Window> C.pasteEvent: EventInstance<ClipboardEvent, C, EventTarget>
    get() = EventInstance(this, EventType("paste"))

inline val <C : Window> C.pointerCancelEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pointercancel"))

inline val <C : Window> C.pointerDownEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pointerdown"))

inline val <C : Window> C.pointerEnterEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pointerenter"))

inline val <C : Window> C.pointerLeaveEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pointerleave"))

inline val <C : Window> C.pointerMoveEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pointermove"))

inline val <C : Window> C.pointerOutEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pointerout"))

inline val <C : Window> C.pointerOverEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pointerover"))

inline val <C : Window> C.pointerrawupdateEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pointerrawupdate"))

inline val <C : Window> C.pointerUpEvent: EventInstance<PointerEvent, C, EventTarget>
    get() = EventInstance(this, EventType("pointerup"))

inline val <C : Window> C.popStateEvent: EventInstance<PopStateEvent, C, EventTarget>
    get() = EventInstance(this, EventType("popstate"))

inline val <C : Window> C.rejectionHandledEvent: EventInstance<PromiseRejectionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("rejectionhandled"))

inline val <C : Window> C.resetEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("reset"))

inline val <C : Window> C.resizeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("resize"))

inline val <C : Window> C.scrollEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("scroll"))

inline val <C : Window> C.scrollEndEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("scrollend"))

inline val <C : Window> C.securityPolicyViolationEvent: EventInstance<SecurityPolicyViolationEvent, C, EventTarget>
    get() = EventInstance(this, EventType("securitypolicyviolation"))

inline val <C : Window> C.selectEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("select"))

inline val <C : Window> C.selectionChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("selectionchange"))

inline val <C : Window> C.selectStartEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("selectstart"))

inline val <C : Window> C.slotChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("slotchange"))

inline val <C : Window> C.storageEvent: EventInstance<StorageEvent, C, EventTarget>
    get() = EventInstance(this, EventType("storage"))

inline val <C : Window> C.submitEvent: EventInstance<SubmitEvent, C, EventTarget>
    get() = EventInstance(this, EventType("submit"))

inline val <C : Window> C.touchCancelEvent: EventInstance<TouchEvent, C, EventTarget>
    get() = EventInstance(this, EventType("touchcancel"))

inline val <C : Window> C.touchEndEvent: EventInstance<TouchEvent, C, EventTarget>
    get() = EventInstance(this, EventType("touchend"))

inline val <C : Window> C.touchMoveEvent: EventInstance<TouchEvent, C, EventTarget>
    get() = EventInstance(this, EventType("touchmove"))

inline val <C : Window> C.touchStartEvent: EventInstance<TouchEvent, C, EventTarget>
    get() = EventInstance(this, EventType("touchstart"))

inline val <C : Window> C.transitionCancelEvent: EventInstance<TransitionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("transitioncancel"))

inline val <C : Window> C.transitionEndEvent: EventInstance<TransitionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("transitionend"))

inline val <C : Window> C.transitionRunEvent: EventInstance<TransitionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("transitionrun"))

inline val <C : Window> C.transitionStartEvent: EventInstance<TransitionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("transitionstart"))

inline val <C : Window> C.unhandledRejectionEvent: EventInstance<PromiseRejectionEvent, C, EventTarget>
    get() = EventInstance(this, EventType("unhandledrejection"))

inline val <C : Window> C.unloadEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("unload"))

inline val <C : Window> C.wheelEvent: EventInstance<WheelEvent, C, EventTarget>
    get() = EventInstance(this, EventType("wheel"))
