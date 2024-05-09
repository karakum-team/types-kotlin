// Automatically generated - do not modify!

package web.push

import js.array.ReadonlyArray
import js.promise.Promise
import web.permissions.PermissionState

/**
 * This Push API interface provides a way to receive notifications from third-party servers as well as request URLs for push notifications.
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/PushManager)
 */
sealed external class PushManager {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/PushManager/getSubscription)
     */
    @JsName("getSubscription")
    fun getSubscriptionAsync(): Promise<PushSubscription?>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/PushManager/permissionState)
     */
    @JsName("permissionState")
    fun permissionStateAsync(options: PushSubscriptionOptionsInit = definedExternally): Promise<PermissionState>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/PushManager/subscribe)
     */
    @JsName("subscribe")
    fun subscribeAsync(options: PushSubscriptionOptionsInit = definedExternally): Promise<PushSubscription>

    companion object {
        /**
         * [MDN Reference](https://developer.mozilla.org/docs/Web/API/PushManager/supportedContentEncodings_static)
         */
        val supportedContentEncodings: ReadonlyArray<String>
    }
}
