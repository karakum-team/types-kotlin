// Automatically generated - do not modify!

package web.gl

import seskar.js.JsAlias
import seskar.js.JsAlias.Companion.THIS
import web.events.Event
import web.events.EventType
import kotlin.js.definedExternally

/**
 * The WebContextEvent interface is part of the WebGL API and is an interface for an event that is generated in response to a status change to the WebGL rendering context.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/WebGLContextEvent)
 */
open external class WebGLContextEvent(
    override val type: EventType<WebGLContextEvent>,
    init: WebGLContextEventInit = definedExternally,
) : Event {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/WebGLContextEvent/statusMessage)
     */
    val statusMessage: String

    @JsAlias(THIS)
    override fun asInit(): WebGLContextEventInit
}
