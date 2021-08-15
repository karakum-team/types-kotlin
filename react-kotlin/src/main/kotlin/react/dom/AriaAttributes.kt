// Automatically generated - do not modify!

package react.dom

external interface AriaAttributes {
    /** Identifies the currently active element when DOM focus is on a composite widget, textbox, group, or application. */
    var ariaActivedescendant: String

    /** Indicates whether assistive technologies will present all, or only parts of, the changed region based on the change notifications defined by the aria-relevant attribute. */
    var ariaAtomic: Boolean

    /**
     * Indicates whether inputting text could trigger display of one or more predictions of the user's intended value for an input and specifies how predictions would be
     * presented if they are made.
     */
    var ariaAutocomplete: String // 'none' | 'inline' | 'list' | 'both'

    /** Indicates an element is being modified and that assistive technologies MAY want to wait until the modifications are complete before exposing them to the user. */
    var ariaBusy: Boolean

    /**
     * Indicates the current "checked" state of checkboxes, radio buttons, and other widgets.
     * @see aria-pressed @see aria-selected.
     */
    var ariaChecked: String // boolean | 'false' | 'mixed' | 'true'

    /**
     * Defines the total number of columns in a table, grid, or treegrid.
     * @see aria-colindex.
     */
    var ariaColcount: Int

    /**
     * Defines an element's column index or position with respect to the total number of columns within a table, grid, or treegrid.
     * @see aria-colcount @see aria-colspan.
     */
    var ariaColindex: Int

    /**
     * Defines the number of columns spanned by a cell or gridcell within a table, grid, or treegrid.
     * @see aria-colindex @see aria-rowspan.
     */
    var ariaColspan: Int

    /**
     * Identifies the element (or elements) whose contents or presence are controlled by the current element.
     * @see aria-owns.
     */
    var ariaControls: String

    /** Indicates the element that represents the current item within a container or set of related elements. */
    var ariaCurrent: String // boolean | 'false' | 'true' | 'page' | 'step' | 'location' | 'date' | 'time'

    /**
     * Identifies the element (or elements) that describes the object.
     * @see aria-labelledby
     */
    var ariaDescribedby: String

    /**
     * Identifies the element that provides a detailed, extended description for the object.
     * @see aria-describedby.
     */
    var ariaDetails: String

    /**
     * Indicates that the element is perceivable but disabled, so it is not editable or otherwise operable.
     * @see aria-hidden @see aria-readonly.
     */
    var ariaDisabled: Boolean

    /**
     * Indicates what functions can be performed when a dragged object is released on the drop target.
     * @deprecated in ARIA 1.1
     */
    var ariaDropeffect: String // 'none' | 'copy' | 'execute' | 'link' | 'move' | 'popup'

    /**
     * Identifies the element that provides an error message for the object.
     * @see aria-invalid @see aria-describedby.
     */
    var ariaErrormessage: String

    /** Indicates whether the element, or another grouping element it controls, is currently expanded or collapsed. */
    var ariaExpanded: Boolean

    /**
     * Identifies the next element (or elements) in an alternate reading order of content which, at the user's discretion,
     * allows assistive technology to override the general default of reading in document source order.
     */
    var ariaFlowto: String

    /**
     * Indicates an element's "grabbed" state in a drag-and-drop operation.
     * @deprecated in ARIA 1.1
     */
    var ariaGrabbed: Boolean

    /** Indicates the availability and type of interactive popup element, such as menu or dialog, that can be triggered by an element. */
    var ariaHaspopup: String // boolean | 'false' | 'true' | 'menu' | 'listbox' | 'tree' | 'grid' | 'dialog'

    /**
     * Indicates whether the element is exposed to an accessibility API.
     * @see aria-disabled.
     */
    var ariaHidden: Boolean

    /**
     * Indicates the entered value does not conform to the format expected by the application.
     * @see aria-errormessage.
     */
    var ariaInvalid: String // boolean | 'false' | 'true' | 'grammar' | 'spelling'

    /** Indicates keyboard shortcuts that an author has implemented to activate or give focus to an element. */
    var ariaKeyshortcuts: String

    /**
     * Defines a string value that labels the current element.
     * @see aria-labelledby.
     */
    var ariaLabel: String

    /**
     * Identifies the element (or elements) that labels the current element.
     * @see aria-describedby.
     */
    var ariaLabelledby: String

    /** Defines the hierarchical level of an element within a structure. */
    var ariaLevel: Int

    /** Indicates that an element will be updated, and describes the types of updates the user agents, assistive technologies, and user can expect from the live region. */
    var ariaLive: String // 'off' | 'assertive' | 'polite'

    /** Indicates whether an element is modal when displayed. */
    var ariaModal: Boolean

    /** Indicates whether a text box accepts multiple lines of input or only a single line. */
    var ariaMultiline: Boolean

    /** Indicates that the user may select more than one item from the current selectable descendants. */
    var ariaMultiselectable: Boolean

    /** Indicates whether the element's orientation is horizontal, vertical, or unknown/ambiguous. */
    var ariaOrientation: String // 'horizontal' | 'vertical'

    /**
     * Identifies an element (or elements) in order to define a visual, functional, or contextual parent/child relationship
     * between DOM elements where the DOM hierarchy cannot be used to represent the relationship.
     * @see aria-controls.
     */
    var ariaOwns: String

    /**
     * Defines a short hint (a word or short phrase) intended to aid the user with data entry when the control has no value.
     * A hint could be a sample value or a brief description of the expected format.
     */
    var ariaPlaceholder: String

    /**
     * Defines an element's number or position in the current set of listitems or treeitems. Not required if all elements in the set are present in the DOM.
     * @see aria-setsize.
     */
    var ariaPosinset: Int

    /**
     * Indicates the current "pressed" state of toggle buttons.
     * @see aria-checked @see aria-selected.
     */
    var ariaPressed: String // boolean | 'false' | 'mixed' | 'true'

    /**
     * Indicates that the element is not editable, but is otherwise operable.
     * @see aria-disabled.
     */
    var ariaReadonly: Boolean

    /**
     * Indicates what notifications the user agent will trigger when the accessibility tree within a live region is modified.
     * @see aria-atomic.
     */
    var ariaRelevant: String // 'additions' | 'additions removals' | 'additions text' | 'all' | 'removals' | 'removals additions' | 'removals text' | 'text' | 'text additions' | 'text removals'

    /** Indicates that user input is required on the element before a form may be submitted. */
    var ariaRequired: Boolean

    /** Defines a human-readable, author-localized description for the role of an element. */
    var ariaRoledescription: String

    /**
     * Defines the total number of rows in a table, grid, or treegrid.
     * @see aria-rowindex.
     */
    var ariaRowcount: Int

    /**
     * Defines an element's row index or position with respect to the total number of rows within a table, grid, or treegrid.
     * @see aria-rowcount @see aria-rowspan.
     */
    var ariaRowindex: Int

    /**
     * Defines the number of rows spanned by a cell or gridcell within a table, grid, or treegrid.
     * @see aria-rowindex @see aria-colspan.
     */
    var ariaRowspan: Int

    /**
     * Indicates the current "selected" state of various widgets.
     * @see aria-checked @see aria-pressed.
     */
    var ariaSelected: Boolean

    /**
     * Defines the number of items in the current set of listitems or treeitems. Not required if all elements in the set are present in the DOM.
     * @see aria-posinset.
     */
    var ariaSetsize: Int

    /** Indicates if items in a table or grid are sorted in ascending or descending order. */
    var ariaSort: String // 'none' | 'ascending' | 'descending' | 'other'

    /** Defines the maximum allowed value for a range widget. */
    var ariaValuemax: Double

    /** Defines the minimum allowed value for a range widget. */
    var ariaValuemin: Double

    /**
     * Defines the current value for a range widget.
     * @see aria-valuetext.
     */
    var ariaValuenow: Double

    /** Defines the human readable text alternative of aria-valuenow for a range widget. */
    var ariaValuetext: String
}
