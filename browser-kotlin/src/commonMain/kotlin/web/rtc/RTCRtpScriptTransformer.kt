// Automatically generated - do not modify!

package web.rtc

import js.core.JsAny
import js.core.JsInt
import js.core.Void
import js.promise.Promise
import seskar.js.JsAsync
import web.events.EventTarget
import web.streams.ReadableStream
import web.streams.WritableStream
import kotlin.js.JsName
import kotlin.js.definedExternally

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCRtpScriptTransformer)
 */
external class RTCRtpScriptTransformer
private constructor() :
    EventTarget {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCRtpScriptTransformer/options)
     */
    val options: JsAny?

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCRtpScriptTransformer/readable)
     */
    val readable: ReadableStream<*>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCRtpScriptTransformer/writable)
     */
    val writable: WritableStream<*>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCRtpScriptTransformer/generateKeyFrame)
     */
    @JsAsync
    @Suppress("WRONG_EXTERNAL_DECLARATION")
    suspend fun generateKeyFrame(rid: String = definedExternally): Int

    @JsName("generateKeyFrame")
    fun generateKeyFrameAsync(rid: String = definedExternally): Promise<JsInt>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/RTCRtpScriptTransformer/sendKeyFrameRequest)
     */
    @JsAsync
    @Suppress("WRONG_EXTERNAL_DECLARATION")
    suspend fun sendKeyFrameRequest()

    @JsName("sendKeyFrameRequest")
    fun sendKeyFrameRequestAsync(): Promise<Void>
}
