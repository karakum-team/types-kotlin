// Automatically generated - do not modify!

@file:Suppress(
"NOTHING_TO_INLINE",
)

package web.autofill

import web.autofill.AutoFill

sealed external interface AutoFillField

                inline fun AutoFillField(
                    contactKind: AutoFillContactKind,
contactField: AutoFillContactField,
                ): AutoFillField =
                    "$contactKind $contactField".unsafeCast<AutoFillField>()
