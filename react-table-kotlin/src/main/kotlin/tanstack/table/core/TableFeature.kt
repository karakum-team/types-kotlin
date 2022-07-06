// Automatically generated - do not modify!

package tanstack.table.core

external interface TableFeature {
    var getDefaultOptions: ((table: Any) -> any)?
    var getInitialState: ((initialState?: InitialTableState) -> any)?
    var createTable: ((table: Any) -> any)?
    var getDefaultColumnDef: (() -> any)?
    var createColumn: ((column: Any, table: Any) -> any)?
    var createHeader: ((column: Any, table: Any) -> any)?
    var createCell: ((cell: Any, column: Any, row: Any, table: Any) -> any)?
    var createRow: ((row: Any, table: Any) -> any)?
}
