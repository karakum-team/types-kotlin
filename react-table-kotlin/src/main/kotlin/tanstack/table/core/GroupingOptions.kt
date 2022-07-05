// Automatically generated - do not modify!

package tanstack.table.core

external interface GroupingOptions = {
    manualGrouping ?: boolean
    onGroupingChange ?: OnChangeFn<GroupingState>
    enableGrouping ?: boolean
    getGroupedRowModel ?: (table: Table<any>) => () => RowModel<any>
    groupedColumnMode ?: false | 'reorder' | 'remove'
}
