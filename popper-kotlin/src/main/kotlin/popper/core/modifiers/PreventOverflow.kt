// Automatically generated - do not modify!

@file:Suppress(
    "NAME_CONTAINS_ILLEGAL_CHARS",
)

package popper.core.modifiers

external interface PreventOverflowOptions {
    var mainAxis: Boolean
    var altAxis: Boolean
    var boundary: Boundary
    var rootBoundary: RootBoundary
    var altBoundary: Boolean
    var
            /**
             * Allows the popper to overflow from its boundaries to keep it near its
             * reference element
             */
            tether: Boolean
    var tetherOffset: TetherOffset
    var padding: popper.core.Padding
}

@JsName("'preventOverflow'")
external val PreventOverflow: popper.core.ModifierName<PreventOverflowOptions>
