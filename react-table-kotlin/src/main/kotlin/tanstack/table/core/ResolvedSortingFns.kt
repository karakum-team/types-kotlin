// Automatically generated - do not modify!

package tanstack.table.core

typealias ResolvedSortingFns = keyof SortingFns extends never ? {
    sortingFns
} : {
    sortingFns: ReadonlyRecord<keyof SortingFns, SortingFn<*>>
}
