// Automatically generated - do not modify!

package tanstack.table.core

external fun <TDeps : readonly any[], TResult> memo(getDeps: () -> [...TDeps], fn: (...args: NoInfer<[...TDeps]>) -> TResult, opts: {
    key: any
    debug ?: () -> any
    onChange ?: (result: TResult) -> Unit
}): () -> TResult
