// Automatically generated - do not modify!

@file:JsModule("react-query")
@file:JsNonModule

@file:Suppress(
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package tanstack.query.core

typealias NotifyCallback = () -> Unit

typealias NotifyFunction = (callback: () -> Unit) -> Unit

typealias BatchNotifyFunction = (callback: () -> Unit) -> Unit

external fun createNotifyManager(): dynamic

external val notifyManager: dynamic
