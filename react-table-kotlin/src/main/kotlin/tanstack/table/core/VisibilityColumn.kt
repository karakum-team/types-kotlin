// Automatically generated - do not modify!

package tanstack.table.core

external interface VisibilityColumn {
    var getCanHide: () -> boolean
    var getIsVisible: () -> boolean
    var toggleVisibility: (value?: boolean) -> void
    var getToggleVisibilityHandler: () -> (event: unknown) -> void
}
