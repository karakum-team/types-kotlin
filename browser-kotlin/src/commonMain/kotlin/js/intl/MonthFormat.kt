// Automatically generated - do not modify!

@file:Suppress(
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package js.intl

import seskar.js.JsValue

sealed external interface MonthFormat {
    companion object {
        @JsValue("numeric")
        val numeric: MonthFormat

        @JsValue("2-digit")
        val twoDigit: MonthFormat

        @JsValue("long")
        val long: MonthFormat

        @JsValue("short")
        val short: MonthFormat

        @JsValue("narrow")
        val narrow: MonthFormat
    }
}
