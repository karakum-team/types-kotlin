// Automatically generated - do not modify!

package web.credentials

import js.core.Void
import js.promise.Promise

/**
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CredentialsContainer)
 */
sealed external class CredentialsContainer {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CredentialsContainer/create)
     */
    @JsName("create")
    fun createAsync(options: CredentialCreationOptions = definedExternally): Promise<Credential?>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CredentialsContainer/get)
     */
    @JsName("get")
    fun getAsync(options: CredentialRequestOptions = definedExternally): Promise<Credential?>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CredentialsContainer/preventSilentAccess)
     */
    @JsName("preventSilentAccess")
    fun preventSilentAccessAsync(): Promise<Void>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CredentialsContainer/store)
     */
    @JsName("store")
    fun storeAsync(credential: Credential): Promise<Void>
}
