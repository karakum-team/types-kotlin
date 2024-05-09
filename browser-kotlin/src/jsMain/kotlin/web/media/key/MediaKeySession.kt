// Automatically generated - do not modify!

package web.media.key

import js.buffer.BufferSource
import js.core.Void
import js.promise.Promise
import web.events.Event
import web.events.EventHandler
import web.events.EventTarget
import web.time.EpochTimeStamp

/**
 * This EncryptedMediaExtensions API interface represents a context for message exchange with a content decryption module (CDM).
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession)
 */
sealed external class MediaKeySession :
    EventTarget {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/closed)
     */
    val closed: Promise<MediaKeySessionClosedReason>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/expiration)
     */
    val expiration: EpochTimeStamp

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/keyStatuses)
     */
    val keyStatuses: MediaKeyStatusMap

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/keystatuseschange_event)
     */
    var onkeystatuseschange: EventHandler<Event, MediaKeySession>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/message_event)
     */
    var onmessage: EventHandler<MediaKeyMessageEvent, MediaKeySession>?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/sessionId)
     */
    val sessionId: String

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/close)
     */
    @JsName("close")
    fun closeAsync(): Promise<Void>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/generateRequest)
     */
    @JsName("generateRequest")
    fun generateRequestAsync(
        initDataType: String,
        initData: BufferSource,
    ): Promise<Void>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/load)
     */
    @JsName("load")
    fun loadAsync(sessionId: String): Promise<Boolean>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/remove)
     */
    @JsName("remove")
    fun removeAsync(): Promise<Void>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/MediaKeySession/update)
     */
    @JsName("update")
    fun updateAsync(response: BufferSource): Promise<Void>
}
