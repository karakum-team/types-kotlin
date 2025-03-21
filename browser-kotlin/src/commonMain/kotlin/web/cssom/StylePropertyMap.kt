// Automatically generated - do not modify!

package web.cssom

import js.core.JsAny

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StylePropertyMap)
 */
sealed /* final */
external class StylePropertyMap
protected /* private */ constructor() :
    StylePropertyMapReadOnly {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StylePropertyMap/append)
     */
    fun append(
        property: String,
        vararg values: JsAny, /* CSSStyleValue | string */
    )

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StylePropertyMap/clear)
     */
    fun clear()

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StylePropertyMap/delete)
     */
    fun delete(property: String)

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StylePropertyMap/set)
     */
    fun set(
        property: String,
        vararg values: JsAny, /* CSSStyleValue | string */
    )
}
