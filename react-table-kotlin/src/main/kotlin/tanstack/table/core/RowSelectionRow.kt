// Automatically generated - do not modify!

package tanstack.table.core

external interface RowSelectionRow {
    var getIsSelected: () -> boolean
    var getIsSomeSelected: () -> boolean
    var getIsAllSubRowsSelected: () -> boolean
    var getCanSelect: () -> boolean
    var getCanMultiSelect: () -> boolean
    var getCanSelectSubRows: () -> boolean
    var toggleSelected: (value?: boolean) -> void
    var getToggleSelectedHandler: () -> (event: unknown) -> void
}
