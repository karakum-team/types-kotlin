// Automatically generated - do not modify!

package tanstack.table.core

typealias ResolvedFilterFns = keyof FilterFns extends never ? {
    filterFns
} : {
    filterFns: ReadonlyRecord<keyof FilterFns, FilterFn<*>>
}
