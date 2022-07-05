// Automatically generated - do not modify!

package tanstack.table.core

external interface RowSelectionRow = {
    getIsSelected: () => boolean
    getIsSomeSelected: () => boolean
    getIsAllSubRowsSelected: () => boolean
    getCanSelect: () => boolean
    getCanMultiSelect: () => boolean
    getCanSelectSubRows: () => boolean
    toggleSelected: (value ?: boolean) => void
    getToggleSelectedHandler: () => (event: unknown) => void
}
