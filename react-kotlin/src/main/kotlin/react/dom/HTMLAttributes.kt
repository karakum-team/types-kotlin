// Automatically generated - do not modify!

package react.dom

external interface HTMLAttributes<T> : AriaAttributes, DOMAttributes<T> {
    // React-specific Attributes
    var defaultChecked: Boolean
    var defaultValue: string | number | ReadonlyArray<string>
    var suppressContentEditableWarning: Boolean
    var suppressHydrationWarning: Boolean
    var // Standard HTML AttributesaccessKey: String
    var className: String
    var contentEditable: Booleanish | "inherit"
    var contextMenu: String
    var dir: String
    var draggable: Boolean
    var hidden: Boolean
    var id: String
    var lang: String
    var placeholder: String
    var slot: String
    var spellCheck: Boolean
    var style: CSSProperties
    var tabIndex: Number
    var title: String
    var translate: String /* 'yes' | 'no' */
    var // UnknownradioGroup: string; // <command>, <menuitem>// WAI-ARIArole?: string
    var // RDFa Attributesabout: String
    var datatype: String
    var inlist: Any
    var prefix: String
    var property: String
    var resource: String
    var typeof: String
    var vocab: String
    var // Non-standard AttributesautoCapitalize: String
    var autoCorrect: String
    var autoSave: String
    var color: String
    var itemProp: String
    var itemScope: Boolean
    var itemType: String
    var itemID: String
    var itemRef: String
    var results: Number
    var security: String
    var unselectable: String /* 'on' | 'off' */
    var // Living Standard/** * Hints at the type of data that might be entered by the user while editing the element or its contents * @see https://html.spec.whatwg.org/multipage/interaction.html#input-modalities:-the-inputmode-attribute */inputMode: String /* 'none' | 'text' | 'tel' | 'url' | 'email' | 'numeric' | 'decimal' | 'search' */

    /**
     * Specify that a standard HTML element should behave like a defined custom built-in element
     * @see https://html.spec.whatwg.org/multipage/custom-elements.html#attr-is
     */
    var is: String
}
