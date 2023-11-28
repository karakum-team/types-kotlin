// Automatically generated - do not modify!

package web.errors

import js.errors.JsError
import js.errors.JsErrorName

/**
 * An abnormal event (called an exception) which occurs as a result of calling a method or accessing a property of a web API.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/DOMException)
 */
open external class DOMException(
    message: String = definedExternally,
    name: JsErrorName = definedExternally,
) : JsError {
    /** [MDN Reference](https://developer.mozilla.org/docs/Web/API/DOMException/message) */
    override val message: String

    /** [MDN Reference](https://developer.mozilla.org/docs/Web/API/DOMException/name) */
    val name: String

    companion object
}
