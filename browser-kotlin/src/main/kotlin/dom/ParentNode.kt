// Automatically generated - do not modify!

package dom

sealed external interface ParentNode /* : Node */ {
    val childElementCount: Int

    /** Returns the child elements. */
    val children: HTMLCollection

    /** Returns the first child that is an element, and null otherwise. */
    val firstElementChild: Element?

    /** Returns the last child that is an element, and null otherwise. */
    val lastElementChild: Element?

    /**
     * Inserts nodes after the last child of node, while replacing strings in nodes with equivalent Text nodes.
     *
     * Throws a "HierarchyRequestError" DOMException if the constraints of the node tree are violated.
     */
    fun append(vararg nodes: Any /* Node | string */)

    /**
     * Inserts nodes before the first child of node, while replacing strings in nodes with equivalent Text nodes.
     *
     * Throws a "HierarchyRequestError" DOMException if the constraints of the node tree are violated.
     */
    fun prepend(vararg nodes: Any /* Node | string */)

    /** Returns the first element that is a descendant of node that matches selectors. */
    fun <K extends querySelector keyof HTMLElementTagNameMap>(selectors: K): HTMLElementTagNameMap[K]?
    fun <K extends querySelector keyof SVGElementTagNameMap>(selectors: K): SVGElementTagNameMap[K]?
    fun <E extends querySelector Element = Element>(selectors: String): E?
    /** Returns all element descendants of node that match selectors. */
    fun <K extends querySelectorAll keyof HTMLElementTagNameMap>(selectors: K): NodeListOf<HTMLElementTagNameMap[K]>
    fun <K extends querySelectorAll keyof SVGElementTagNameMap>(selectors: K): NodeListOf<SVGElementTagNameMap[K]>
    fun <E extends querySelectorAll Element = Element>(selectors: String): NodeListOf<E>
    /**
     * Replace all children of node with nodes, while replacing strings in nodes with equivalent Text nodes.
     *
     * Throws a "HierarchyRequestError" DOMException if the constraints of the node tree are violated.
     */
    fun replaceChildren(vararg nodes: Any /* Node | string */)
}
