// Automatically generated - do not modify!

package web.authn

import js.objects.JsPlainObject
import web.selection.Selection

@JsPlainObject
sealed external interface AuthenticatorSelectionCriteria {
var authenticatorAttachment: AuthenticatorAttachment?
var requireResidentKey: Boolean?
var residentKey: ResidentKeyRequirement?
var userVerification: UserVerificationRequirement?
}
