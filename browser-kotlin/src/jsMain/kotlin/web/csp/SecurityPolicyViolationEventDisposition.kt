// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.csp

import seskar.js.JsValue
import seskar.js.JsVirtual
import web.events.Event

        @JsVirtual
        sealed external interface SecurityPolicyViolationEventDisposition {
            companion object {
                @JsValue("enforce")    
val enforce: SecurityPolicyViolationEventDisposition
@JsValue("report")    
val report: SecurityPolicyViolationEventDisposition
            }
        }
