// Automatically generated - do not modify!

package web.html

import web.dom.Element
import web.html.HTMLCollection

sealed external class HTMLCollection<T : Element> :
HTMLCollectionBase<T> {
/**
 * Retrieves a select object or an object from an options collection.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/HTMLCollection/namedItem)
 */
 fun namedItem(name: String): T?
}
