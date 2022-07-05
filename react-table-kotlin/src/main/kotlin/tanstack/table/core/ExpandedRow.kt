// Automatically generated - do not modify!

package tanstack.table.core

external interface ExpandedRow {
    var toggleExpanded: (expanded?: boolean) -> Unit
    var getIsExpanded: () -> Boolean
    var getCanExpand: () -> Boolean
    var getToggleExpandedHandler: () -> () -> Unit
}
