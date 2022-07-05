// Automatically generated - do not modify!

package tanstack.table.core

external interface TableFeature {
    var getDefaultOptions?: (table: any) -> any
    var getInitialState?: (initialState ?: InitialTableState) -> any
    var createTable?: (table: any) -> any
    var getDefaultColumnDef?: () -> any
    var createColumn?: (column: any, table: any) -> any
    var createHeader?: (column: any, table: any) -> any
    var createCell?: (cell: any, column: any, row: any, table: any) -> any
    var createRow?: (row: any, table: any) -> any
}
