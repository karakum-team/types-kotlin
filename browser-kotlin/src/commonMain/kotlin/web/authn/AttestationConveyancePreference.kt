// Automatically generated - do not modify!

@file:Suppress(
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package web.authn

import seskar.js.JsValue

sealed external interface AttestationConveyancePreference {
    companion object {
        @JsValue("direct")
        val direct: AttestationConveyancePreference

        @JsValue("enterprise")
        val enterprise: AttestationConveyancePreference

        @JsValue("indirect")
        val indirect: AttestationConveyancePreference

        @JsValue("none")
        val none: AttestationConveyancePreference
    }
}
