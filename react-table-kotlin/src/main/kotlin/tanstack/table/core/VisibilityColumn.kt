// Automatically generated - do not modify!

package tanstack.table.core

external interface VisibilityColumn {
    getCanHide: () => boolean
    getIsVisible: () => boolean
    toggleVisibility: (value ?: boolean) => void
    getToggleVisibilityHandler: () => (event: unknown) => void
}
