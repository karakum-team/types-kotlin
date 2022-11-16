// Automatically generated - do not modify!

package tanstack.table.core

typealias ResolvedAggregationFns = keyof AggregationFns extends never ? {
    aggregationFns
} : {
    aggregationFns: ReadonlyRecord<keyof AggregationFns, AggregationFn<*>>
}
