// Automatically generated - do not modify!

package tanstack.table.core

external interface ExpandedRow {
    var toggleExpanded: (expanded?: boolean) => void
    var getIsExpanded: () => boolean
    var getCanExpand: () => boolean
    var getToggleExpandedHandler: () => () => void
}
