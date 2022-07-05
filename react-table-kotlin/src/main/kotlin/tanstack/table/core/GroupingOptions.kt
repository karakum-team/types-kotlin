// Automatically generated - do not modify!

package tanstack.table.core

external interface GroupingOptions {
    var manualGrouping: boolean?
    var onGroupingChange: OnChangeFn<GroupingState>?
    var enableGrouping: boolean?
    var getGroupedRowModel: ((table: Table<any>) -> () -> RowModel<any>)?
    var groupedColumnMode: false | 'reorder' | 'remove'?
}
