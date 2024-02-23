// Automatically generated - do not modify!

package web.dom

import web.dom.Document
import web.dom.Element
import web.dom.Node
import web.dom.ParentNode

sealed external interface NonElementParentNode {
/**
 * Returns the first element within node's descendants whose ID is elementId.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Document/getElementById)
 */
 fun getElementById(elementId: String): Element?
}
