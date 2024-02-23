// Automatically generated - do not modify!

package web.validation

import web.validation.ValidationTarget
import web.validation.ValidityState

            external interface ValidationTarget {
                val validationMessage: String
val validity: ValidityState
val willValidate: Boolean
fun checkValidity(): Boolean
fun reportValidity(): Boolean
            }
